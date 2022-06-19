package com.gyan.ecommerce.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gyan.ecommerce.Model.LocalDataModel;

import java.util.ArrayList;
import java.util.List;

public class SqLiteHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Cart_Database";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_USERS="Cart";
    public static final String ID="ID";
    public static final String NAME="name";
    public static final String FINAL_PRICE="final_price";
    public static final String IMAGE_PRODUCT="img_product";
    public static final String QTY="qty";

    public SqLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" + ID + " TEXT," + NAME + " TEXT," + FINAL_PRICE + " TEXT," + IMAGE_PRODUCT + " TEXT," + QTY + " TEXT" + ")";
        db.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        onCreate(db);
    }

    //Data insert into Database
    public void addData(LocalDataModel model) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,model.getID());
        values.put(NAME,model.getName());
        values.put(FINAL_PRICE,model.getFinal_price());
        values.put(IMAGE_PRODUCT,model.getImg_product());
        values.put(QTY,model.getQty());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    //Data get from database
    public List<LocalDataModel> getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<LocalDataModel> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_USERS,null);
        while (cursor.moveToNext()){
            LocalDataModel user = new LocalDataModel();
            user.setID(cursor.getString(cursor.getColumnIndex(ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setFinal_price(cursor.getString(cursor.getColumnIndex(FINAL_PRICE)));
            user.setImg_product(cursor.getString(cursor.getColumnIndex(IMAGE_PRODUCT)));
            user.setQty(cursor.getString(cursor.getColumnIndex(QTY)));
            userList.add(user);
        }
        return  userList;
    }

    //update data
    public void updateQty(String qty, String Id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(QTY,qty);
        db.update(TABLE_USERS,values,"ID=?", new String[]{Id});
        db.close();
    }

    //delete data
    public void deleteData(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, ID + " = ?", new String[] {Id});
        db.close();
    }
}
