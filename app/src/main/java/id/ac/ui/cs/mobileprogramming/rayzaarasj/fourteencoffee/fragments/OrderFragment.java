package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.ApiUtils;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.CartAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.MenuViewModel;

public class OrderFragment extends Fragment {

    private MenuViewModel menuViewModel;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        menuViewModel.getAllMenus().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                menuViewModel.menus = menus;

                List<Cart> cartList = new ArrayList<>();
                for (Menu menu : menuViewModel.menus) {
                    cartList.add(new Cart(menu, 0));
                }
                menuViewModel.carts.setValue(cartList);

                CartAdapter cartAdapter = new CartAdapter();
                Log.d("DEBUGGER", "" + menuViewModel.carts.getValue());
                cartAdapter.setCartList(menuViewModel.carts.getValue());
                RecyclerView cartRecyclerView = getView().findViewById(R.id.order_recycler_view);
                cartRecyclerView.setAdapter(cartAdapter);
                cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
//
        menuViewModel.carts.observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                CartAdapter cartAdapter = new CartAdapter();
                Log.d("DEBUGGER", "" + menuViewModel.carts.getValue());
                cartAdapter.setCartList(menuViewModel.carts.getValue());
                RecyclerView cartRecyclerView = getView().findViewById(R.id.order_recycler_view);
                cartRecyclerView.setAdapter(cartAdapter);
                cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        ImageView orderBackImage = getView().findViewById(R.id.order_back_image);
        orderBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        Button checkoutButton = getView().findViewById(R.id.order_checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> cartList = menuViewModel.carts.getValue();
                cartList.get(0).setCount(1);
                menuViewModel.carts.setValue(cartList);
            }
        });

        RequestParams requestParams = new RequestParams();
        ApiUtils.get("menu/get-all/", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUGGER", "---------------- this is response : " + response);
                try {
                    Log.d("DEBUGGER", " " + menuViewModel.menus.size());
                    Log.d("DEBUGGER", " this is response : " + response.getJSONObject((0)));
                    Log.d("DEBUGGER", " this is response : " + response.getJSONObject((0)).getString("pk"));
                    Log.d("DEBUGGER", " this is response : " + response.getJSONObject((0)).getJSONObject("fields"));
                    Log.d("DEBUGGER", " this is response : " + response.getJSONObject((0)).getJSONObject("fields").getInt("price"));
                    Log.d("DEBUGGER", " this is response : " + response.getJSONObject((0)).getJSONObject("fields").getString("image_url"));
                    if (menuViewModel.menus.size() != response.length()) {
                        menuViewModel.deleteAll();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject menuJSON = response.getJSONObject(i);
                            String name = menuJSON.getString("pk");
                            int price = menuJSON.getJSONObject("fields").getInt("price");
                            String imageUrl = menuJSON.getJSONObject("fields").getString("image_url");
                            menuViewModel.insert(new Menu(name, price, imageUrl));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
