package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository.AddressRepository;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;

public class AddressViewModel extends AndroidViewModel {

    public List<Address> addresses;

    private AddressRepository addressRepository;
    private LiveData<List<Address>> addressListData;
    public MutableLiveData<Address> addressLiveData = new MutableLiveData<>();

    public AddressViewModel(@NonNull Application application) {
        super(application);
        addressRepository = new AddressRepository(application);
        addressListData = addressRepository.getAllAddresses();
    }

    public LiveData<List<Address>> getAllAddress() {
        Log.d("DEBUGGER", ""  + addressRepository.getAllAddresses().getValue());
        return addressListData;
    }

    public void insert(Address address) {
        addressRepository.insertAddress(address);
        addressLiveData.setValue(address);
    }

    public void deleteAll() {
        addressRepository.deleteAll();
    }
}
