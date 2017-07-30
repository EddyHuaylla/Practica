package com.practica.eddy.myapplication.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.practica.eddy.myapplication.Beans.PersonaBean;
import com.practica.eddy.myapplication.Conexion.Conexion;

import java.util.ArrayList;

/**
 * Created by eddy on 30/07/2017.
 */

public class PersonaDAO {
    Context mContext;

    public PersonaDAO(Context context){
        mContext=context;
    }

    private PersonaBean cursorApersona(Cursor cursor){
        PersonaBean personaBean=new PersonaBean();
        personaBean.setDni(cursor.getString(0));
        personaBean.setNombre(cursor.getString(1));
        personaBean.setApellido(cursor.getString(2));
        personaBean.setEdad(Integer.parseInt(cursor.getString(3)));
        personaBean.setSexo(cursor.getString(4));
        return personaBean;
    }

    public ArrayList<PersonaBean> listado(){
        ArrayList<PersonaBean> lista=new ArrayList<>();
        Conexion conexion=new Conexion(mContext,null,null,1);
        SQLiteDatabase sqLiteDatabase=conexion.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tb_persona",new String[]{});
        while (cursor.moveToNext()){
            PersonaBean personaBean=cursorApersona(cursor);
            lista.add(personaBean);
        }
        return lista;
    }

    public int agregar(PersonaBean personaBean){
        int result;
        try {
            Conexion conexion=new Conexion(mContext,null,null,1);
            SQLiteDatabase sqLiteDatabase=conexion.getWritableDatabase();
            sqLiteDatabase.execSQL("insert into tb_persona values(?,?,?,?,?)",new String[]{personaBean.getDni(),personaBean.getNombre(),personaBean.getApellido(), String.valueOf(personaBean.getEdad()),personaBean.getSexo()});
            result=1;
        }
        catch (Exception e){
            e.printStackTrace();
            result=-1;
        }
        return result;
    }

    public int actualizar(PersonaBean personaBean){
        int result;
        try {
            Conexion conexion=new Conexion(mContext,null,null,1);
            SQLiteDatabase sqLiteDatabase=conexion.getWritableDatabase();
            sqLiteDatabase.execSQL("update tb_persona set nombre=?,apellido=?,edad=?,sexo=? where dni=?",new String[]{personaBean.getNombre(),personaBean.getApellido(), String.valueOf(personaBean.getEdad()),personaBean.getSexo(),personaBean.getDni()});
            result=1;
        }
        catch (Exception e){
            e.printStackTrace();
            result=-1;
        }
        return result;
    }

    public int eliminar(String dni){
        int result;
        try {
            Conexion conexion=new Conexion(mContext,null,null,1);
            SQLiteDatabase sqLiteDatabase=conexion.getWritableDatabase();
            sqLiteDatabase.execSQL("delete from tb_persona where dni=?",new String[]{dni});
            result=1;
        }
        catch (Exception e){
            e.printStackTrace();
            result=-1;
        }
        return result;
    }

    public ArrayList<PersonaBean> listadoXsexo(String sexo){
        ArrayList<PersonaBean> lista=new ArrayList<>();
        Conexion conexion=new Conexion(mContext,null,null,1);
        SQLiteDatabase sqLiteDatabase=conexion.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tb_persona where sexo=?",new String[]{sexo});
        while (cursor.moveToNext()){
            PersonaBean personaBean=cursorApersona(cursor);
            lista.add(personaBean);
        }
        return lista;
    }

    public PersonaBean personaXdni(String dni){
        PersonaBean personaBean=null;
        Conexion conexion=new Conexion(mContext,null,null,1);
        SQLiteDatabase sqLiteDatabase=conexion.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tb_persona where dni=?",new String[]{dni});
        while (cursor.moveToNext()) {
            personaBean = cursorApersona(cursor);
        }
        return personaBean;
    }
}
