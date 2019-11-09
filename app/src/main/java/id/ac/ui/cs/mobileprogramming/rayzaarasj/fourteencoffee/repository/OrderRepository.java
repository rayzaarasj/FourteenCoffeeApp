package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.AppDatabase;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao.OrderDAO;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;

public class OrderRepository {
    private OrderDAO orderDao;

    public OrderRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        orderDao = db.getOrderDAO();
    }

    public LiveData<List<Order>> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public LiveData<Order> getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    public void insertOrder(Order order) {
        new InsertOrderAsyncTask(orderDao).execute(order);
    }

    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDAO orderDAO;

        public InsertOrderAsyncTask(OrderDAO orderDAO) {
            this.orderDAO = orderDAO;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDAO.insert(orders[0]);
            Log.d("DEBUGGER", "done inserting order");
            // TODO : jalanin async task yang bakal update status order
            return null;
        }
    }

    public void deleteAll() {
        new DeleteAllOrderAsyncTask(orderDao).execute();
    }

    private static class DeleteAllOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDAO orderDAO;

        public DeleteAllOrderAsyncTask(OrderDAO orderDAO) {
            this.orderDAO = orderDAO;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDAO.deleteAll();
            Log.d("DEBUGGER", "done deleting order");
            return null;
        }
    }

    public void updateOrder(Order order) {
        new UpdateOrderAsyncTask(orderDao).execute(order);
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDAO orderDAO;

        public UpdateOrderAsyncTask(OrderDAO orderDAO) {
            this.orderDAO = orderDAO;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDAO.updateOrder(orders[0]);
            Log.d("DEBUGGER", "done update order " + orders[0].getId());
            return null;
        }
    }

}
