package main;

import java.awt.Color;

public class shape_Square extends shape {
    public shape_Square(){
        create(Color.GRAY);
    }
    public void setXY(int x , int y){
        /*
              * *
              * *
         */
        b[0].x=x;
        b[0].y=y;
        
        b[1].x=b[0].x;
        b[1].y=b[0].y+block.size;

        b[2].x=b[0].x + block.size;
        b[2].y=b[0].y;

        b[3].x=b[0].x + block.size;
        b[3].y=b[0].y  +  block.size;
    }
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}

}
