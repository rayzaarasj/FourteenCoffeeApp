package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Order;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository.OrderRepository;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.OrderViewModel;

public class CheckCoffeeService extends Service {

    private OrderRepository orderRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int orderId = intent.getExtras().getInt("orderId");
        String orderDate = intent.getExtras().getString("orderDate");
        String orderMenus = intent.getExtras().getString("orderMenus");
        String orderPrices = intent.getExtras().getString("orderPrices");
        String orderCounts = intent.getExtras().getString("orderCounts");
        String orderAddress = intent.getExtras().getString("orderAddress");

        final OrderViewModel orderViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(OrderViewModel.class);
        final Order order = new Order(
                orderId,
                orderDate,
                orderMenus,
                orderPrices,
                orderCounts,
                orderAddress,
                false
        );
        final Handler mainHandler = new Handler();
        final Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                final Runnable runnable = this;
                final boolean[] done = {false};
                RequestParams requestParams = new RequestParams();
                requestParams.put("key", order.getId());
                ApiUtils.get("order/get/", requestParams, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            if (response.getJSONObject(0).getJSONObject("fields").getBoolean("done")) {
                                order.setDone(true);
                                orderViewModel.updateOrder(order);
                                done[0] = true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            if (!done[0]) {
                                mainHandler.postDelayed(runnable, 1000L);
                            }
                        }
                    }
                });
            }
        };
        mainHandler.post(myRunnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
