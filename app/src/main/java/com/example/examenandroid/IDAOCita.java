package com.example.examenandroid;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public interface IDAOCita {

    public int insertarCita(Cita cita);
    public int eliminarCita(Cita cita);
    public int eliminarCitas();
    public int modificarCita(Integer codigo, String cita, String autor, String valoracion);
    public List<Cita> getCitas();
    public Cita getCita(Integer codigo);
    public Cita getCitaMenosVista();
    public ArrayList<String> getCitasToString();
    public int modificarValoracionCita(Integer codigo,String valoracion);
    public int aumentarVistaCita(Integer codigo, Integer numVeces);
}
