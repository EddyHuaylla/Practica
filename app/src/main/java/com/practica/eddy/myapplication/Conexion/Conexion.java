package com.practica.eddy.myapplication.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eddy on 30/07/2017.
 */

public class Conexion extends SQLiteOpenHelper{
    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "db_prac", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLA="create table tb_persona(dni text primary key,nombre text,apellido text,edad number,sexo text);";
        db.execSQL(TABLA);
        db.execSQL("insert into tb_persona values('87628394','juan','sanchez',23,'Varon'");
        db.execSQL("insert into tb_persona values('84523494','juana','solano',21,'Mujer'");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
