package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");


            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public boolean subscribe(ClientHandler client, String nickName) {
        boolean isSameNick = false;
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickName)) {
                isSameNick = true;
            }
        }
        if (!isSameNick) {
            clients.add(client);
        }
        return isSameNick;
    }

    public void unsubsribe(ClientHandler client) {
        clients.remove(client);
        System.out.println("Клиент отключился");
        System.out.println("Клиентов осталось: " + clients.size());
    }

}
