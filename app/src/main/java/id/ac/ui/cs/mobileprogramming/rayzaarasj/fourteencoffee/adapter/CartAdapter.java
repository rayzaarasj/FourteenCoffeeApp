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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Cart> cartList;

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.cartMenuName.setText(cart.getMenu().getName());
        holder.cartMenuPrice.setText("" + cart.getMenu().getPrice() + " IDR");
        holder.cartCount.setText("" + cart.getCount());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartMenuName;
        private final TextView cartMenuPrice;
        private final TextView cartCount;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartMenuName = itemView.findViewById(R.id.order_item_name);
            cartMenuPrice = itemView.findViewById(R.id.order_item_price);
            cartCount = itemView.findViewById(R.id.order_item_count);
        }
    }
}
