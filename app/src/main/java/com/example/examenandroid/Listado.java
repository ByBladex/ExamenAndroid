package com.example.examenandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Listado extends AppCompatActivity {
    private IDAOCita dao = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        dao = DAOCita.getInstance(getApplicationContext());

        ListView listViewListado = findViewById(R.id.listViewListado);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dao.getCitasToString());
        listViewListado.setAdapter(adapter);
        listViewListado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int posicion = position;
                Cita cita = dao.getCita(posicion);
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Listado.this);
                dialogo1.setTitle("Datos de la cita");
                dialogo1.setMessage("Codigo: "+cita.getCodigo()+"\nCita: "+cita.getCita()+"\nAutor: "+cita.getAutor()+"\nNumero veces: "+cita.getNumVeces()+"\nValoracion: "+cita.getValoracion());
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }
}
