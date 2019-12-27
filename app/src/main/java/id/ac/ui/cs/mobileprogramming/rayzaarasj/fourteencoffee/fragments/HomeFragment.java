package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.AddressActivity;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.HistoryActivity;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.OrderActivity;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button orderButton = getView().findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm =
                        (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {
                    Intent orderIntent = new Intent(getActivity(), OrderActivity.class);
                    getActivity().finish();
                    startActivity(orderIntent);
                } else {
                    Toast.makeText(getContext(), R.string.not_connected_to_internet, Toast.LENGTH_LONG).show();
                }
            }
        });

        Button historyButton = getView().findViewById(R.id.history_button);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyIntent = new Intent(getActivity(), HistoryActivity.class);
                getActivity().finish();
                startActivity(historyIntent);
            }
        });

        Button addressButton = getView().findViewById(R.id.address_button);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addressIntent = new Intent(getActivity(), AddressActivity.class);
                getActivity().finish();
                startActivity(addressIntent);
            }
        });
    }
}
