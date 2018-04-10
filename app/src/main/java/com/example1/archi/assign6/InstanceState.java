package com.example1.archi.assign6;

import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class InstanceState implements Serializable {
    Set<String> categorySet;
    List<Artical> articalList;
    String lastCatSelected="ALL";

    public Set<String> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<String> categorySet) {
        this.categorySet = categorySet;
    }

    public List<Artical> getArticalList() {
        return articalList;
    }

    public void setArticalList(List<Artical> articalList) {
        this.articalList = articalList;
    }

    public String getLastCatSelected() {
        return lastCatSelected;
    }

    public void setLastCatSelected(String lastCatSelected) {
        this.lastCatSelected = lastCatSelected;
    }
}
