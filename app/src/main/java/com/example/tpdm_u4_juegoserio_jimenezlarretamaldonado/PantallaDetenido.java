package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.graphics.*;
import android.util.DisplayMetrics;

public class PantallaDetenido {
    Bitmap fondo,texto;
    DisplayMetrics metrics;
    int width,height;
    public PantallaDetenido(Lienzo l, int imagen,int imagen2){
        fondo = BitmapFactory.decodeResource(l.getResources(),imagen);
        texto = BitmapFactory.decodeResource(l.getResources(),imagen2);
        DisplayMetrics metrics = l.getResources().getDisplayMetrics();
         width = metrics.widthPixels;
         height = metrics.heightPixels;

    }

    public void pintar(Canvas c, Paint p){




        fondo = Bitmap.createScaledBitmap(fondo, width, height, false);

        Rect frameToDraw = new Rect(0, 0, fondo.getWidth(), fondo.getHeight());
        RectF whereToDraw = new RectF(0, 0, width, height);

        c.drawBitmap(fondo,frameToDraw,whereToDraw,p);
        c.drawBitmap(texto,50,500,p);
    }
}
