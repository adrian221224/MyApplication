package com.example.adria.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Miembros extends AppCompatActivity {
        ListView lista;
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre, tv_miemEdad, tv_miemTel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miembros);
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        lista = (ListView) findViewById(R.id.listViewMiembrosN);

        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
                DBhelper.MIEMBRO_EDAD,
                DBhelper.MIEMBRO_TEL,


        };
        int[] to = new int[] {
                R.id.miembro_id,
                R.id.miembro_nombre,
                R.id.miembro_edad,
                R.id.miembro_telefono,

        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Miembros.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);
                tv_miemEdad = (TextView) view.findViewById(R.id.miembro_edad);
                tv_miemTel = (TextView) view.findViewById(R.id.miembro_telefono);


                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();
                String aux_miembroEdad = tv_miemEdad.getText().toString();
                String aux_miembroTel = tv_miemTel.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), ModificarMiembro.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                modify_intent.putExtra("miembroEdad", aux_miembroEdad);
                modify_intent.putExtra("miembroTel", aux_miembroTel);

                startActivity(modify_intent);
            }
        });
    }

}
