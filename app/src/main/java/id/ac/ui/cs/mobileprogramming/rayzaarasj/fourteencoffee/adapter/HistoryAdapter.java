package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<Order> orderList;

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.historyItemDate.setText("Order on " + order.getDate());
        holder.historyItemTotalPrice.setText("" + order.getTotalPrice() + " IDR");
        holder.historyItemStatus.setText((order.isDone()) ? R.string.done : R.string.process);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView historyItemDate;
        private final TextView historyItemTotalPrice;
        private final TextView historyItemStatus;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            historyItemDate = itemView.findViewById(R.id.history_item_date);
            historyItemTotalPrice = itemView.findViewById(R.id.history_item_total_price);
            historyItemStatus = itemView.findViewById(R.id.history_item_status);
        }
    }
}
