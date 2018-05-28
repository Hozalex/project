package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {

    @FXML
    TextArea chatArea;

    @FXML
    TextField textField;

    @FXML
    HBox upperPanel;

    @FXML
    HBox bottomPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    private boolean isAuthorized;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        if (!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
        } else {
            chatArea.clear();
            textField.requestFocus();
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }


    public void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                chatArea.appendText(str + "\n");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) {
                                break;
                            }
                            chatArea.appendText(str + "\n");
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {

        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
