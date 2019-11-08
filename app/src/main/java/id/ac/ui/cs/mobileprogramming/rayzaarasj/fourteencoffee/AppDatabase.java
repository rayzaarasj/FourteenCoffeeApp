package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao.AddressDAO;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao.MenuDAO;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Address;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;

@Database(entities = {Address.class, Menu.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "fourteen_coffee_db";
    public static volatile AppDatabase INSTANCE;

    public abstract AddressDAO getAddressDAO();
    public abstract MenuDAO getMenuDAO();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
