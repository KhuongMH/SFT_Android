package com.example.khuongman.sft_android.Classes;

public class LayoutIDWithTitle {
    private int id;
    private String title;

    public LayoutIDWithTitle() {
    }

    public LayoutIDWithTitle(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public LayoutIDWithTitle setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LayoutIDWithTitle setTitle(String title) {
        this.title = title;
        return this;
    }
}
