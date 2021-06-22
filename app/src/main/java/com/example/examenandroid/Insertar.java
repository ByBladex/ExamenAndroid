package com.example.examenandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Insertar extends AppCompatActivity {
    private IDAOCita dao = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        dao = DAOCita.getInstance(getApplicationContext());


        EditText txtCodigo = findViewById(R.id.editTextCodigo);
        EditText txtCita = findViewById(R.id.editTextCita);
        EditText txtAutor = findViewById(R.id.editTextAutor);
        EditText txtValoracion = findViewById(R.id.editTextValoracion);

        Button btnInsertarCita = findViewById(R.id.btnInsertarCita);
        btnInsertarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtCodigo.getText().toString().isEmpty() && !txtCita.getText().toString().isEmpty() && !txtAutor.getText().toString().isEmpty() && !txtValoracion.getText().toString().isEmpty()){
                    if(dao.insertarCita(new Cita(Integer.parseInt(txtCodigo.getText().toString()),txtCita.getText().toString(),txtAutor.getText().toString(), 0,txtValoracion.getText().toString())) == 1){
                        Toast.makeText(Insertar.this, "Cita añadida correctamente", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Insertar.this, "Error. No se pudo añadir la cita", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Insertar.this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
