package HomeWorkEight;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private int sizeX;
    private int sizeY;
    private int x;
    private int y;
    String title;


    public MyFrame(String title, int sizeX, int sizeY, int x, int y) throws HeadlessException {
        super(title);
        this.setBounds(x, y, sizeX, sizeY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.title = title;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;

    }

    public void init() {
        this.setVisible(true);
    }


}
