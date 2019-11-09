package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.RequestParams;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.CheckoutAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.MenuViewModel;

public class CheckoutFragment extends Fragment {

    private MenuViewModel menuViewModel;

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
        Log.d("DEBUGGER", "checkout" + menuViewModel.getNonEmptyCart().size());

        List<Cart> nonEmptyCart = menuViewModel.getNonEmptyCart();

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

        Button chooseAddress = getView().findViewById(R.id.checkout_address);
        chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUGGER", "klik text");
                getFragmentManager().beginTransaction()
                        .replace(R.id.order_container, ChooseAddressFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button order = getView().findViewById(R.id.checkout_button);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams requestParams = new RequestParams();
                Toast.makeText(getContext(), "ORDER", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
