package main;

import java.awt.Color;

public class shape_Bar extends shape{
public shape_Bar(){
    create(Color.white);
}
public void setXY(int x ,int y){

  /*
        * * * *
   */
    b[0].x=x;
    b[0].y=y;

    b[1].x=b[0].x-block.size;
    b[1].y=b[0].y;

    b[2].x=b[0].x+block.size;
    b[2].y=b[0].y;

    b[3].x=b[0].x+block.size*2;
    b[3].y=b[0].y;

}
  public void getDirection1(){

     /*
         * * * *
      */
    tempB[0].x=b[0].x;
    tempB[0].y=b[0].y;

    tempB[1].x=b[0].x-block.size;
    tempB[1].y=b[0].y;

    tempB[2].x=b[0].x+block.size;
    tempB[2].y=b[0].y;

    tempB[3].x=b[0].x+block.size*2;
    tempB[3].y=b[0].y;
    updateXY(1);
  }
  public void getDirection2(){

    /*
           *
           * 
           * 
           * 
     */
    tempB[0].x=b[0].x;
    tempB[0].y=b[0].y;

    tempB[1].x=b[0].x;
    tempB[1].y=b[0].y-block.size;

    tempB[2].x=b[0].x;
    tempB[2].y=b[0].y+block.size;

    tempB[3].x=b[0].x;
    tempB[3].y=b[0].y+block.size*2;
    updateXY(2);
  }
  public void getDirection3(){
    getDirection1();;
  }
  public void getDirection4(){
    getDirection2();;
  }

}
 