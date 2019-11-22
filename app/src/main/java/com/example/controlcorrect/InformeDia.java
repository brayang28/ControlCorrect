package com.example.controlcorrect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformeDia extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_dia);

        back = (ImageButton)findViewById(R.id.imageButtonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(InformeDia.this, Principal.class);
                startActivity(back);
            }
        });

    }
}
