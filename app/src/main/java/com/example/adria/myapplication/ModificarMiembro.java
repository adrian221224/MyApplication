package com.example.adria.myapplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarMiembro extends Activity implements OnClickListener {

    EditText et, et2 ,et3;
    Button btnActualizar, btnEliminar;

    long member_id, member_edad, member_tel;

    SQLControlador dbcon;
    public void aceptar() {
        dbcon.deleteData(member_id);
        Toast.makeText(ModificarMiembro.this, "Eliminaste a un miembro", Toast.LENGTH_SHORT).show();
        this.returnHome();
    }

    public void cancelar() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_miembro);

        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        et = (EditText) findViewById(R.id.et_miembro_id);
        et2 = (EditText) findViewById(R.id.et_miembro_edad);
        et3= (EditText)findViewById(R.id.et_miembro_tel);


        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");
        String memberEdad2 = i.getStringExtra("miembroEdad");
        String memberTel2 = i.getStringExtra("miembroTel");

        member_id = Long.parseLong(memberID);
        member_edad = Long.parseLong(memberEdad2);
        member_tel = Long.parseLong(memberTel2);



        et.setText(memberName);
        et2.setText(memberEdad2);
        et3.setText(memberTel2);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnActualizar:
                String memName_upd = et.getText().toString();
                String memberEdad_upd = et2.getText().toString();
                String memberTel_upd = et3.getText().toString();

                dbcon.actualizarDatos(member_id, memName_upd,member_edad, memberEdad_upd, memberTel_upd);
                Toast.makeText(this, "Actualizaste a un miembro", Toast.LENGTH_SHORT).show();
                this.returnHome();
                break;

            case R.id.btnEliminar:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Acepta la ejecución de este programa en modo prueba ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                    dialogo1.show();


                    break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}