package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.AddressRepository;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;

public class AddressViewModel extends AndroidViewModel {

    private AddressRepository addressRepository;
    private LiveData<List<Address>> addressListData;

    public AddressViewModel(@NonNull Application application) {
        super(application);
        addressRepository = new AddressRepository(application);
        addressListData = addressRepository.getAllAddreses();
    }

    public LiveData<List<Address>> getAllAddress() {
        return addressListData;
    }

    public void insert(Address address) {
        addressRepository.insertAddress(address);
    }

    public void deleteAll() {
        addressRepository.deleteAll();
    }
}
