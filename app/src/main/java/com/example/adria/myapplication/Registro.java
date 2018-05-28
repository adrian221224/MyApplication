package com.example.adria.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    DBhelper helper = new DBhelper(this);

    ImageButton btnsign;
    EditText nombre,email,username,pass1,pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        bindViews();

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = nombre.getText().toString();
                String passw1 = pass1.getText().toString();

                    Contact c = new Contact();
                    c.setName(user1);
                    c.setPass(passw1);

                    helper.insertContact(c);
                    Toast mensaje = Toast.makeText(Registro.this, "Registrado", Toast.LENGTH_SHORT);
                    mensaje.show();
                    Intent iregresar = new Intent(Registro.this, MainActivity.class);
                    startActivity(iregresar);


            }

        });

    }




    private void bindViews(){
        nombre = (EditText)findViewById(R.id.txtUsuario);
        pass1 = (EditText)findViewById(R.id.txtPass);
        btnsign = (ImageButton)findViewById(R.id.btnAgregar);
    }
}

