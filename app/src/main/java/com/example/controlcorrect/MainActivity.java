package com.example.controlcorrect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button inicio, registrar;
    EditText textCorreo, textContra;

    //Variables de datos a registrar
    String correo = "";
    String pass = "";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        textCorreo = (EditText)findViewById(R.id.correo);
        textContra = (EditText)findViewById(R.id.password);

        inicio = (Button)findViewById(R.id.botonIniciar);
        registrar = (Button)findViewById(R.id.botonRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(MainActivity.this, Registrar.class);
                startActivity(registrar);
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = textCorreo.getText().toString();
                pass = textContra.getText().toString();

                if(!correo.isEmpty() && !pass.isEmpty()){
                    loginUser();
                }
            }
        });
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Principal.class));
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
