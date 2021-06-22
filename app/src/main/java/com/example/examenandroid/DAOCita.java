package com.example.examenandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOCita implements IDAOCita{

    private static IDAOCita dao = null;
    private ArrayList<Cita> listaCitas;
    private DatosSQLiteHelper conn;
    private SQLiteDatabase db;

    DAOCita(Context context){
        listaCitas = new ArrayList<Cita>();
        conn = new DatosSQLiteHelper(context);

        rellenarLista();
    }

    public int rellenarLista(){
        try{
            db = conn.getWritableDatabase();
            listaCitas.clear();
            if(db != null){
                Cursor c = db.rawQuery("SELECT * FROM citas", null);
                if(c.moveToFirst()){
                    do{
                        listaCitas.add(new Cita(c.getInt(0),c.getString(1),c.getString(2), c.getInt(3),c.getString(4)));
                    }
                    while (c.moveToNext());
                }
                c.close();
                db.close();
                return 1;
            }
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int insertarCita(Cita cita) {
        try{
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("INSERT INTO citas (codigo, cita, autor, numVeces, valoracion) VALUES ("+cita.getCodigo()+",'"+cita.getCita()+"','"+cita.getAutor()+"','"+cita.getNumVeces()+"','"+cita.getValoracion()+"')");
                rellenarLista();
                db.close();
                return 1;
            }
            else
                return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int eliminarCita(Cita cita) {
        try{
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("DELETE FROM citas WHERE codigo='"+cita.getCodigo()+"'");
                db.close();
                rellenarLista();
                return 1;
            }
            rellenarLista();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }
    @Override
    public int eliminarCitas(){
        try{
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("DELETE FROM citas");
                db.execSQL("INSERT INTO citas (codigo, cita, autor, numVeces, valoracion) VALUES (1,'Primeracita','Yo',0,'Buena'),(2,'Segundacita','Tu',0,'Buena'),(3,'Terceracita','El',0,'Buena'),(4,'Cuartacita','Nosotros',0,'Buena'),(5,'Quintacita','Vosotros',0,'Buena')");
                db.close();
                rellenarLista();
                return 1;
            }
            rellenarLista();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int modificarCita(Integer codigo, String nuevacita, String autor, String valoracion) {
        try{
            Cita cita = getCita(codigo);
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("UPDATE citas SET codigo='"+codigo+"',cita='"+nuevacita+"',autor='"+autor+"' WHERE codigo='"+cita.getCodigo()+"'");
                db.close();
                rellenarLista();
                return 1;
            }
            rellenarLista();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public ArrayList<Cita> getCitas() {
        return listaCitas;
    }

    @Override
    public Cita getCita(Integer pos) {
        return listaCitas.get(pos);
    }

    public ArrayList<String> getCitasToString(){
        ArrayList<String> cadena = new ArrayList<>();
        for (Cita cita:listaCitas) {
            cadena.add(cita.getCita());
        }
        return cadena;
    }

    @Override
    public int modificarValoracionCita(Integer codigo, String valoracion) {
        try{
            Cita cita = getCita(codigo);
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("UPDATE citas SET valoracion='"+valoracion+"' WHERE codigo='"+cita.getCodigo()+"'");
                db.close();
                rellenarLista();
                return 1;
            }
            rellenarLista();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int aumentarVistaCita(Integer codigo, Integer numVeces) {
        try{
            Cita cita = getCita(codigo);
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("UPDATE citas SET numVeces='"+numVeces+"' WHERE codigo='"+cita.getCodigo()+"'");
                db.close();
                rellenarLista();
                return 1;
            }
            rellenarLista();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }


    public Cita getCitaMenosVista(){
        int randomIndex = (int) (Math.random() * listaCitas.size());
        Cita citaMenosVista = listaCitas.get(0);
        for (Cita cita:listaCitas) {
            if((cita.getNumVeces().equals(citaMenosVista.getNumVeces()))){
                if (cita.getValoracion().equalsIgnoreCase("Muy Buena") &&
                        (citaMenosVista.getValoracion().equalsIgnoreCase("Buena") ||
                                citaMenosVista.getValoracion().equalsIgnoreCase("Regular") ||
                                citaMenosVista.getValoracion().equalsIgnoreCase("Mala"))) {
                    citaMenosVista=cita;
                }
                else if(cita.getValoracion().equalsIgnoreCase("Buena") &&
                        (citaMenosVista.getValoracion().equalsIgnoreCase("Regular") ||
                                citaMenosVista.getValoracion().equalsIgnoreCase("Mala"))){
                    citaMenosVista = cita;
                }
                else if(cita.getValoracion().equalsIgnoreCase("Regular") && citaMenosVista.getValoracion().equalsIgnoreCase("Mala"))
                    citaMenosVista=cita;
            }
            else{
                citaMenosVista = cita;
            }
            return citaMenosVista;
        }
        return listaCitas.get(randomIndex);
    }

    public static IDAOCita getInstance(Context context) {
        if(dao==null)
            dao = new DAOCita(context);
        return dao;
    }
}
