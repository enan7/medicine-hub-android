package com.example.medic.Responses;

public class CategoryResponse {

    private String categoryName;
    private String  categoryIcon;
    private Long id;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getCategoryIcon() {
        return categoryIcon.getBytes();
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
