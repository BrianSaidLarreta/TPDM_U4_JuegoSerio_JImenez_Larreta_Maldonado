package com.example.tpdm_u4_juegoserio_jimenezlarretamaldonado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lienzo extends View {
    Thread hilo,movimientoComida;
    List<Comida> comidas;
    int decision,maxW,maxH,indice;
    int [] imagenesSaludables = {R.drawable.apple1,R.drawable.beans,R.drawable.carrot,R.drawable.cucumber,R.drawable.fish,R.drawable.onion1,R.drawable.orange,R.drawable.pear,R.drawable.pineapple,R.drawable.salad1,R.drawable.strawberry,
            R.drawable.tomato,R.drawable.watermelon};
    int [] imagenesDaninas = {R.drawable.bacon,R.drawable.cake,R.drawable.cookies,R.drawable.doughnut1,R.drawable.fries,R.drawable.icecream12,R.drawable.pancakes1,R.drawable.pie,R.drawable.pizza,R.drawable.pudding,R.drawable.sabritas};


    public Lienzo(Context este){
        super(este);
        maxW= getResources().getSystem().getDisplayMetrics().widthPixels;
        maxH = getResources().getSystem().getDisplayMetrics().heightPixels-200;
        comidas = new ArrayList<>();
        hilo = new Thread(){
            public void run(){
                while(true){
                    decision = (int)(Math.random()*10);
                    if(decision > 5){
                        indice = (int)(Math.random()*12);
                        comidas.add(new Comida("saludable",maxW,imagenesSaludables[indice],Lienzo.this));
                    }else{
                        indice = (int)(Math.random()*10);
                        comidas.add(new Comida("danina",maxW,imagenesDaninas[indice],Lienzo.this));
                    }
                    invalidate();
                    try {
                        sleep(100);
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
    }


    protected void onDraw(Canvas c){
        Paint p = new Paint();
        for(int i = 0; i<comidas.size();i++){
            if(comidas.get(i).y >= maxH){
                comidas.remove(i);
            }else{
                comidas.get(i).pintar(c,p);
                comidas.get(i).mover();
            }
        }
    }

}
