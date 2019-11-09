package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo.Cart;

@Entity(tableName = "orders")
public class Order {

    @NonNull
    @PrimaryKey
    private int id;

    private String date;

    private String menus;

    private String prices;

    private String counts;

    private String address;

    private boolean done;

    public Order(int id, String date, String menus, String prices, String counts, String address, boolean done) {
        this.id = id;
        this.date = date;
        this.menus = menus;
        this.prices = prices;
        this.counts = counts;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        String[] prices = this.prices.split(";");
        int[] pricesInt = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            pricesInt[i] = Integer.parseInt(prices[i]);
        }

        String[] counts = this.counts.split(";");
        int[] countsInt = new int[counts.length];
        for (int i = 0; i < counts.length; i++) {
            countsInt[i] = Integer.parseInt(counts[i]);
        }

        int sum = 0;
        for (int i = 0; i < pricesInt.length; i++) {
            sum += (pricesInt[i] * countsInt[i]);
        }
        return sum;
    }

    public List<Cart> getOrderCarts() {
        String[] menus = this.menus.split(";");

        String[] prices = this.prices.split(";");
        int[] pricesInt = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            pricesInt[i] = Integer.parseInt(prices[i]);
        }

        String[] counts = this.counts.split(";");
        int[] countsInt = new int[counts.length];
        for (int i = 0; i < counts.length; i++) {
            countsInt[i] = Integer.parseInt(counts[i]);
        }

        List<Cart> cartList = new ArrayList<>();

        for (int i = 0; i < menus.length; i++) {
            Menu menu = new Menu(menus[i], pricesInt[i], "");
            Cart cart = new Cart(menu, countsInt[i]);
            cartList.add(cart);
        }

        return cartList;
    }
}
