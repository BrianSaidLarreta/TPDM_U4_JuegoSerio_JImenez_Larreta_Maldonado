package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Comida {
    String tipo;
    int x,y,calorias, movY = 3;
    Bitmap imagen;
    Random rdn;


    public Comida (String tipo,int maxW, int imagen, Lienzo l,int calorias){
        rdn = new Random();
        this.tipo = tipo;
        this.imagen = BitmapFactory.decodeResource(l.getResources(),imagen);
        x = rdn.nextInt(maxW-this.imagen.getWidth()+1)+20;
        y = 0;
        this.calorias = calorias;
    }

    public void pintar(Canvas c, Paint p){
        c.drawBitmap(imagen,x,y,p);
    }
    public void mover(){
        y += movY;
    }



}
