package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Cart> cartList;
    private OnItemClickListener clickListener;

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
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
        holder.bind(cart, this.clickListener);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartMenuName;
        private final TextView cartMenuPrice;
        private final TextView cartCount;
        private final CardView cardView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartMenuName = itemView.findViewById(R.id.order_item_name);
            cartMenuPrice = itemView.findViewById(R.id.order_item_price);
            cartCount = itemView.findViewById(R.id.order_item_count);
            cardView = itemView.findViewById(R.id.order_item);
        }

        void bind(final Cart cart, final OnItemClickListener clickListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, cart);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Cart cart);
    }
}
