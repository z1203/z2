package com.bwie.mvvm;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by 张丹阳 on 2017/10/9.
 */

public class User {
    private String name;
    private int age;
    private String image;

    public User() {
    }

    public User(String name, int age, String image) {
        this.name = name;
        this.age = age;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @BindingAdapter("bind:image")
    public static void getInternetImage(ImageView iv,String image){
        Picasso.with(iv.getContext()).load(image).into(iv);
    }

}
