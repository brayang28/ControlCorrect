package com.example.controlcorrect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TareasEmpleados extends AppCompatActivity {

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_empleados);

        back = (ImageButton)findViewById(R.id.imageButtonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(TareasEmpleados.this, Principal.class);
                startActivity(back);
            }
        });

    }
}
