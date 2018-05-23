package HomeWork6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8765);
            System.out.println("Server started");
            socket = server.accept();
            System.out.println("Client join");

            Scanner in = new Scanner(socket.getInputStream());

            while (true) {

                String str = in.nextLine();
                if (str.equals("/q")) {
                    break;
                }
                System.out.println(str);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
