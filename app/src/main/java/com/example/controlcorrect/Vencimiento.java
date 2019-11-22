package com.example.controlcorrect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Vencimiento extends AppCompatActivity {

    Button add;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vencimiento);

        add = (Button)findViewById(R.id.buttonAddVenci);
        back = (ImageButton)findViewById(R.id.imageButtonBack);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(Vencimiento.this, AddVenci.class);
                startActivity(add);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Vencimiento.this, Principal.class);
                startActivity(back);
            }
        });

    }
}
