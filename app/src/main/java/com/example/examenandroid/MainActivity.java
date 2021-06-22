package com.example.examenandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IDAOCita dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = DAOCita.getInstance(getApplicationContext());

        Button btnEliminarDatos = findViewById(R.id.btnEliminarDatos);
        btnEliminarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Eliminar");
                dialogo1.setMessage("¿Desea eliminar todos las citas?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminarCitas();
                        Toast.makeText(MainActivity.this, "Citas eliminadas correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialogo1.show();            }
        });
        Button btnInsertar = findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Insertar.class);
                startActivity(intent);
            }
        });
        Button btnListado = findViewById(R.id.btnListado);
        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Listado.class);
                startActivity(intent);
            }
        });

        Button btnMostrarCitaDia = findViewById(R.id.btnMostrarCitaDia);
        btnMostrarCitaDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CitaDelDia.class);
                startActivity(intent);
            }
        });
    }
}