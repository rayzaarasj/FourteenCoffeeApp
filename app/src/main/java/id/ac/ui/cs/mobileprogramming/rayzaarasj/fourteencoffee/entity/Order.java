package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {

    @NonNull
    @PrimaryKey
    private int id;

    private String date;

    private String menus;

    private String prices;

    private String counts;

    private boolean done;

    public Order(int id, String date, String menus, String prices, String counts, boolean done) {
        this.id = id;
        this.date = date;
        this.menus = menus;
        this.prices = prices;
        this.counts = counts;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}