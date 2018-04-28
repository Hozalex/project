package HomeWorkEight;

import javax.swing.*;

public class MyButton extends JButton {

    private int sizeX;
    private int sizeY;

    public MyButton(String text, int sizeX, int sizeY) {
        super(text);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
