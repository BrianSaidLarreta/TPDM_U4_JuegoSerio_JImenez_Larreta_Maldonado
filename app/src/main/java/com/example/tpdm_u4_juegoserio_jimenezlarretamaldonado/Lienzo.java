package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lienzo extends View {
    Thread hilo,movimientoComida,movimientonino, tiempo,cuadros,animacion;
    List<Comida> comidas;
    boolean bandera = true;
    int decision,maxW,maxH,indice, calorias,puntos=0,contador = 3;
    int [] imagenesSaludables = {R.drawable.apple1,R.drawable.beans,R.drawable.carrot,R.drawable.broccoli,R.drawable.fish,R.drawable.grapes,R.drawable.orange,R.drawable.pear,R.drawable.pineapple,R.drawable.salad1,R.drawable.strawberry,
            R.drawable.tomato,R.drawable.watermelon};
    int [] imagenesDaninas = {R.drawable.bacon,R.drawable.cake,R.drawable.cookies,R.drawable.doughnut1,R.drawable.fries,R.drawable.icecream12,R.drawable.pancakes1,R.drawable.pie,R.drawable.pizza,R.drawable.pudding,R.drawable.sabritas,R.drawable.can};
    int [] imagenesPowerUps = {R.drawable.bici,R.drawable.box,R.drawable.zapatos,R.drawable.pesa,R.drawable.volibol};
    Nino jugador;
    Bitmap calavera;

    public Lienzo(Context este){
        super(este);
        maxW= getResources().getSystem().getDisplayMetrics().widthPixels;
        maxH = getResources().getSystem().getDisplayMetrics().heightPixels;
        comidas = new ArrayList<>();
        jugador = new Nino(Lienzo.this,maxW,maxH,R.drawable.nino);
        calavera = BitmapFactory.decodeResource(getResources(),R.drawable.calaberita);

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

                    invalidate();
                    try {
                        sleep(2500);
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
                    if(contador >= 1 && contador <= 3)
                        puntos += 1;
                    else if(contador == 4)
                        puntos -= 1;
                    else if (contador == 5)
                        puntos -= 2;
                    else if (contador == 6)
                        puntos -= 4;
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
                    try {
                        if(contador==0)
                            contador=1;
                        if (contador >= 1 && contador <= 3) {
                            sleep(7000);
                        } else if (contador == 4)
                            sleep(9000);
                        else if (contador == 5)
                            sleep(11000);
                        else if (contador == 6)
                            sleep(13000);
                        contador--;
                    }
                     catch (InterruptedException e) {
                        e.printStackTrace();
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
        Paint p = new Paint();
        p.setTextSize(60f);
        jugador.pintar(c,p);
        if(puntos <= 100){
            puntos = 100;
        }

        switch(contador){
            case 0:
                //morido
                break;
            case 1:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                break;
            case 2:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                break;
            case 3:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                c.drawRect(120,0,170,50,p);
                break;
            case 4:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                c.drawRect(120,0,170,50,p);
                p.setColor(Color.rgb(225,223,47));//amarillo
                c.drawRect(180,0,230,50,p);
                break;
            case 5:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                c.drawRect(120,0,170,50,p);
                p.setColor(Color.rgb(225,223,47));//amarillo
                c.drawRect(180,0,230,50,p);
                p.setColor(Color.rgb(225,131,47));//naranja
                c.drawRect(240,0,290,50,p);
                break;
            case 6:
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                c.drawRect(120,0,170,50,p);
                p.setColor(Color.rgb(225,223,47));//amarillo
                c.drawRect(180,0,230,50,p);
                p.setColor(Color.rgb(225,131,47));//naranja
                c.drawRect(240,0,290,50,p);
                p.setColor(Color.rgb(225,64,47));//rojo
                c.drawRect(300,0,350,50,p);
                break;
            default:
                //moridox2
                p.setColor(Color.rgb(84,206,41));//verde
                c.drawRect(0,0,50,50,p);
                c.drawRect(60,0,110,50,p);
                c.drawRect(120,0,170,50,p);
                p.setColor(Color.rgb(225,223,47));//amarillo
                c.drawRect(180,0,230,50,p);
                p.setColor(Color.rgb(225,131,47));//naranja
                c.drawRect(240,0,290,50,p);
                p.setColor(Color.rgb(225,64,47));//rojo
                c.drawRect(300,0,350,50,p);
                c.drawBitmap(calavera,350,0,p);
        }
        c.drawText(puntos+"",50,100,p);
        for(int i = 0; i<comidas.size();i++){
            if(comidas.get(i).y >= maxH){
                comidas.remove(i);
            }else{
                if(jugador.estaEnColision(comidas.get(i))) {
//                    puntos += comidas.get(i).calorias;
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
                                    sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    movimientonino.start();
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
}
