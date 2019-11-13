package com.example.controlcorrect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {

    Button iniciar;
    EditText textCorreo, textContra, textConfContra;

    //Variables
    String correo = "";
    String contra = "";
    String confContra = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textCorreo = (EditText)findViewById(R.id.regisCorreo);
        textContra = (EditText)findViewById(R.id.newContra);
        textConfContra = (EditText)findViewById(R.id.confContra);

        iniciar = (Button)findViewById(R.id.botonIniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = textCorreo.getText().toString();
                contra = textContra.getText().toString();
                confContra = textConfContra.getText().toString();

                if(!correo.isEmpty() && !contra.isEmpty() && !confContra.isEmpty()){
                    if(contra.length() >= 6 && contra.equals(confContra)){
                        registerUser();
                    }
                }
                else{
                    Toast.makeText(Registrar.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("mail", correo);
                    map.put("password", contra);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Toast.makeText(Registrar.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                                Intent iniciar = new Intent(Registrar.this, Principal.class);
                                startActivity(iniciar);
                                finish();
                            }
                            else{
                                Toast.makeText(Registrar.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Registrar.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
