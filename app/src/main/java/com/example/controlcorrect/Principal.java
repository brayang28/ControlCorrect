package com.example.controlcorrect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Principal extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    private int[] galeria = { R.drawable.portada, R.drawable.portada2};
    private int posicion;
    private static final int DURACION = 9000;
    private Timer timer = null;
    ImageButton tyh, tareas, fv;
    Button cerrar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(Principal.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);

        tyh = (ImageButton)findViewById(R.id.boton1);

        tyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tyh = new Intent(Principal.this, InformeDia.class);
                startActivity(tyh);
            }
        });


        tareas = (ImageButton)findViewById(R.id.boton2);

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas = new Intent(Principal.this, TareasEmpleados.class);
                startActivity(tareas);
            }
        });

        fv = (ImageButton)findViewById(R.id.boton3);

        fv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fv = new Intent(Principal.this, Vencimiento.class);
                startActivity(fv);
            }
        });

        cerrar = (Button)findViewById(R.id.buttonCerrarSesion);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAuth.signOut();
                startActivity(new Intent(Principal.this, MainActivity.class));
                finish();
            }
        });
    }
}
