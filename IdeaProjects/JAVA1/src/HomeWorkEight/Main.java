package HomeWorkEight;


import java.awt.*;

public class Main {

    public static void main(String[] args) {

        MyFrame fr = new MyFrame("Test", 600, 400, 100, 100);
        MyButton bt = new MyButton("Press", 50, 50);

        fr.setLayout(new FlowLayout());
        fr.add(bt);
        bt.addActionListener(e -> System.exit(0));
        fr.init();

    }

}
