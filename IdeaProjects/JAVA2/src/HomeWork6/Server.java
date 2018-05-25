package HomeWork6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {

    ServerSocket server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public static void main(String[] args) {

        Server srv = new Server();
        srv.serverStart();

    }

    private void serverStart() {

        try {

            server = new ServerSocket(8765);
            System.out.println("Server started...");
            socket = server.accept();
            System.out.println("Client join.");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String stringIn = in.readUTF();
                            if (stringIn.equals("/q")) {
                                out.writeUTF("/serverClosed ");
                                break;
                            }
                            if (!stringIn.isEmpty()) {
                                System.out.println(stringIn);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                            socket.close();
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
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

