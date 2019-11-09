package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.CheckoutAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.OrderViewModel;

public class OrderDetailFragment extends Fragment {

    private OrderViewModel orderViewModel;

    public static OrderDetailFragment newInstance() {
        return new OrderDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);
        Order activeOrder = orderViewModel.orders.getValue().get(orderViewModel.activeOrderDetailIndex);
        List<Cart> activeOrderCart = activeOrder.getOrderCarts();

        Log.d("DEBUGGER", "activeOrderCart" + activeOrderCart);
        CheckoutAdapter checkoutAdapter = new CheckoutAdapter();
        checkoutAdapter.setCartList(activeOrderCart);
        RecyclerView orderDetailRecyclerView = getView().findViewById(R.id.order_detail_recycler_view);
        orderDetailRecyclerView.setAdapter(checkoutAdapter);
        orderDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView addressText = getView().findViewById(R.id.order_detail_address);
        addressText.setText(activeOrder.getAddress());

        orderViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                Order activeOrder = orders.get(orderViewModel.activeOrderDetailIndex);
                TextView statusText = getView().findViewById(R.id.order_detail_status);
                statusText.setText(R.string.status);
                String statusString = statusText.getText().toString();
                statusText.setText(activeOrder.isDone() ? R.string.done : R.string.process);
                statusText.setText(statusString + " : " + statusText.getText());
            }
        });

        TextView totalPriceText = getView().findViewById(R.id.order_detail_total_price);
        totalPriceText.setText(totalPriceText.getText() + " : " + activeOrder.getTotalPrice());

        Button receiptButton = getView().findViewById(R.id.order_detail_receipt_button);
        receiptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Order order = orderViewModel.orders.getValue().get(orderViewModel.activeOrderDetailIndex);
//                order.setDone(true);
//                orderViewModel.updateOrder(order);
            }
        });

        Button backButton = getView().findViewById(R.id.order_detail_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
