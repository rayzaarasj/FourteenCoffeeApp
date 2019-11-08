package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao.AddressDAO;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;

public class AddressRepository {
    private AddressDAO addressDao;

    public AddressRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        addressDao = db.getAddressDAO();
    }

    public LiveData<List<Address>> getAllAddreses() {
        return addressDao.getAllAddress();
    }

    public void insertAddress(Address address){
        new InsertAddressAsyncTask(addressDao).execute(address);
    }

    public void deleteAll() {
        new DeleteAllAddressesAsyncTask(addressDao).execute();
    }

    private static class DeleteAllAddressesAsyncTask extends AsyncTask<Address, Void, Void> {
        private AddressDAO addressDao;

        public DeleteAllAddressesAsyncTask(AddressDAO addressDao) {
            this.addressDao = addressDao;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            addressDao.deleteAll();
            return null;
        }
    }

    private static class InsertAddressAsyncTask extends AsyncTask<Address, Void, Void> {
        private AddressDAO addressDao;

        public InsertAddressAsyncTask(AddressDAO addressDao) {
            this.addressDao = addressDao;
        }

        @Override
        protected Void doInBackground(Address... addresses) {
            addressDao.insert(addresses[0]);
            return null;
        }
    }
}
