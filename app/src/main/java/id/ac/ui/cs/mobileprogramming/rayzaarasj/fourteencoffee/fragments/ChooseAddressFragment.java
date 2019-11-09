package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.ChooseAddressAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.AddressViewModel;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.MenuViewModel;

public class ChooseAddressFragment extends Fragment implements ChooseAddressAdapter.OnItemClickListener{

    private MenuViewModel menuViewModel;
    private AddressViewModel addressViewModel;

    public static ChooseAddressFragment newInstance() {
        return new ChooseAddressFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_address_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressViewModel = ViewModelProviders.of(getActivity()).get(AddressViewModel.class);
        menuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        addressViewModel.chooseAddressAdapter.setClickListener(this);

        addressViewModel.getAllAddress().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                ChooseAddressAdapter chooseAddressAdapter = addressViewModel.chooseAddressAdapter;
                Log.d("data", "Data size:" + addresses.size());
                chooseAddressAdapter.setAddressList(addresses);
                RecyclerView addressRecyclerView = getView().findViewById(R.id.choose_address_recycler_view);
                addressRecyclerView.setAdapter(chooseAddressAdapter);
                addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    @Override
    public void onItemClick(View view, Address address) {
        menuViewModel.address = address.getAddress();
        getActivity().onBackPressed();
    }
}
