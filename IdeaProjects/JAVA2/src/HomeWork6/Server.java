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

            try {

                while (true) {

                    String stringIn = in.readUTF();

                    if (stringIn.equals("/q")) {
                        out.writeBytes("/serverClosed ");
                        break;
                    }

                    System.out.println(stringIn);
                    String stringOut = scanner.nextLine();
                    out.writeUTF(stringOut);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

