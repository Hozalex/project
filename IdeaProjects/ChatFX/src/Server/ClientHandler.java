package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;


    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginPass(tokens[1], tokens[2]);

                                if (newNick != null && !server.subscribe(ClientHandler.this, newNick)) {
                                    sendMsg("/authok");
                                    nick = newNick;
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
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ");
                                String toNick = tokens[1];
                                String sendMsg = str.substring(toNick.length() + 3);
                                server.broadcastMsg(nick + ": " + sendMsg, toNick);
                            } else {
                                server.broadcastMsg(nick + ": " + str);
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

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

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
