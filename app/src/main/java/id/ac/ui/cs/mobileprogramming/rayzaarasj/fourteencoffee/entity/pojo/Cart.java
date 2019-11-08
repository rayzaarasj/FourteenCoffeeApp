package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.pojo;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.entity.Menu;

public class Cart {

    private Menu menu;
    private int count;

    public Cart(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add() {
        this.count += 1;
    }

    public void minus() {
        if (this.count > 0) {
            this.count -= 1;
        }
    }
}
