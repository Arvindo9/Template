package com.aiprog.template.data.model.db.notification;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

/**
 * Author : Arvindo Mondal
 * Email : arvindomondal@gmail.com
 * Created on : 1/23/2019
 * Company : Roundpay Techno Media OPC Pvt Ltd
 * Designation : Programmer and Developer
 */
@Entity(tableName = "notification")
public class Notification {

    @Expose
    @PrimaryKey(autoGenerate = true)
    public Long _id;

    @ColumnInfo(name = "MenuId")
    private String MenuId;

    @ColumnInfo(name = "Image")
    private String Image;

    @ColumnInfo(name = "Url")
    private String Url;

    @ColumnInfo(name = "JobTitle")
    private String JobTitle;

    @ColumnInfo(name = "Key")
    private String Key;

    @ColumnInfo(name = "Date")
    private String Date;

    @ColumnInfo(name = "Menu")
    private String Menu;

    @ColumnInfo(name = "Type")
    private String Type;

    public Notification(){

    }

    @Ignore
    public Notification(String menuId, String image, String url, String jobTitle,
                        String key, String date, String menu, String type) {
        MenuId = menuId;
        Image = image;
        Url = url;
        JobTitle = jobTitle;
        Key = key;
        Date = date;
        Menu = menu;
        Type = type;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        Menu = menu;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
