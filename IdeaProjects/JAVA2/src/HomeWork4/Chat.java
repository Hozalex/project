package HomeWork4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chat {

    public static void main(String[] args) {

        new MyWindow();
    }
}

class MyWindow extends JFrame {
    MyWindow() {
        setTitle("Java Hello");
        setBounds(800, 300, 400, 400);
        setMinimumSize(new Dimension(400, 400));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(Color.black.gray);
        bottomPanel.setBackground(Color.lightGray);

        bottomPanel.setPreferredSize(new Dimension(1, 40));

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        JButton jButton = new JButton("Send");

        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        centerPanel.add(jsp, BorderLayout.CENTER);

        JTextField jtf = new JTextField();

        bottomPanel.add(jtf);
        bottomPanel.add(jButton);

        jtf.setPreferredSize(new Dimension(300, 28));
        jta.setEditable(false);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(jtf, jta);
            }
        });

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == e.VK_ENTER) {
                    sendMessage(jtf, jta);
                }
            }
        });


        setVisible(true);
    }


    private void sendMessage(JTextField jtf, JTextArea jta) {
        if (!jtf.getText().isEmpty()) {
            jta.append("nick1: " + jtf.getText() + "\n");
            jtf.setText("");
            jtf.grabFocus();
        }

    }
}







