package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.isNickInBlacklist(from.getNick()))
                o.sendMsg(msg);
        }
    }

    public void sendMsgToNick(String msg, String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick))
                o.sendMsg(msg);
        }
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }

        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }

    }

    public boolean isThereNick(String nickName) {
        boolean isSameNick = false;
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickName)) {
                isSameNick = true;
            }
        }
        return isSameNick;

    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubsribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
        System.out.println("Клиент отключился");
        System.out.println("Клиентов осталось: " + clients.size());
    }

}
