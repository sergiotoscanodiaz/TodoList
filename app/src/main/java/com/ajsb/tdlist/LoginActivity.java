package com.ajsb.tdlist;

/**
 * Programación Multimedia y de Dispositivos Móviles
 * Antonio J.Sánchez
 * ToDoList
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{
    public EditText email ;
    public EditText password ;
    public Button btnLogin ;
    public Button btnRegister ;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email       = findViewById(R.id.email) ;
        password    = findViewById(R.id.password) ;
        btnLogin    = findViewById(R.id.btnLogin) ;
        btnRegister = findViewById(R.id.btnRegister) ;

        // definimos la acción para el botón de login
        //btnLogin.setOnClickListener(this::doLogin);
        btnLogin.setOnClickListener(v -> doLogin(v)) ;

        // definimos la acción para el botón de registro
        btnRegister.setOnClickListener(v ->
        {
            Intent i = new Intent(this, RegisterActivity.class) ;
            startActivity(i) ;
        }); ;
    }

    /**
     * Realiza el login utilizando Firebase
     * @param view
     */
    private void doLogin(View view)
    {
        // obtenemos el valor de los campos
        String ema = getString(email) ;
        String pas = getString(password) ;

        // obtenemos una instancia el objeto FirebaseAuth
        FirebaseAuth fba = FirebaseAuth.getInstance() ;

        // intentamos realizar un login
        fba.signInWithEmailAndPassword(ema, pas)
             .addOnCompleteListener(this, task ->
             {
                 // si se produce un error mostramos un mensaje
                 if (!task.isSuccessful())
                    Snackbar.make(view, R.string.err_login, Snackbar.LENGTH_LONG).show() ;
                 else
                 {
                     Intent i = new Intent(this, MainActivity.class) ;
                     startActivity(i) ;
                 }
             }) ;

    }

    /**
     * @param view
     * @return
     */
    private String getString(TextView view)
    {
        return view.getText().toString().trim() ;
    }
}