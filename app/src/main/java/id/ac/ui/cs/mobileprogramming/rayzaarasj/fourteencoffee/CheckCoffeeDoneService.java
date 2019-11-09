package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class CheckCoffeeDoneService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        final Handler mainHandler = new Handler();
//        final Runnable myRunnable = new Runnable() {
//            @Override
//            public void run() {
//                final Runnable runnable = this;
//                final boolean[] done = {false};
//                RequestParams requestParams = new RequestParams();
//                requestParams.put("key", order.getId());
//                ApiUtils.get("order/get/", requestParams, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                        super.onSuccess(statusCode, headers, response);
//                        try {
//                            Log.d("DEBUGGER", "asynctask" + order.getId() + " " + response.getJSONObject(0).getJSONObject("fields").getBoolean("done"));
//                            if (response.getJSONObject(0).getJSONObject("fields").getBoolean("done")) {
//                                order.setDone(true);
//                                orderViewModel.updateOrder(order);
//                                Log.d("DEBUGGER", "done asynctask");
//                                done[0] = true;
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } finally {
//                            if (!done[0]) {
//                                mainHandler.postDelayed(runnable, 1000L);
//                            }
//                        }
//                    }
//                });
//            }
//        };
//        mainHandler.post(myRunnable);
    }
}
