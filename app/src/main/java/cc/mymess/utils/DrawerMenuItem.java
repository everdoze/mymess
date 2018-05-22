package cc.mymess.utils;

import android.support.annotation.Nullable;

public class DrawerMenuItem {

    private int imageId;
    private int bigImageId;
    private String name;
    private String addional;
    private String action;

    public DrawerMenuItem(int imageId, int bigImageId, String name, @Nullable String addional, @Nullable String action){
        this.imageId = imageId;
        this.bigImageId = bigImageId;
        this.name = name;
        this.addional = addional;
        this.action = action;
    }

//    public DrawerMenuItem(int imageId, int bigImageId, String name){
//        this.imageId = imageId;
//        this.bigImageId = bigImageId;
//        this.name = name;
//    }

    public String getAction() { return action; }

    public int getImageId() {
        return imageId;
    }

    public int getBigImageId() {
        return bigImageId;
    }

    public String getAddional() {
        return addional;
    }

    public String getName() {
        return name;
    }

}
