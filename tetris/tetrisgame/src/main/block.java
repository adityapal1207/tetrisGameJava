package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class block extends Rectangle{
  public int x, y;
public Color c;
  public static final int size = 30;
  public block(Color c){
    this.c=c;
  }
  public void draw(Graphics2D g2){
    int margin = 2;
    g2.setColor(c);
    g2.fillRect(x+margin,y+margin,size-(margin*2),size-(margin*2));
  }

}
