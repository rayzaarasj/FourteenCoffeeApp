package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.adapter.ChooseAddressAdapter;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository.AddressRepository;

public class AddressViewModel extends AndroidViewModel {

    public List<Address> addresses;
    public ChooseAddressAdapter chooseAddressAdapter = new ChooseAddressAdapter();

    private AddressRepository addressRepository;
    private LiveData<List<Address>> addressListData;
    public MutableLiveData<Address> addressLiveData = new MutableLiveData<>();

    public AddressViewModel(@NonNull Application application) {
        super(application);
        addressRepository = new AddressRepository(application);
        addressListData = addressRepository.getAllAddresses();
    }

    public LiveData<List<Address>> getAllAddress() {
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
