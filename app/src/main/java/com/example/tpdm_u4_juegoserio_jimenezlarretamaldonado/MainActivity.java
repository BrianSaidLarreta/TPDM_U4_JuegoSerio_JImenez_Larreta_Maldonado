package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(new Lienzo(this));
    }
}