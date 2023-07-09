package com.example.healthcaresystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "create table users(username text,email text,password text)";
        db.execSQL(createTableStatement);

        String query1 = "create table cart(username text,product text,price float,orderType text)";
        db.execSQL(query1);

        String query2 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,orderType text)";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select*from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            return 1;
        }
        return result;
    }

    public void addCart(String username, String product, Float price, String orderType) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("orderType", orderType);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    //checks if the item exists in the cart
    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        //if item exist in the cart return 1 otherwise 0
        Cursor c = db.rawQuery("select*from cart where username=? and product=?", str);
        if (c.moveToFirst()) {
            return 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String orderType) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = orderType;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and orderType=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String orderType) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = orderType;
        Cursor c = db.rawQuery("select*from cart where username=? and orderType=?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode,
                         String date, String time, float price, String orderType) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contact", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("orderType", orderType);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderTable", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select*from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                //concatenate all columns into one string and add t to the array
                arr.add(c.getString(1) + "$" + c.getString(2) + "$"
                        + c.getString(3) + "$" + c.getString(4) + "$" +
                        c.getString(5) + "$" + c.getString(6) + "$" +
                        c.getString(7) + "$" + c.getString(8));

            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }
}
