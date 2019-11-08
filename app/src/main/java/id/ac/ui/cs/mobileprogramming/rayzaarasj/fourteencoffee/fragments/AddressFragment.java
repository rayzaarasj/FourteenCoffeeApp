package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.AddressAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.AddressViewModel;

public class AddressFragment extends Fragment {

    private AddressViewModel addressViewModel;

    public static AddressFragment newInstance() {
        return new AddressFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.address_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressViewModel = ViewModelProviders.of(getActivity()).get(AddressViewModel.class);

        Button addAddressButton = getView().findViewById(R.id.add_address_button);
        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.address_container, AddAddressFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
//                addressViewModel.deleteAll();

            }
        });

//        List<Address> temp = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            temp.add(new Address("Taman Melati Margonda Unit A142" + i));
//        }

//        addressViewModel.deleteAll();
//        addressViewModel.insert(new Address("Taman Melati Margonda Unit A1426"));
        addressViewModel.getAllAddress().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                AddressAdapter addressAdapter = new AddressAdapter();
                Log.d("data", "Data size:" + addresses.size());
                addressAdapter.setAddressList(addresses);
                RecyclerView addressRecyclerView = getView().findViewById(R.id.address_recycler_view);
                addressRecyclerView.setAdapter(addressAdapter);
                addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }
}
