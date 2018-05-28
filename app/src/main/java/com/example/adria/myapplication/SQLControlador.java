package com.example.adria.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name, String edad, String tel) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.MIEMBRO_NOMBRE, name);
        cv.put(DBhelper.MIEMBRO_EDAD, edad);
        cv.put(DBhelper.MIEMBRO_TEL, tel);
        database.insert(DBhelper.TABLE_MEMBER, null, cv);
    }
    public void insertarDatos2(String name, String place) {
        ContentValues cvs = new ContentValues();
        cvs.put(DBhelper.COLUMNS_NAME, name);
        cvs.put(DBhelper.COLUMNS_PLACE, place);
        database.insert(DBhelper.TABLE_GASTOS, null, cvs);
    }
    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
                DBhelper.MIEMBRO_EDAD,
                DBhelper.MIEMBRO_TEL
        };
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long memberID, String memberName,long memberEdad2, String memberEdad, String memberTel) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.MIEMBRO_NOMBRE, memberName);
        cvActualizar.put(DBhelper.MIEMBRO_EDAD, memberEdad);
        cvActualizar.put(DBhelper.MIEMBRO_TEL,memberTel);
        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.MIEMBRO_ID + "="
                + memberID, null);
    }
}