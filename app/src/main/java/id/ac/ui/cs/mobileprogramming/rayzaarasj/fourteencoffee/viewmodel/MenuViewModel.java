package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository.MenuRepository;

public class MenuViewModel extends AndroidViewModel {

    public List<Menu> menus;
    public MutableLiveData<List<Cart>> carts = new MutableLiveData<>();

    private MenuRepository menuRepository;
    private LiveData<List<Menu>> menuListData;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        menuRepository = new MenuRepository(application);
        menuListData = menuRepository.getAllMenus();
    }

    public LiveData<List<Menu>> getAllMenus() {
        return menuListData;
    }

    public void insert(Menu menu) {
        menuRepository.insertMenu(menu);
    }

    public void deleteAll() {
        menuRepository.deleteAll();
    }
}
