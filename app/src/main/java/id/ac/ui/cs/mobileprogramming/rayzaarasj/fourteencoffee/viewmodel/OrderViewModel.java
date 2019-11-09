package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.HistoryAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository.OrderRepository;

public class OrderViewModel extends AndroidViewModel {

    public int activeOrderDetailIndex;
    public HistoryAdapter historyAdapter = new HistoryAdapter();
    public MutableLiveData<List<Order>> orders = new MutableLiveData<>();

    private OrderRepository orderRepository;
    private LiveData<List<Order>> orderListData;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
        orderListData = orderRepository.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return orderListData;
    }

    public LiveData<Order> getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    public void insert(Order order) {
        orderRepository.insertOrder(order);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }
}
