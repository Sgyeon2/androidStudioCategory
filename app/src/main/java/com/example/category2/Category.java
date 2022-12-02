package com.example.category2;

public class Category {
    private String imageUri;
    private String cType;
    private String cColor;
    private String cSeason;

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcColor() {
        return cColor;
    }

    public void setcColor(String cColor) {
        this.cColor = cColor;
    }

    public String getcSeason() {
        return cSeason;
    }

    public void setcSeason(String cSeason) {
        this.cSeason = cSeason;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
