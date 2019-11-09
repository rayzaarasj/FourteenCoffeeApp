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
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;

public class ChooseAddressAdapter extends RecyclerView.Adapter<ChooseAddressAdapter.ChooseAddressViewHolder> {

    List<Address> addressList;
    private OnItemClickListener clickListener;

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ChooseAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_item, parent, false);
        return new ChooseAddressAdapter.ChooseAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAddressViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.addressTextView.setText(address.getAddress());
        holder.bind(address, this.clickListener);
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ChooseAddressViewHolder extends RecyclerView.ViewHolder {
        private final TextView addressTextView;
        private final CardView cardView;

        public ChooseAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.address_item_text);
            cardView = itemView.findViewById(R.id.address_item_card);
        }

        void bind(final Address address, final OnItemClickListener clickListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, address);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Address address);
    }
}
