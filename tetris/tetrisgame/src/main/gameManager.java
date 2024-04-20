package main;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

public class gameManager {
    // play area
    final int width = 360;
    final int height = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    // for shape
    shape currentShape;
    final int shape_start_x;
    final int shape_start_y;

    shape nextShape;
    final int nextShape_X;
    final int nextShape_Y;
    public static ArrayList<block> staticBlocks = new ArrayList<>();

    public static int dropInterval = 60;
    
    boolean gameOver;
    
    // effect while deleting the lines
    boolean effectCounterOn;
    int effectCounter; 
    ArrayList<Integer> effectY =new ArrayList<>();

    // for score
     int level=1;
     int lines;
     int Score;

    public gameManager() {
        // play area shape
        left_x = (game.width / 2) - (width / 2);
        right_x = left_x + width;
        top_y = 50;
        bottom_y = top_y + height;

        shape_start_x = left_x + (width / 2) - block.size;
        shape_start_y = top_y + block.size;

        nextShape_X = right_x + 175;
        nextShape_Y = top_y + 500;
        
        Random objRandom = new Random();
        int randNum = objRandom.nextInt(7);

        switch (randNum) {
            case 0:
                currentShape = new shape_L1();
                break;
            case 1:
                currentShape = new shape_L2();
                break;
            case 2:
                currentShape = new shape_T1();
                break;
            case 3:
                currentShape = new shape_Bar();
                break;
            case 4:
                currentShape = new shape_Square();
                break;
            case 5:
                currentShape = new shape_Z1();
                break;
            case 6:
                currentShape = new shape_Z2();
                break;
            default:
                currentShape = null;
                break;
        }

        

        // currentShape = new shape_L1();
        currentShape.setXY(shape_start_x, shape_start_y);

        // next Shape

        Random obRandom2 = new Random();
        int randNumNext = obRandom2.nextInt(7);
        switch (randNumNext) {
            case 0:
                nextShape = new shape_L1();
                break;
            case 1:
                nextShape = new shape_L2();
                break;
            case 2:
                nextShape = new shape_T1();
                break;
            case 3:
                nextShape = new shape_Bar();
                break;
            case 4:
                nextShape = new shape_Square();
                break;
            case 5:
                nextShape = new shape_Z1();
                break;
            case 6:
                nextShape = new shape_Z2();
                break;
            default:
                nextShape = null;
                break;
        }

        nextShape.setXY(nextShape_X, nextShape_Y);

    }

    public void update() {
        if (currentShape.active == false) {
            staticBlocks.add(currentShape.b[0]);
            staticBlocks.add(currentShape.b[1]);
            staticBlocks.add(currentShape.b[2]);
            staticBlocks.add(currentShape.b[3]);
            
            if(currentShape.b[0].x==shape_start_x && currentShape.b[0].y == shape_start_y){
               
               //if  current shape immediately collided another shape without moving  
                gameOver=true;
            }

            currentShape.deactivation = false;

            currentShape = nextShape;
            currentShape.setXY(shape_start_x, shape_start_y);

            Random obRandom3 = new Random();
            int randNumNext3 = obRandom3.nextInt(7);
            switch (randNumNext3) {
                case 0:
                    nextShape = new shape_L1();
                    break;
                case 1:
                    nextShape = new shape_L2();
                    break;
                case 2:
                    nextShape = new shape_T1();
                    break;
                case 3:
                    nextShape = new shape_Bar();
                    break;
                case 4:
                    nextShape = new shape_Square();
                    break;
                case 5:
                    nextShape = new shape_Z1();
                    break;
                case 6:
                    nextShape = new shape_Z2();
                    break;
                default:
                    nextShape = null;
                    break;
            }

            nextShape.setXY(nextShape_X, nextShape_Y);

            checkDelete();

        } else {
            currentShape.update();
        }
    }

    private void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount=0;
        while (x < right_x && y < bottom_y) {
            for (int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    blockCount++;
                }
            }
            x += block.size;
            if (x == right_x) {

                // 12 boxes per line
                if (blockCount == 12) {
                  
                    effectCounterOn=true;
                    effectY.add(y);

                    for (int i = staticBlocks.size() - 1; i > -1; i--) {
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }
                 lineCount++;
                 lines++;
                 //incresing drop speed after 10 line drawn 
                 if(lines%10==0&&dropInterval>1){
                    level++;
                    if(dropInterval>10){
                        dropInterval-=10;
                    }
                    else{
                        dropInterval-=1;
                    }
                 }

                    // moving one line down if all the boxes are filled
                    for (int i = 0; i < staticBlocks.size(); i++) {
                        if (staticBlocks.get(i).y < y) {
                            staticBlocks.get(i).y += block.size;
                        }
                    }
                }

                blockCount = 0;
                x = left_x;
                y += block.size;
            }
        }
        if(lineCount>0){
            int singleLineScore=10*level;
            Score +=singleLineScore*lineCount;
        }
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, width + 8, height + 8);
        // next Frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Aerial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        // Score Box
            g2.drawRect(x,top_y,250,300);
             x+=40;
             y=top_y+90;
             g2.drawString("Level: " +level , x,y);
             y+=70;
             g2.drawString("Lines: "+lines,x,y);
             y+=70;
             g2.drawString("Score: " + Score, x, y);
        if (currentShape != null) {
            currentShape.draw(g2);
        }
        nextShape.draw(g2);
        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        // effect while deleting line
        if(effectCounterOn){
            effectCounter++;
            g2.setColor(Color.red);
            for(int i=0;i<effectY.size();i++){
                g2.fillRect(left_x, effectY.get(i), width, block.size);
            }
            if(effectCounter == 10){
                effectCounterOn=false;
                effectCounter=0;
                effectY.clear();
            }
        }

        // if paused

        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
         if(gameOver){
            x=left_x+25;
            y=top_y+320;
            g2.drawString("Game Over !", x, y);
         }
        else if (keyHandler.puasePressed) {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }
        // title for left side
         x=35;
         y=top_y+250;
         g2.setColor(Color.white);
         g2.setFont(new Font("Times New Roman",Font.ITALIC,40));
         g2.drawString("Tetris Game 2024" , x+50,y);
         g2.setFont(new Font("Times New Roman",Font.ITALIC,30));
         g2.drawString("developed by ~ Aditya Kumar Pal student @ Lovely Professional University", 180, y+410);
    }

}
