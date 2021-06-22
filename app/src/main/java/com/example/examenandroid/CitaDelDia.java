package com.example.examenandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CitaDelDia extends AppCompatActivity {
    private IDAOCita dao = null;
    private Cita citaDelDia;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citadeldia);
        dao = DAOCita.getInstance(getApplicationContext());


        TextView txtCodigo = findViewById(R.id.txtCodigo);
        TextView txtCita = findViewById(R.id.txtCita);
        TextView txtAutor = findViewById(R.id.txtAutor);
        TextView txtValoracion = findViewById(R.id.txtValoracion);

        citaDelDia = dao.getCitaMenosVista();

        Integer numVeces = (citaDelDia.getNumVeces())+1;
        if(dao.aumentarVistaCita(citaDelDia.getCodigo(), numVeces)==1)
            Toast.makeText(CitaDelDia.this, "Nueva vista a la cita del dia", Toast.LENGTH_SHORT).show();

        txtCodigo.setText(citaDelDia.getCodigo().toString());
        txtCita.setText(citaDelDia.getCita());
        txtAutor.setText(citaDelDia.getAutor());
        txtValoracion.setText(citaDelDia.getValoracion());

        Button btnMuyBuena = findViewById(R.id.btnMuyBuena);
        btnMuyBuena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.modificarValoracionCita(citaDelDia.getCodigo(),"Muy Buena") == 1){
                    Toast.makeText(CitaDelDia.this, "Cita del dia valorada como: Muy Buena", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnBuena = findViewById(R.id.btnBuena);
        btnBuena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.modificarValoracionCita(citaDelDia.getCodigo(),"Buena") == 1){
                    Toast.makeText(CitaDelDia.this, "Cita del dia valorada como: Buena", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnRegular = findViewById(R.id.btnRegular);
        btnRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.modificarValoracionCita(citaDelDia.getCodigo(),"Regular") == 1){
                    Toast.makeText(CitaDelDia.this, "Cita del dia valorada como: Regular", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnMala = findViewById(R.id.btnMala);
        btnMala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.modificarValoracionCita(citaDelDia.getCodigo(),"Mala") == 1){
                    Toast.makeText(CitaDelDia.this, "Cita del dia valorada como: Mala", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
