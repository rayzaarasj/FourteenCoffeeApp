package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.AppDatabase;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.dao.MenuDAO;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;

public class MenuRepository {
    private MenuDAO menuDao;

    public MenuRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        menuDao = db.getMenuDAO();
    }

    public LiveData<List<Menu>> getAllMenus() {
        return menuDao.getAllAddress();
    }

    public void insertMenu(Menu menu) {
        new InsertMenuAsyncTask(menuDao).execute(menu);
    }

    private static class InsertMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDAO menuDao;

        public InsertMenuAsyncTask(MenuDAO menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.insert(menus[0]);
            Log.d("DEBUGGER", "done inserting menu");
            return null;
        }
    }

    public void deleteAll() {
        new DeleteAllMenusAsyncTask(menuDao).execute();
    }

    public static class DeleteAllMenusAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDAO menuDao;

        public DeleteAllMenusAsyncTask(MenuDAO menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.deleteAll();
            Log.d("DEBUGGER", "done deleting menu");
            return null;
        }
    }
}
