package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.AddressViewModel;

public class AddAddressFragment extends Fragment {

    private AddressViewModel addressViewModel;
    private List<Address> addresses;

    public static AddAddressFragment newInstance() {
        return new AddAddressFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_address_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressViewModel = ViewModelProviders.of(getActivity()).get(AddressViewModel.class);
        addressViewModel.addressLiveData.setValue(null);
        final EditText addAddressEditView = getView().findViewById(R.id.add_address_edit_text);

        final Button addAddressAddButton = getView().findViewById(R.id.add_address_add_button);
        addAddressAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean conflict = false;
                for (Address address : addressViewModel.addresses) {
                    if (addAddressEditView.getText().toString().equals(address.getAddress())) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict) {
                    addressViewModel.insert(new Address(addAddressEditView.getText().toString()));
                } else {
                    Toast.makeText(getActivity(), R.string.input_address_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button addAddressBackButton = getView().findViewById(R.id.add_address_back_button);
        addAddressBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        addressViewModel.getAllAddress().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                addressViewModel.addresses = addresses;
            }
        });

        addressViewModel.addressLiveData.observe(this, new Observer<Address>() {
            @Override
            public void onChanged(Address address) {
                if (address != null) {
                    getActivity().onBackPressed();
                    Toast.makeText(getActivity(), R.string.input_address_success, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
