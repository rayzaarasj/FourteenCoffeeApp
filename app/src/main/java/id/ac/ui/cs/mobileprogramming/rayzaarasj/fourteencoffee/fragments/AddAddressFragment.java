package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel.AddressViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddAddressFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 200;

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
                addAddress(addAddressEditView.getText().toString());
            }
        });

        Button addAddressBackButton = getView().findViewById(R.id.add_address_back_button);
        addAddressBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        Button useCurrentLongitudeAndLatitudeButton = getView().findViewById(R.id.use_current_long_lat_button);
        useCurrentLongitudeAndLatitudeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    String address = "long:" + longitude + "\nlat:" + latitude;
                    addAddress(address);
                } else {
                    requestPermissions(new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
                }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted && ContextCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();
                        String address = "long:" + longitude + "\nlat:" + latitude;
                        addAddress(address);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                new AlertDialog.Builder(getContext())
                                        .setMessage(getResources().getString(R.string.permission_reason))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        })
                                        .setNegativeButton(R.string.cancel, null)
                                        .create()
                                        .show();
                            }
                        }
                    }
                }
        }
    }

    public void addAddress(String address) {
        if (address.equals("")) {
            Toast.makeText(getActivity(), R.string.input_address_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        boolean conflict = false;
        for (Address addressObj : addressViewModel.addresses) {
            if (address.equals(addressObj.getAddress())) {
                conflict = true;
                break;
            }
        }
        if (!conflict) {
            addressViewModel.insert(new Address(address));
        } else {
            Toast.makeText(getActivity(), R.string.input_address_failed, Toast.LENGTH_SHORT).show();
        }
    }
}
