package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lienzo extends View {
    Thread hilo,movimientoComida,movimientonino, tiempo,cuadros,animacion;
    List<Comida> comidas;
    MediaPlayer[] sonidosComiendo;
    boolean bandera = true, fin=false,inicio=true;
    int decision,maxW,maxH,indice, calorias,puntos=0,contador = 3;
    int [] imagenesSaludables = {R.drawable.apple1,R.drawable.beans,R.drawable.carrot,R.drawable.broccoli,R.drawable.fish,R.drawable.grapes,R.drawable.orange,R.drawable.pear,R.drawable.pineapple,R.drawable.salad1,R.drawable.strawberry,
            R.drawable.tomato,R.drawable.watermelon};
    int [] imagenesDaninas = {R.drawable.bacon,R.drawable.cake,R.drawable.cookies,R.drawable.doughnut1,R.drawable.fries,R.drawable.icecream12,R.drawable.pancakes1,R.drawable.pie,R.drawable.pizza,R.drawable.pudding,R.drawable.sabritas,R.drawable.can};
    int [] imagenesPowerUps = {R.drawable.bici,R.drawable.box,R.drawable.zapatos,R.drawable.pesa,R.drawable.volibol};
    Nino jugador;
    Bitmap[] barritas;
    Random rdn;
    DisplayMetrics metrics;
    int width,height;
//variables

// inicia el constructor
    public Lienzo(Context este){
        super(este);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = (BitmapFactory.decodeResource(getResources(),R.drawable.bar1).getHeight());

        maxW= getResources().getSystem().getDisplayMetrics().widthPixels;
        maxH = getResources().getSystem().getDisplayMetrics().heightPixels;
        comidas = new ArrayList<>();
        jugador = new Nino(Lienzo.this,maxW,maxH,R.drawable.nino);
        barritas = new Bitmap[8];




        barritas[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bar1);
        barritas[0] = Bitmap.createScaledBitmap(barritas[0], width, height, false);

        barritas[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bar2);
        barritas[1] = Bitmap.createScaledBitmap(barritas[1], width, height, false);

        barritas[2] = BitmapFactory.decodeResource(getResources(),R.drawable.bar3);
        barritas[2] = Bitmap.createScaledBitmap(barritas[2], width, height, false);

        barritas[3] = BitmapFactory.decodeResource(getResources(),R.drawable.bar4);
        barritas[3] = Bitmap.createScaledBitmap(barritas[3], width, height, false);

        barritas[4] = BitmapFactory.decodeResource(getResources(),R.drawable.bar5);
        barritas[4] = Bitmap.createScaledBitmap(barritas[4], width, height, false);

        barritas[5] = BitmapFactory.decodeResource(getResources(),R.drawable.bar6);
        barritas[5] = Bitmap.createScaledBitmap(barritas[5], width, height, false);

        barritas[6] = BitmapFactory.decodeResource(getResources(),R.drawable.bar7);
        barritas[6] = Bitmap.createScaledBitmap(barritas[6], width, height, false);

        barritas[7] = BitmapFactory.decodeResource(getResources(),R.drawable.bar8);
        barritas[7] = Bitmap.createScaledBitmap(barritas[7], width, height, false);




        sonidosComiendo = new MediaPlayer[3];
        sonidosComiendo[1] =MediaPlayer.create(este,R.raw.sonidomanzana);
        sonidosComiendo[0] =MediaPlayer.create(este,R.raw.sonidopapa);
        sonidosComiendo[2] = MediaPlayer.create(este,R.raw.sonidolechuga);
        rdn = new Random();
        hilo = new Thread(){
            public void run(){
                while(true){
                    decision = (int)(Math.random()*29+1);
                    if(decision > 1 && decision <= 12 ){
                        indice = (int)(Math.random()*12+1);
                        calorias = (int)(Math.random()*80+10);
                        comidas.add(new Comida("saludable",maxW,imagenesSaludables[indice],Lienzo.this,calorias));
                    }else if (decision >12 && decision <= 27){
                        indice = (int)(Math.random()*10);
                        calorias = (int)(Math.random()*500+80);
                        comidas.add(new Comida("danina",maxW,imagenesDaninas[indice],Lienzo.this,calorias));
                    }else{
                        indice = indice = (int)(Math.random()*4 +1);
                        calorias = (int)(Math.random()*250+100);
                        comidas.add(new Comida("poder",maxW,imagenesPowerUps[indice],Lienzo.this,calorias*-1));
                    }
                    if (fin || inicio) {
                        comidas.clear();
                    }

                    invalidate();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        hilo.start();
        movimientoComida = new Thread(){
            public void run(){
                while(true){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    invalidate();
                }
            }
        };
        movimientoComida.start();

        tiempo = new Thread(){
            public void run(){
                while(true){
                    if(!fin){

                        if(contador >= 1 && contador <= 3){
                            jugador.peso =1;
                            jugador.movX = 9;
                            jugador.cambiarPeso(Lienzo.this);
                            puntos += 1;
                        }
                        else if(contador == 4){
                            jugador.peso =2;
                            jugador.movX = 5f;
                            jugador.cambiarPeso(Lienzo.this);
                            puntos -= 1;
                        }
                        else if (contador == 5){
                            jugador.peso =3;
                            jugador.movX = 4f;
                            jugador.cambiarPeso(Lienzo.this);
                            puntos -= 2;
                        }
                        else if (contador == 6){
                            jugador.peso =4;
                            jugador.movX = 3f;
                            jugador.cambiarPeso(Lienzo.this);
                            puntos -= 4;
                        }
                    }
                    try {
                        sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    invalidate();
                }
            }
        };
        tiempo.start();
        cuadros = new Thread(){
            public void run(){
                while(true){
                    if(!fin)
                    try {

                        if (contador >= 1 && contador <= 3) {
                            sleep(10000);
                        } else if (contador == 4)
                            sleep(13000);
                        else if (contador == 5)
                            sleep(16000);
                        else if (contador == 6)
                            sleep(20000);
                        if(contador!=0){
                            contador--;
                        }


                    }
                     catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    else {
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    invalidate();
                }
            }
        };
        cuadros.start();
        //comer
        animacion = new Thread(){
            public void run(){
                jugador.cambiarPeso(Lienzo.this);
                while(true){
                    try{
                        if(jugador.comiendo==true){
                            jugador.nino = jugador.animacion.get(2);
                            sleep(30);
                            jugador.nino = jugador.animacion.get(3);
                            sleep(50);
                            jugador.nino = jugador.animacion.get(4);
                            sleep(30);

                            jugador.comiendo = false;

                        }else {
                            jugador.nino = jugador.animacion.get(0);
                            sleep(50);
                            jugador.nino = jugador.animacion.get(1);
                            sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    invalidate();
                }
            }
        };
        animacion.start();
    }


    protected void onDraw(Canvas c){
        Rect frameToDraw = new Rect(0, 0, barritas[0].getWidth(), barritas[0].getHeight());
        RectF whereToDraw = new RectF(0, 0, width, height);
        Paint p = new Paint();
        p.setTextSize(60f);
        int equis=17;
        if(inicio || fin){
            PantallaDetenido detenido = new PantallaDetenido(Lienzo.this,R.drawable.fondo,R.drawable.texto);
            detenido.pintar(c,p);
            return;
        }
//        if(fin || inicio)

        jugador.pintar(c,p);
        if(puntos <= 100){
            puntos = 100;
        }

        switch(contador){


            case 0:
                //morido
                c.drawBitmap(barritas[0],frameToDraw,whereToDraw,p);
                fin=true;
                break;
            case 1:
                c.drawBitmap(barritas[1],frameToDraw,whereToDraw,p);
                break;
            case 2:
                c.drawBitmap(barritas[2],frameToDraw,whereToDraw,p);
                break;
            case 3:
                c.drawBitmap(barritas[3],frameToDraw,whereToDraw,p);
                break;
            case 4:
                c.drawBitmap(barritas[4],frameToDraw,whereToDraw,p);
                break;
            case 5:
                c.drawBitmap(barritas[5],frameToDraw,whereToDraw,p);
                break;
            case 6:
                c.drawBitmap(barritas[6],frameToDraw,whereToDraw,p);
                break;
            default:
                //moridox2
                c.drawBitmap(barritas[7],frameToDraw,whereToDraw,p);
                fin=true;
                break;
        }
        c.drawText(puntos+"",50,100,p);
        for(int i = 0; i<comidas.size();i++){
            if(comidas.get(i).y >= maxH){
                comidas.remove(i);
            }else{
                if(jugador.estaEnColision(comidas.get(i))) {
//                    puntos += comidas.get(i).calorias;
                    sonidosComiendo[rdn.nextInt(3)].start();
                    if(comidas.get(i).tipo=="saludable" || comidas.get(i).tipo=="poder"){
                        puntos+=10;
                        if(contador>=1 && contador<3){
                            contador++;
                        }
                        else if(contador>3 && comidas.get(i).tipo=="poder"){
                            contador--;
                        }
                    }else{
                        contador++;
                    }
                    jugador.comiendo = true;
                    comidas.remove(i);
                }
                else{
                    comidas.get(i).pintar(c,p);
                    comidas.get(i).mover();
                }

            }
        }
    }


    public boolean onTouchEvent(MotionEvent me) {
        int accion = me.getAction();
        int posx = (int)me.getX();
        int posy = (int)me.getY();
        System.out.println(accion);
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                {
                    bandera = true;
                    if(fin){
                        resetear();

                    }
                    if(inicio)
                        inicio=false;
                    else{

                        if (posx <= maxW / 2 && posx >= 0) {
                            jugador.direccion = -1;
                        } else {
                            jugador.direccion = 1;
                        }
                        movimientonino = new Thread(){
                            public void run(){
                                while(true){
                                    if(bandera) {
                                        if (jugador.x >= 0 && (jugador.nino.getWidth() + jugador.x) <= maxW)
                                            jugador.mover();
                                        if (jugador.x <= 0) {
                                            jugador.x = 1;
                                        }
                                        if ((jugador.x + jugador.nino.getWidth()) >= maxW) {
                                            jugador.x = (maxW-jugador.nino.getWidth())-1 ;
                                        }

                                    }
                                    else
                                        break;

                                    invalidate();
                                    try {
                                        sleep(10);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        };
                        movimientonino.start();
                    }
                    break;
                }
            case MotionEvent.ACTION_UP:
                {
                    bandera = false;
                    break;
                }
            }
            invalidate();
            return true;
        }

        private void resetear(){
            jugador.peso = 1;
            jugador.movX= 9;
            jugador.cambiarPeso(Lienzo.this);
            jugador.centrarX(maxW);
            puntos=0;
            contador=3;
            bandera=true;
            fin=false;
        }
}
