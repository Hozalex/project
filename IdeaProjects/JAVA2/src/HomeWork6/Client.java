package HomeWork6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    final String IP_ADDRESS = "localhost";
    final int PORT = 8765;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Scanner scanner;

    public static void main(String[] args) {
        Client client = new Client();
        client.init();

    }

    private void init() {

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            System.out.println("Client ready...");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String stringIn = in.readUTF();
                            if (!stringIn.isEmpty()) {
                                System.out.println(stringIn);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String stringOut = scanner.nextLine();
                            if (!stringOut.isEmpty()) {
                                out.writeUTF(stringOut);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
