package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.graphics.*;

public class PantallaDetenido {
    Bitmap fondo,texto;
    public PantallaDetenido(Lienzo l, int imagen,int imagen2){
        fondo = BitmapFactory.decodeResource(l.getResources(),imagen);
        texto = BitmapFactory.decodeResource(l.getResources(),imagen2);

    }

    public void pintar(Canvas c, Paint p){
        c.drawBitmap(fondo,0,0,p);
        c.drawBitmap(texto,50,500,p);
    }
}
