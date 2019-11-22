package com.example.controlcorrect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class AddVenci extends AppCompatActivity {

    ImageButton back;
    EditText codBar, fv;
    Button add;

    DatabaseReference myRootReference;
    StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venci);


        myRootReference = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();

        codBar = (EditText)findViewById(R.id.editText);
        fv = (EditText)findViewById(R.id.editText2);
        add = (Button)findViewById(R.id.buttonAdd);

        back = (ImageButton)findViewById(R.id.imageButtonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AddVenci.this, Vencimiento.class);
                startActivity(back);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = fv.getText().toString();
                int cod = Integer.parseInt(codBar.getText().toString());

                agregarProductosFirebase(cod, fecha);

                Toast noti = Toast.makeText(AddVenci.this, "¡Producto agregado!", Toast.LENGTH_SHORT);
                noti.show();
                Intent add = new Intent(AddVenci.this, Principal.class);
                startActivity(add);
            }
        });

    }

    private void agregarProductosFirebase(int codBar, String fv) {
        Map<String, Object> datosVencimiento = new HashMap<>();
        datosVencimiento.put("codigo", codBar);
        datosVencimiento.put("fv", fv);

        myRootReference.child("Fecha").push().setValue(datosVencimiento);
    }
}
