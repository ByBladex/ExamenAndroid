package com.example.examenandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatosSQLiteHelper extends SQLiteOpenHelper {

    final String SQLCREATE ="CREATE TABLE citas (codigo INT PRIMARY KEY, cita TEXT, autor TEXT, numVeces INT, valoracion TEXT)";

    private static final String DATABASE_NOMBRE = "db_Examen";
    private static final int DATABASE_VERSION = 1;

    public DatosSQLiteHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);
        db.execSQL("INSERT INTO citas (codigo, cita, autor, numVeces, valoracion) VALUES (1,'Primeracita','Yo',0,'Buena'),(2,'Segundacita','Tu',0,'Buena'),(3,'Terceracita','El',0,'Buena'),(4,'Cuartacita','Nosotros',0,'Buena'),(5,'Quintacita','Vosotros',0,'Buena')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS citas");
        onCreate(db);
    }
}
