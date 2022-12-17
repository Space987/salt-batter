package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sqliteDB30.db";
    private static final int DB_VERSION = 3;

    ContentValues contentValues = new ContentValues();
    ContentValues contentValues2 = new ContentValues();
    ContentValues contentValues3 = new ContentValues();
    ContentValues contentValues4 = new ContentValues();
    ContentValues contentValues5 = new ContentValues();

    Bitmap fishAndChipsImage;
    Bitmap calamaryImage;
    Bitmap crabImage;
    Bitmap lobsterImage;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        fishAndChipsImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.fishandchipsorder);
        calamaryImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.calamaryorder);
        crabImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.craborder);
        lobsterImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.lobsterorder);

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String sqlData2 = "CREATE TABLE admin (id integer PRIMARY KEY AUTOINCREMENT, username varchar, password string);";
        db.execSQL(sqlData2);

        String sqlData = "CREATE TABLE user (id integer PRIMARY KEY AUTOINCREMENT, username varchar, password string, total integer, image_data KEY_IMAGE);";
        db.execSQL(sqlData);

        String sqlData3 = "CREATE TABLE food (id integer PRIMARY KEY AUTOINCREMENT, name varchar, description varchar ,cost integer, quantity integer, image_data KEY_IMAGE);";
        db.execSQL(sqlData3);

        String sqlData4 = "CREATE TABLE orderFood (id integer PRIMARY KEY AUTOINCREMENT, profile_id integer, food_id integer ,name varchar, cost integer, quantity integer);";
        db.execSQL(sqlData4);

        String sqlData5 = "CREATE TABLE orderHistory (id integer PRIMARY KEY AUTOINCREMENT, profile_id integer, food_id integer ,name varchar, cost integer, quantity integer, order_group integer);";
        db.execSQL(sqlData5);

        String sqlData6 = "CREATE TABLE messages (id integer PRIMARY KEY AUTOINCREMENT, profile_id integer, message varchar, email varchar);";
        db.execSQL(sqlData6);

        contentValues.put("username", "Boss");
        contentValues.put("password", 1234);
        db.insert("admin", null, contentValues);

        contentValues2.put("name", "Fish and Chips");
        contentValues2.put("cost", 20);
        contentValues2.put("description", "Fish and chips is a popular hot dish consisting of fried fish in crispy batter, served with chips. The dish originated in England.");
        contentValues2.put("quantity", 5);
        byte[] fishAndChipsImgData = getBitmapAsByteArray(fishAndChipsImage);
        contentValues2.put("image_data", fishAndChipsImgData);

        contentValues3.put("name", "Calamari");
        contentValues3.put("cost", 15);
        contentValues3.put("description", "Squid is eaten in many cuisines; in English, the culinary name calamari is often used for squid dishes. There are many ways to prepare and cook squid.");
        contentValues3.put("quantity", 8);
        byte[] calamaryImgData = getBitmapAsByteArray(calamaryImage);
        contentValues3.put("image_data", calamaryImgData);

        contentValues4.put("name", "Crab");
        contentValues4.put("cost", 40);
        contentValues4.put("description", "Crab meat or crab marrow is the meat found within a crab. It is used in many cuisines around the world, prized for its soft, delicate and sweet taste.");
        contentValues4.put("quantity", 2);
        byte[] crabImgData = getBitmapAsByteArray(crabImage);
        contentValues4.put("image_data", crabImgData);

        contentValues5.put("name", "Lobster");
        contentValues5.put("cost", 50);
        contentValues5.put("description", "Our premium Maine lobster meat for sale includes big chunks of tail, claw and knuckle lobster meat. Our 100% natural fresh- frozen lobster meat is ready to eat");
        contentValues5.put("quantity", 12);
        byte[] lobsterImageData = getBitmapAsByteArray(lobsterImage);
        contentValues5.put("image_data", lobsterImageData);

        db.insert("food", null, contentValues2);
        db.insert("food", null, contentValues3);
        db.insert("food", null, contentValues4);
        db.insert("food", null, contentValues5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlData = "DROP TABLE IF EXISTS user";
        db.execSQL(sqlData);
        String sqlData2 = "DROP TABLE IF EXISTS admin";
        db.execSQL(sqlData2);
        String sqlData3 = "DROP TABLE IF EXISTS food";
        db.execSQL(sqlData3);
        String sqlData4 = "DROP TABLE IF EXISTS orderFood";
        db.execSQL(sqlData4);
        String sqlData5 = "DROP TABLE IF EXISTS orderHistory";
        db.execSQL(sqlData5);
        onCreate(db);


    }

    public void createOrder(int profile_id, int food_id, String name, int cost, int quantity){
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put("profile_id", profile_id);
        contentValues.put("food_id", food_id);
        contentValues.put("name", name);
        contentValues.put("cost", cost);
        contentValues.put("quantity", quantity);
        db.insert("orderFood", null, contentValues);
        db.close();
    }

    public void createMessage(int profile_id, String message, String email){
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put("profile_id", profile_id);
        contentValues.put("message", message);
        contentValues.put("email", email);
        db.insert("messages", null, contentValues);
        db.close();
    }

    public List<Messages> getMessageSpecific(int id){
        SQLiteDatabase db = getWritableDatabase();
        List<Messages> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM messages WHERE profile_id ="+ id, null);
        Messages messages = new Messages();

        if (cursor.moveToFirst()) {
            do {

                list.add(messages = new Messages(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public List<Messages> getMessageAll(){
        SQLiteDatabase db = getWritableDatabase();
        List<Messages> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM messages", null);
        Messages messages = new Messages();

        if (cursor.moveToFirst()) {
            do {

                list.add(messages = new Messages(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public void updateOrder(int profile_id, int food_id, int quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", quantity);
        db.update("orderFood",
                contentValues,
                food_id + " = food_id AND " + profile_id + " = profile_id",
                null);

        db.close();
    }


    public void deleteOrder(int order_id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("orderFood", "id =" + order_id, null);
        db.close();
    }

    public List<OrderFood> getOrder(int id){
        SQLiteDatabase db = getWritableDatabase();
        List<OrderFood> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM orderFood WHERE profile_id ="+ id, null);
        OrderFood orderFood = new OrderFood();

        if (cursor.moveToFirst()) {
            do {

                list.add(orderFood = new OrderFood(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),cursor.getString(3),  Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5))));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public OrderFood getOrderSpecific(int profile_id, int food_id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orderFood WHERE profile_id ="+ profile_id + " AND "+ "food_id ="+ food_id, null);
        OrderFood orderFood = new OrderFood();

        if (cursor.moveToFirst()) {
            do {

                orderFood = new OrderFood(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),cursor.getString(3),  Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return orderFood;
    }

    public List<OrderFood> getOrderHistory(int id){
        SQLiteDatabase db = getWritableDatabase();
        List<OrderFood> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM orderHistory WHERE profile_id ="+ id, null);
        OrderFood orderHistory = new OrderFood();

        if (cursor.moveToFirst()) {
            do {

                list.add(orderHistory = new OrderFood(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),cursor.getString(3),  Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6))));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public List<OrderFood> getOrderHistoryAll(){
        SQLiteDatabase db = getWritableDatabase();
        List<OrderFood> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM orderHistory ", null);
        OrderFood orderHistory = new OrderFood();

        if (cursor.moveToFirst()) {
            do {

                list.add(orderHistory = new OrderFood(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),cursor.getString(3),  Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6))));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public void createOrderHistory(int profile_id, int food_id, String name, int cost, int quantity, int order_group){
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put("profile_id", profile_id);
        contentValues.put("food_id", food_id);
        contentValues.put("name", name);
        contentValues.put("cost", cost);
        contentValues.put("quantity", quantity);
        contentValues.put("order_group", order_group);
        db.insert("orderHistory", null, contentValues);
        db.close();
    }

    public boolean addData(String username, String password, byte[] image_data){
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("total", 0);
        contentValues.put("image_data", image_data);
        db.insert("user", null, contentValues);
        db.close();
        return true;
    }

    public boolean addTotal(int Id, int total) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", total);
        db.update("user", contentValues ,"id =" + Id, null);
        db.close();
        return true;
    }


    public List<User> getData(){
        SQLiteDatabase db = getWritableDatabase();
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        User user = new User();

        if (cursor.moveToFirst()) {
            do {

                list.add(user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1),  cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getBlob(4)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public User getDataSpecific(int user_id){
        SQLiteDatabase db = getWritableDatabase();
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id =" + user_id, null);
        User user = new User();

        if (cursor.moveToFirst()) {
            do {

                user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1),  cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getBlob(4));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return user;
    }

    public Admin getDataAdmin(){
        SQLiteDatabase db = getWritableDatabase();
        List<Admin> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM admin", null);
        Admin admin = new Admin();

        if (cursor.moveToFirst()) {
            do {

                admin = new Admin(Integer.parseInt(cursor.getString(0)), cursor.getString(1),  cursor.getString(2));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return admin;
    }

    public void removeTotal(int Id, int total){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("total", total);
        db.update("user", contentValues ,"id =" + Id, null);
        db.close();
    }

    public List<Food> getDataFood(){
        SQLiteDatabase db = getWritableDatabase();
        List<Food> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM food", null);
        Food food = new Food();

        if (cursor.moveToFirst()) {
            do {

                list.add(food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getBlob(5)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public byte[] getDataFoodImage(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE id ="+ id, null);
        byte[] image = null;
        if (cursor.moveToFirst()) {
            do {

                image = cursor.getBlob(5);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return image;
    }

    public byte[] getUserFoodImage(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id ="+ id, null);
        byte[] image = null;
        if (cursor.moveToFirst()) {
            do {

                image = cursor.getBlob(4);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return image;
    }

    public void subtractQuantity(int Id, int quantity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", quantity);
        db.update("food", contentValues ,"id =" + Id, null);
        db.close();
    }

    public void deleteFood(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("food", "id =" + id, null);
        db.close();
    }

    public void deleteData(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("user", "id =" + id, null);
        db.close();
    }



    public boolean updateData(int Id, String username, String password, byte[] imgData) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("image_data", imgData);
        db.update("user", contentValues ,"id =" + Id, null);
        db.close();
        return true;
    }

    ////////////////////ADMIN//////////////////////////////////////


    public boolean addFoodAdmin(String name, String description,int cost, int quantity, byte[] imgData){
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("cost", cost);
        contentValues.put("quantity", quantity);
        contentValues.put("image_data", imgData);
        db.insert("food", null, contentValues);
        db.close();
        return true;
    }

    public boolean updateFood(int Id, String name, String description,int cost, int quantity, byte[] imgData) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("cost", cost);
        contentValues.put("quantity", quantity);
        contentValues.put("image_data", imgData);
        db.update("food", contentValues ,"id =" + Id, null);
        db.close();
        return true;
    }

    public Food getDataFoodSpecific(int food_id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE id="+food_id , null);
        Food food = new Food();

        if (cursor.moveToFirst()) {
            do {

                food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1),  cursor.getString(2),Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getBlob(5));
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return food;
    }

}

