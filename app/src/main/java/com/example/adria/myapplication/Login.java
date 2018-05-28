package com.example.adria.myapplication;

import android.app.Notification;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.actions.NoteIntents;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.appindexing.internal.Thing;

public class Login extends AppCompatActivity {
    DBhelper helper = new DBhelper(this);
    Context mcontext;
    Button btnlogin;
    EditText username, userpass;
    private String title, text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        mcontext = this;





            btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername = username.getText().toString();
                String strpass = userpass.getText().toString();

                String password = helper.searchPass(strusername);
                if(strpass.equals(password)){
                    Intent i = new Intent(mcontext, MainActivity.class);
                    i.putExtra("Username",strusername);
                    startActivity(i);
                }
                else
                {
                    Toast temp = Toast.makeText(mcontext, "Usuario y contraseña estan incorrectos!", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

    }
    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

            Toast.makeText(mcontext, "hola"+query, Toast.LENGTH_SHORT).show();

        }
    }
    public void createNote(String subject, String text) {

      Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE)
                .putExtra(NoteIntents.EXTRA_NAME, subject)
                .putExtra(NoteIntents.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void insertDato(String dato){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.getDataString();
        intent.putExtra(Intent.ACTION_INSERT,dato);
        Toast.makeText(mcontext, ""+dato, Toast.LENGTH_SHORT).show();
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void insertContact(String name, String email) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void bindViews(){
        btnlogin = (Button)findViewById(R.id.btningresar);
        username = (EditText)findViewById(R.id.user_name);
        userpass = (EditText)findViewById(R.id.user_pass);

    }
}
