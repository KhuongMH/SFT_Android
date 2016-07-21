package com.example.khuongman.sft_android.Classes;

/**
 * Created by khuong.man on 7/20/2016.
 */
public class Food {
    private String name;
    private String image;
    private String price;
    private int difPrice;
    private boolean isNew;

    public Food() {
        name = "";
        image = "";
        price = "";
        difPrice = 0;
        isNew = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDifPrice() {
        return difPrice;
    }

    public void setDifPrice(int difPrice) {
        this.difPrice = difPrice;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
