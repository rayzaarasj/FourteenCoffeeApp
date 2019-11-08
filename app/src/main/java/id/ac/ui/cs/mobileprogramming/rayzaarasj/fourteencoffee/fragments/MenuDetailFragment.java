package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.MenuViewModel;

public class MenuDetailFragment extends Fragment {

    private MenuViewModel menuViewModel;

    public static MenuDetailFragment newInstance() {
        return new MenuDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);

        ImageView add = getView().findViewById(R.id.menu_detail_add_image);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> cartList = menuViewModel.carts.getValue();
                cartList.get(menuViewModel.activeDetailIndex).add();
                menuViewModel.carts.setValue(cartList);
            }
        });

        ImageView minus = getView().findViewById(R.id.menu_detail_minus_image);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> cartList = menuViewModel.carts.getValue();
                cartList.get(menuViewModel.activeDetailIndex).minus();
                menuViewModel.carts.setValue(cartList);
            }
        });

        menuViewModel.carts.observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                Log.d("DEBUGGER", "carts update");
                Cart activeCart = menuViewModel.carts.getValue().get(menuViewModel.activeDetailIndex);

                TextView menuDetailName = getView().findViewById(R.id.menu_detail_name);
                menuDetailName.setText(activeCart.getMenu().getName());

                TextView menuDetailPrice = getView().findViewById(R.id.menu_detail_price);
                menuDetailPrice.setText("" + activeCart.getMenu().getPrice() + " IDR");

                TextView menuDetailCount = getView().findViewById(R.id.menu_detail_count);
                menuDetailCount.setText("" + activeCart.getCount());

                ImageView menuDetailImage = getView().findViewById(R.id.menu_detail_image);
                Picasso.get().load(activeCart.getMenu().getImageUrl()).into(menuDetailImage);
            }
        });
    }
}
