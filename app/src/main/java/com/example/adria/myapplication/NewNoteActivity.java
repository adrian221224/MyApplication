package com.example.adria.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.actions.NoteIntents;

import com.google.android.gms.appindexing.Action;

import com.google.android.gms.appindexing.AppIndex;

import com.google.android.gms.appindexing.Thing;

import com.google.android.gms.common.api.GoogleApiClient;

public class NewNoteActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    String str;
    TextView texto1,names;
    EditText edit, editx;
    EditText et, et2;
    Button btn;
    SQLControlador dbconeccion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textV = (TextView)findViewById(R.id.textView);
        TextView textV2 = (TextView)findViewById(R.id.textView2);
        EditText edit = (EditText) findViewById(R.id.editText2);
        EditText edit2 = (EditText) findViewById(R.id.editText);
        Button btn = (Button)findViewById(R.id.button2);
        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d(TAG, String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }


        String texto1 = getIntent().getStringExtra("android.intent.extra.SUBJECT");
        // textob.setText(texto1);
        String texto = getIntent().getStringExtra("android.intent.extra.TEXT");
        //textoa.setText(texto);
        String texto2 = getIntent().getStringExtra("com.google.android.gms.action.EXTRA_COMPLETION_TOKEN");
        //textoc.setText(texto2);



        String s1= texto;
        String replaceString=s1.replaceAll("[^\\d]","");
        String textodf= replaceString;
        edit.setText(textodf);

        String replaceString3=textodf.replaceAll("[a-zA-Z]","");
        String textonuevo= replaceString3;
        //textof.setText(textonuevo);

        String s12= texto;
        String replaceString2=s12.replaceAll("[^\\s^a-zA-Z]","");
        String textodff= replaceString2;
        // textog.setText(textodff);
        edit2.setText(textodff);





    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAgregarId2:
                String name = et.getText().toString();
                String place = et2.getText().toString();
                if(name.isEmpty()|| place.isEmpty()){

                }
                else {
                    dbconeccion.insertarDatos2(name,place);

                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                }
                if (name.isEmpty()){
                    Toast.makeText(this, "El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
                }
                if (place.isEmpty()){
                    Toast.makeText(this , "El campo edad esta vacio", Toast.LENGTH_SHORT).show();

                }

                else {
                    Intent main = new Intent(NewNoteActivity.this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(main);
                    break;
                }
            default:
                break;
        }
    }


    public void ACTION_CREATE_NOTE(String subject, String text) {
        Log.d(TAG, "ACTION_CREATE_NOTE: "+subject+ " " +text);

        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE);
        intent.putExtra(NoteIntents.EXTRA_NAME, subject);
        intent.putExtra(NoteIntents.EXTRA_TEXT, text);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }
}
