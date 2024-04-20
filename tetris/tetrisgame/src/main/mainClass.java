package main;
import javax.swing.JFrame;
public class mainClass {
  public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game 2024");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        game gameObj = new game();
        frame.add(gameObj);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gameObj.launchGame();
    }
}
