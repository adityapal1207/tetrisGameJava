package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class shape {
    public block b[] = new block[4];
    public block tempB[] = new block[4];

    int autoDropCounter = 0;
    public int direction = 1;

    boolean leftLine, rightLine, bottomLine;
    public boolean active = true;
     public boolean deactivation;
    int deactivationCounter=0;
    public void create(Color magenta) {
        b[0] = new block(magenta);
        b[1] = new block(magenta);
        b[2] = new block(magenta);
        b[3] = new block(magenta);
        tempB[0] = new block(magenta);
        tempB[1] = new block(magenta);
        tempB[2] = new block(magenta);
        tempB[3] = new block(magenta);

    }

    public void setXY(int x, int y) {

    }

    public void updateXY(int direction) {
        checkRotationCollision();
        if (leftLine == false && rightLine == false && bottomLine == false) {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;

            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;

            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;

            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }

    public void getDirection1() {
    }

    public void getDirection2() {
    }

    public void getDirection3() {
    }

    public void getDirection4() {
    }

    public void checkMovementCollision() {
        leftLine = false;
        rightLine = false;
        bottomLine = false;
        checkStaticBlockCollision();
        // for left line
        for (int i = 0; i < b.length; i++) {
            if (b[i].x == gameManager.left_x) {
                leftLine = true;
            }
        }
        // for right line
        for (int i = 0; i < b.length; i++) {
            if (b[i].x + block.size == gameManager.right_x) {
                rightLine = true;
            }
        }

        // for bottom
        for (int i = 0; i < b.length; i++) {
            if (b[i].y + block.size == gameManager.bottom_y) {
                bottomLine = true;
            }
        }
    }

    public void checkRotationCollision() {

        leftLine = false;
        rightLine = false;
        bottomLine = false;
        checkStaticBlockCollision();
        // for left line
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x < gameManager.left_x) {
                leftLine = true;
            }
        }
        // for right line
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x + block.size > gameManager.right_x) {
                rightLine = true;
            }
        }

        // for bottom
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].y + block.size > gameManager.bottom_y) {
                bottomLine = true;
            }
        }

    }

    private void checkStaticBlockCollision() {
        for(int i = 0; i < gameManager.staticBlocks.size(); i++) {
            int targetX = gameManager.staticBlocks.get(i).x;
            int targetY = gameManager.staticBlocks.get(i).y;
            // for down collision 
            for(int j =0;j<b.length;j++){
                if(b[j].y+block.size == targetY && b[j].x == targetX){
                    bottomLine=true;
                }
            }
            // for left collision
            for(int k =0;k<b.length;k++){
                if(b[k].x-block.size==targetX && b[k].y ==targetY){
                    leftLine=true;
                }
            }
            for(int l =0;l<b.length;l++){
                if(b[l].x+block.size == targetX && b[l].y == targetY){
                    rightLine=true;
                }
            }
        }
    }

    public void update() {

        if(deactivation){
            deactivation();
        }
        // up press is for rotating.
        if (keyHandler.upPressed) {
            switch (direction) {
                case 1:
                    getDirection2();
                    break;
                case 2:
                    getDirection3();
                    break;
                case 3:
                    getDirection1();
                    break;
                case 4:
                    getDirection1();
                    break;
            }
            keyHandler.upPressed = false;
        }
        checkMovementCollision();
        if (keyHandler.downPressed) {
            if (bottomLine == false) {
                b[0].y += block.size;
                b[1].y += block.size;
                b[2].y += block.size;
                b[3].y += block.size;
                autoDropCounter = 0;
            }

            keyHandler.downPressed = false;
        }

        if (keyHandler.leftPressed) {
            if (leftLine == false) {
                b[0].x -= block.size;
                b[1].x -= block.size;
                b[2].x -= block.size;
                b[3].x -= block.size;
            }
            keyHandler.leftPressed = false;
        }
        if (keyHandler.rightPressed) {
            if (rightLine == false) {
                b[0].x += block.size;
                b[1].x += block.size;
                b[2].x += block.size;
                b[3].x += block.size;
            }
            keyHandler.rightPressed = false;
        }
        if (bottomLine) {
            deactivation=true;
            //active = false;
        } else {
            autoDropCounter++;
            if (autoDropCounter == gameManager.dropInterval) {
                b[0].y += block.size;
                b[1].y += block.size;
                b[2].y += block.size;
                b[3].y += block.size;
                autoDropCounter = 0;
            }
        }

    }
     private void deactivation(){
        deactivationCounter++;
        if(deactivationCounter == 45){
            deactivationCounter=0;
           checkMovementCollision();
           if(bottomLine){
            active=false;
           }
        }
     }
    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(b[0].c);

        g2.fillRect(b[0].x + margin, b[0].y + margin, block.size - (margin * 2), block.size - (margin * 2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, block.size - (margin * 2), block.size - (margin * 2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, block.size - (margin * 2), block.size - (margin * 2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, block.size - (margin * 2), block.size - (margin * 2));

    }

}
