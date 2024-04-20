package main;

import java.awt.*;
import javax.swing.JPanel;

public class game extends JPanel implements Runnable {

    public static final int width = 1280;
    public static final int height = 720;
    final int fps = 60;
    Thread gameThread;
    gameManager gm;

    public game() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        gm = new gameManager();
        this.addKeyListener(new keyHandler());
        this.setFocusable(true);
        
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

        
    }

    public void update() {
        if(keyHandler.puasePressed == false && gm.gameOver==false){
            gm.update();
        }
       
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gm.draw(g2);
    }
}
