package com.example.adria.myapplication;

import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.adria.myapplication.SQLControlador;

public class AgregarMiembro extends AppCompatActivity implements OnClickListener {
    EditText et, et2, et3;
    Button btnAgregar, read_bt;
    ImageButton btnAgregar2;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_miembro);
        et = (EditText) findViewById(R.id.et_miembro_id);
        et2 = (EditText) findViewById(R.id.et_edad_id);
        et3 = (EditText) findViewById(R.id.et_telefono_id);
        btnAgregar = (Button) findViewById(R.id.btnAgregarId);
        btnAgregar2 = (ImageButton) findViewById(R.id.btnAgregarId2);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregar.setOnClickListener(this);
        btnAgregar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAgregarId2:
                String name = et.getText().toString();
                String edad = et2.getText().toString();
                String tel = et3.getText().toString();
                if(name.isEmpty()|| edad.isEmpty() || tel.isEmpty()){

                }
                else {
                    dbconeccion.insertarDatos(name,edad,tel);

                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                }
                if (name.isEmpty()){
                    Toast.makeText(this, "El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
                }
                if (edad.isEmpty()){
                    Toast.makeText(this , "El campo edad esta vacio", Toast.LENGTH_SHORT).show();

                }
                if (tel.isEmpty()){
                    Toast.makeText(this, "El campo Telefono esta vacio", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent main = new Intent(AgregarMiembro.this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(main);
                    break;
                }
            default:
                break;
        }
    }

}