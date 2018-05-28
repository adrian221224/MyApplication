package com.example.adria.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.sql.Statement;

public class DBhelper extends SQLiteOpenHelper {

    // Información de la tabla
    public static final String TABLE_MEMBER = "miembros";
    public static final String MIEMBRO_ID = "_id";
    public static final String MIEMBRO_NOMBRE = "nombre";
    public static final String MIEMBRO_EDAD = "edad";
    public static final String MIEMBRO_TEL = "tel";

    public static final String TABLE_USUARIO = "usuario";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASS= "pass";

    public static final String TABLE_GASTOS = "gastos";
    public static final String COLUMNS_ID = "id";
    public static final String COLUMNS_NAME = "costo";
    public static final String COLUMNS_PLACE= "lugar";

    private SQLiteDatabase database;

    // información del a base de datos
    static final String DB_NAME = "DBMIEMBRO";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table "
            + TABLE_MEMBER + "("
            + MIEMBRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MIEMBRO_NOMBRE + " TEXT NOT NULL, "
            + MIEMBRO_EDAD + " INTEGER NOT NULL, "
            + MIEMBRO_TEL + " TEXT NOT NULL )";
    private static final String CREATE_TABLE2 = "create table "
            + TABLE_USUARIO + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_PASS + " TEXT NOT NULL )";
    private static final String CREATE_TABLE3 = "create table "
            + TABLE_GASTOS + "("
            + COLUMNS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMNS_NAME + " TEXT NOT NULL, "
            + COLUMNS_PLACE + " TEXT NOT NULL )";



    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
    }


    public void insertContact(Contact c){

        database= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query =" select * from usuario";
        Cursor cursor = database.rawQuery(query, null);
        int count = cursor.getCount();

        String querys =" select NAME from usuario where NAME ="+c.getName();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , c.getName());
        values.put(COLUMN_PASS , c.getPass());

        database.insert(TABLE_USUARIO , null , values);
        database.close();

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS);
        onCreate(db);
    }

    public String searchPass(String username){
        database = this.getReadableDatabase();
        String query = "select name , pass from "+TABLE_USUARIO;
        Cursor cursor = database.rawQuery(query , null);
        String a,b;
        b = "Not Found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(username)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

}