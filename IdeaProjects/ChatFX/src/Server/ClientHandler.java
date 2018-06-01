package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    ArrayList<String> blackList;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();
            new Thread(() -> {
                try {

                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) {
                            String[] tokens = str.split(" ");
                            String newNick = AuthService.getNickByLoginPass(tokens[1], tokens[2]);

                            if (newNick != null && !server.isThereNick(newNick)) {
                                sendMsg("/authok");
                                nick = newNick;
                                server.subscribe(this);
                                break;
                            } else {
                                sendMsg("такой nick уже залогинился или не существует");
                            }
                        } else {
                            sendMsg("неверный логин/пароль");
                        }

                    }

                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ", 3);
                                server.sendMsgToNick(nick + ": " + tokens[2], tokens[1]);
                            }
                            if (str.startsWith("/blacklist")) {
                                String[] tokens = str.split(" ");
                                blackList.add(tokens[1]);
                                sendMsg("вы добавили пользователя " + tokens[1] + " в черный список");
                                for (String s : blackList) {
                                    System.out.println(nick + " добавил " + s);
                                }
                            }
                        } else {
                            server.broadcastMsg(this, "from " + nick + " " + str);
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
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubsribe(ClientHandler.this);
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isNickInBlacklist(String nick) {
        return blackList.contains(nick);

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

}
