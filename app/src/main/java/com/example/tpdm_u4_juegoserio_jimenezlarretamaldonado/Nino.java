package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Nino {
    int x,y,direccion = 1,movX = 1;
    Bitmap nino,puntero;

    public Nino(Lienzo l,int posx, int posy, int imagen){
        nino = BitmapFactory.decodeResource(l.getResources(),imagen);
        x = (posx/2)-(nino.getWidth()/2);
        y = posy-nino.getHeight();
    }

    public void pintar(Canvas c, Paint p){
        c.drawBitmap(nino,x,y,p);
    }

    public void mover(){
        x += movX*direccion;
    }

    public boolean estaEnArea(int dedox,int dedoy) {
        int x2=x+nino.getWidth();
        int y2=y+nino.getHeight()/2;
        if(dedox >=x && dedox<=x2 && dedoy >=y && dedoy<=y2){
            return true;
        }
        return false;
    }

    public boolean estaEnColision(Comida objetoB){
        if(!objetoB.equals(this) ) {
            int bx = objetoB.x;
            int by = objetoB.y;
            int bx2 = bx + objetoB.imagen.getWidth();
            int by2 = by + objetoB.imagen.getHeight();
            if (estaEnArea(bx2, by2)) {//p4 en b
                return true;
            }
            if (estaEnArea(bx, by)) { //p1 en b
                return true;
            }
            if (estaEnArea(bx, by2)) {//p3 en b
                return true;
            }
            if (estaEnArea(bx2, by)) {
                return true;
            }
        }
        return false;
    }

}
