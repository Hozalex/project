package HomeWorkNine;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ColorFrame extends JFrame {

    private Panel colorPanel;
    private Panel sliderPanel;
    private Panel basementPanel;
    static protected JLabel colorText = new JLabel("RGB: ");
    private JLabel rdl = new JLabel("Red");
    private JLabel grl = new JLabel("Green");
    private JLabel bll = new JLabel("Blue");
    private JLabel lbl = new JLabel("// ");

    public ColorFrame(String title) throws HeadlessException {
        super(title);

    }

    public void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 300));
        setDefaultLookAndFeelDecorated(true);


        //Panels
        basementPanel = new Panel();
        basementPanel.setLayout(new GridLayout());
        basementPanel.add(colorText).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        basementPanel.add(lbl).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        sliderPanel = new Panel();

        colorPanel = new Panel();
        colorPanel.setBorder(new LineBorder(Color.WHITE, 5));
        colorPanel.setPanelColor(0, 0, 0);


        //Sliders&Events
        Slider redSlider = new Slider(0, 255, 0, "red");
        colorPanel.event(redSlider);

        Slider greenSlider = new Slider(0, 255, 0, "green");
        colorPanel.event(greenSlider);

        Slider blueSlider = new Slider(0, 255, 0, "blue");
        colorPanel.event(blueSlider);

        Slider[] sliders = new Slider[]{redSlider, greenSlider, blueSlider};
        JLabel[] labels = new JLabel[]{rdl, grl, bll};

        //addSliders&TextLabel
        sliderPanel.setLayout(new GridLayout(6, 3));
        for (int i = 0; i < sliders.length; i++) {
            sliderPanel.add(labels[i]);
            sliderPanel.add(sliders[i]);
        }

        //addToFrame
        add(basementPanel, BorderLayout.SOUTH);
        add(sliderPanel, BorderLayout.EAST);
        add(colorPanel, BorderLayout.CENTER);
        setVisible(true);

    }


}
