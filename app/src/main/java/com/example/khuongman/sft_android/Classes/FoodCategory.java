package com.example.khuongman.sft_android.Classes;

import java.util.ArrayList;
import java.util.List;

public class FoodCategory {
    private String title;
    private String url;
    private List<Food> foodList;

    public FoodCategory() {
        title = "";
        url = "";
        foodList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
