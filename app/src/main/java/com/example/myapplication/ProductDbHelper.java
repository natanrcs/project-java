package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProductDbHelper extends SQLiteOpenHelper {

    public ProductDbHelper(Context context, String databaseName, int version) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS products (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "price REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertProduct(Product product) {

        ContentValues values = new ContentValues();

        values.put("name", product.name);
        values.put("price", product.price);

        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert("products", null, values);

        return result != -1;
    }

    public ArrayList<Product> listProducts() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                "products",
                new String[]{"id", "name", "price"},
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Product> products = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);

            products.add(new Product(id, name, price));
        }

        cursor.close();

        return products;
    }
}