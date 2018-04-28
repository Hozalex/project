package HomeWorkNine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Panel extends JPanel {

    private int rd;
    private int gr;
    private int bl;

    public Panel() {
        super();
    }

    public void setPanelColor(int rd, int gr, int bl) {
        this.rd = rd;
        this.gr = gr;
        this.bl = bl;
        setBackground(new Color(rd, gr, bl));

    }


    public void event(Slider slider) {

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                switch (slider.getName()) {
                    case "red":
                        rd = slider.getValue();
                        setBackground(new Color(rd, gr, bl));
                        ColorFrame.colorText.setText("RGB: " + rd + " " + gr + " " + bl);
                        break;
                    case "green":
                        gr = slider.getValue();
                        setBackground(new Color(rd, gr, bl));
                        ColorFrame.colorText.setText("RGB: " + rd + " " + gr + " " + bl);
                        break;
                    case "blue":
                        bl = slider.getValue();
                        setBackground(new Color(rd, gr, bl));
                        ColorFrame.colorText.setText("RGB: " + rd + " " + gr + " " + bl);
                        break;
                    default:
                        break;

                }

            }
        });


    }


}
