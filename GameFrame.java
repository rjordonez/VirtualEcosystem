import java.awt.Dimension;

import javax.swing.JFrame;
import java.util.Timer;

public class GameFrame extends JFrame 
{
    GamePanel g;
    Timer timer;
    GameFrame(int outputSize)
    {
        g = new GamePanel(outputSize);
        this.add(g);
        this.setTitle("Perlin");
        this.setSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        //makes it in the middle
        this.setLocationRelativeTo(null);
        
    }
    public GamePanel getGamePanel()
    {
        return this.g;
    }
}

