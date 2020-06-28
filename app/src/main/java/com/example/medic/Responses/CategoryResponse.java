package com.example.medic.Responses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class CategoryResponse {

    private String categoryName;
    private String categoryIcon;
    private Long id;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        if (categoryIcon != null) {
            byte[] decodedString = Base64.decode(categoryIcon, Base64.DEFAULT);
            String newDecodedImageUri = new String(decodedString, StandardCharsets.UTF_8);
            String base64Image = newDecodedImageUri.split(",")[1];
            return base64Image;
        }
        return null;
    }


    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
