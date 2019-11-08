package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {

    List<Cart> cartList;

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.cartMenuName.setText(cart.getMenu().getName());
        holder.cartMenuPrice.setText("" + cart.getMenu().getPrice() + " IDR");
        holder.cartCount.setText("" + cart.getCount());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartMenuName;
        private final TextView cartMenuPrice;
        private final TextView cartCount;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            cartMenuName = itemView.findViewById(R.id.order_item_name);
            cartMenuPrice = itemView.findViewById(R.id.order_item_price);
            cartCount = itemView.findViewById(R.id.order_item_count);
        }
    }
}
