package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.HistoryAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.OrderViewModel;

public class HistoryFragment extends Fragment implements HistoryAdapter.OnItemClickListener {

    private OrderViewModel orderViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);
        orderViewModel.historyAdapter.setClickListener(this);

        ImageView historyBackImage = getView().findViewById(R.id.history_back_image);
        historyBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                //TODO: ini cara udpate order status
//                Order order = orderViewModel.orders.getValue().get(0);
//                order.setDone(true);
//                orderViewModel.updateOrder(order);
            }
        });

//        orderViewModel.deleteAll();
//        orderViewModel.insert(new Order(
//                1,
//                "14-10-2019",
//                "A;B;C",
//                "10;20;30",
//                "1;2;3",
//                "Taman Melati Maegonda A1426",
//                false
//        ));
//        orderViewModel.insert(new Order(
//                2,
//                "15-10-2019",
//                "A;B;C;D",
//                "10;20;30;40",
//                "1;2;3;4",
//                "Taman Melati Maegonda A1427",
//                false
//        ));
        orderViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                if (orders.size() > 0) {
                    Log.d("DEBUGGER", "" + orders.get(0).getTotalPrice());
                }
                orderViewModel.orders.setValue(orders);
                HistoryAdapter historyAdapter = orderViewModel.historyAdapter;
                historyAdapter.setOrderList(orders);
                RecyclerView historyRecyclerView = getView().findViewById(R.id.history_recycler_view);
                historyRecyclerView.setAdapter(historyAdapter);
                historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    @Override
    public void onItemClick(View view, Order order) {
//        Toast.makeText(getContext(), "You clicked" + orderViewModel.orders.getValue().indexOf(order), Toast.LENGTH_SHORT).show();
        orderViewModel.activeOrderDetailIndex = orderViewModel.orders.getValue().indexOf(order);
        getFragmentManager().beginTransaction()
                .replace(R.id.history_container, OrderDetailFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
