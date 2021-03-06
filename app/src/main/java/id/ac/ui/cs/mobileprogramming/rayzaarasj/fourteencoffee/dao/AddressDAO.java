package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;

@Dao
public interface AddressDAO {

    @Insert
    Long insert(Address address);

    @Query("SELECT * from addresses")
    LiveData<List<Address>> getAllAddress();

    @Query("DELETE from addresses")
    void deleteAll();
}
