package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses")
public class Address {

    @PrimaryKey
    @NonNull
    private String address;

    public Address(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }
}
