package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;

@Dao
public interface OrderDAO {

    @Insert
    Long insert(Order order);

    @Query("SELECT * from orders")
    LiveData<List<Order>> getAllOrders();

    @Query("DELETE from orders")
    void deleteAll();

    @Query("SELECT * from orders where orders.id = :id")
    LiveData<Order> getOrderById(int id);

    @Update
    void updateOrder(Order order);
}
