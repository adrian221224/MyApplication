package com.example.adria.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAgregarMiembro,btnMiembros, btnUsuarios, btnInicio;
    ListView lista;
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre;
    EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        btnMiembros = (Button) findViewById(R.id.btnMiembros);
        lista = (ListView) findViewById(R.id.listViewMiembros);
        btnUsuarios = (Button) findViewById(R.id.btnUsuarios);
        btnInicio = (Button) findViewById(R.id.btnInicio);

        //////////

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MainActivity.this, AgregarMiembro.class);
                startActivity(iagregar);
            }
        });
        btnUsuarios.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iusuario = new Intent(MainActivity.this, Registro.class);
                startActivity(iusuario);
            }
        });

        btnMiembros.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent miembros = new Intent(MainActivity.this, Miembros.class);
                startActivity(miembros);
            }
        });

        btnInicio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });
        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
        };
        int[] to = new int[] {
                R.id.miembro_id,
                R.id.miembro_nombre,
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);

                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModificarMiembro.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate
} //termina clase