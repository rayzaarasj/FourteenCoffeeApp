package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.ApiUtils;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.activity.HomeActivity;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.CheckoutAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.MenuViewModel;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.OrderViewModel;

public class CheckoutFragment extends Fragment {

    private MenuViewModel menuViewModel;
    private OrderViewModel orderViewModel;

    public static CheckoutFragment newInstance() {
        return new CheckoutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        orderViewModel = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);

        final List<Cart> nonEmptyCart = menuViewModel.getNonEmptyCart();

        CheckoutAdapter checkoutAdapter = new CheckoutAdapter();
        checkoutAdapter.setCartList(nonEmptyCart);
        RecyclerView checkoutRecyclerView = getView().findViewById(R.id.checkout_recycler_view);
        checkoutRecyclerView.setAdapter(checkoutAdapter);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView checkoutTotal = getView().findViewById(R.id.checkout_total);
        int total = 0;
        for (Cart cart : nonEmptyCart) {
            total += (cart.getCount() * cart.getMenu().getPrice());
        }
        checkoutTotal.setText(checkoutTotal.getText() + " : " + total + " IDR");

        if (menuViewModel.address != null) {
            Button chooseAddress = getView().findViewById(R.id.checkout_address);
            chooseAddress.setText(menuViewModel.address);
        }

        ImageView checkoutBackImage = getView().findViewById(R.id.checkout_back_image);
        checkoutBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        final Button chooseAddress = getView().findViewById(R.id.checkout_address);
        chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.order_container, ChooseAddressFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        orderViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                orderViewModel.orders.setValue(orders);
            }
        });

        final Button order = getView().findViewById(R.id.checkout_button);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nonEmptyCart.size() <= 0) {
                    Toast.makeText(getContext(), R.string.cart_empty, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (menuViewModel.address == null) {
                    Toast.makeText(getContext(), R.string.address_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                String menus = "";
                String prices = "";
                String counts = "";
                final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                for (Cart cart : nonEmptyCart) {
                    menus += cart.getMenu().getName() + ";";
                    prices += cart.getMenu().getPrice() + ";";
                    counts += cart.getCount() + ";";
                }

                menus = menus.substring(0, menus.length() - 1);
                prices = prices.substring(0, prices.length() - 1);
                counts = counts.substring(0, counts.length() - 1);
                final int id = orderViewModel.orders.getValue().size() + 1;

                RequestParams requestParams = new RequestParams();
                requestParams.put("key", id);
                requestParams.put("menus", menus);
                requestParams.put("prices", prices);
                requestParams.put("counts", counts);

                final String finalMenus = menus;
                final String finalPrices = prices;
                final String finalCounts = counts;
                final Order order = new Order(
                        id,
                        date,
                        finalMenus,
                        finalPrices,
                        finalCounts,
                        menuViewModel.address,
                        false
                );
                ApiUtils.get("order/post/", requestParams, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        orderViewModel.insert(order);
                        final Handler mainHandler = new Handler();
                        final Runnable myRunnable = new Runnable() {
                            @Override
                            public void run() {
                                final Runnable runnable = this;
                                final boolean[] done = {false};
                                RequestParams requestParams = new RequestParams();
                                requestParams.put("key", order.getId());
                                ApiUtils.get("order/get/", requestParams, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                        super.onSuccess(statusCode, headers, response);
                                        try {
                                            if (response.getJSONObject(0).getJSONObject("fields").getBoolean("done")) {
                                                order.setDone(true);
                                                orderViewModel.updateOrder(order);
                                                done[0] = true;
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } finally {
                                            if (!done[0]) {
                                                mainHandler.postDelayed(runnable, 1000L);
                                            }
                                        }
                                    }
                                });
                            }
                        };
                        mainHandler.post(myRunnable);
                    }
                });
                Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                getActivity().finish();
                startActivity(homeIntent);
            }
        });
    }
}
