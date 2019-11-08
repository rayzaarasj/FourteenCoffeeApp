package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;

@Dao
public interface MenuDAO {

    @Insert
    Long insert(Menu address);

    @Query("SELECT * from menus")
    LiveData<List<Menu>> getAllAddress();

    @Query("DELETE from menus")
    void deleteAll();
}
