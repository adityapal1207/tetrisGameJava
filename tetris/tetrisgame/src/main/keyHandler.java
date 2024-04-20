package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
    
    public static boolean upPressed , downPressed , leftPressed,rightPressed,puasePressed;
    @Override
    public void keyTyped(KeyEvent e) {
     }

    @Override
    public void keyPressed(KeyEvent e) {
       int code = e.getKeyCode();
       if(code == KeyEvent.VK_UP){
         upPressed = true;
       }
       if(code == KeyEvent.VK_LEFT){
        leftPressed = true;
       }
       if(code== KeyEvent.VK_DOWN){
        downPressed = true;
       }
       if(code == KeyEvent.VK_RIGHT){
        rightPressed= true;
       }

       if(code == KeyEvent.VK_SPACE){
            if(puasePressed){
              puasePressed=false;
            }
            else{
              puasePressed=true;
            }
       }
       
            }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
