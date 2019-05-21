package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Nino {
    int x,y,direccion = 1,peso=1;
    float movX = 9;
    Bitmap nino,puntero;
    boolean comiendo=false;
    List<Bitmap> animacion = new ArrayList<>();

    public Nino(Lienzo l,int posx, int posy, int imagen){

        nino = BitmapFactory.decodeResource(l.getResources(),imagen);
        for (int i = 0; i < 5; i++) {
            animacion.add(null);
        }
        x = (posx/2)-(nino.getWidth()/2);
        y = posy-nino.getHeight()+120;
    }
    public void centrarX(int posx){
        x = (posx/2)-(nino.getWidth()/2);
    }
    public void pintar(Canvas c, Paint p){
        c.drawBitmap(nino,x,y,p);
    }

    public void mover(){
        x += movX*direccion;
    }

    public boolean estaEnArea(int dedox,int dedoy) {
        int x2=(x+nino.getWidth())-(nino.getWidth()/4);
        int y2=y+nino.getHeight()/4;
        if(dedox >=(x+(nino.getWidth()/4)) && dedox<=x2 && dedoy >=y && dedoy<=y2){
            return true;
        }
        return false;
    }
    public void cambiarPeso(Lienzo lawea){
        if(peso ==1){
            animacion.set(0,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle1));
            animacion.set(1,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle2));
            animacion.set(2,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat1));
            animacion.set(3,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat2));
            animacion.set(4,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat3));
        }else if(peso ==2){
            animacion.set(0,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle1f1));
            animacion.set(1,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle2f1));
            animacion.set(2,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat1f1));
            animacion.set(3,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat2f1));
            animacion.set(4,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat3f1));

        }else if(peso == 3){
            animacion.set(0,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle1f2));
            animacion.set(1,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle2f2));
            animacion.set(2,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat1f2));
            animacion.set(3,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat2f2));
            animacion.set(4,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat3f2));

        }else if(peso == 4){
            animacion.set(0,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle1f3));
            animacion.set(1,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoidle2f3));
            animacion.set(2,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat1f3));
            animacion.set(3,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat2f3));
            animacion.set(4,BitmapFactory.decodeResource(lawea.getResources(),R.drawable.ninoeat3f3));

        }else{
            //pues te mueres si llega 0 o 5 xd
        }
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
