package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menus")
public class Menu {

    @PrimaryKey
    @NonNull
    private String name;

    private int price;

    public Menu(@NonNull String name, int price) {
        this.name = name;
        this.price = price;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
