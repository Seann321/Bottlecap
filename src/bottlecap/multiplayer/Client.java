package bottlecap.multiplayer;

import bottlecap.states.Handler;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

    private BufferedReader in;
    private PrintWriter out;
    private String IP = "";

    private Handler handler;

    public Client(Handler handler) {
        this.handler = handler;
    }

    public void connectToServer(String x) {
        IP = x;
        System.out.println("Trying to connect to: " + IP);
        Socket socket = null;
        try {
            socket = new Socket(IP, 9001);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        try {
            checkForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForMessage() throws IOException {
        if (in == null)
            return;
        String line = "";
        if (in.ready())
            line = in.readLine();
        else return;
        handler.setLastMessage(line);
        if (line.startsWith("PING")) {
            sendMessage("PONG");
            System.out.println("Connected to " + IP);
        }
    }

    private String lastMessage = "";

    public void sendMessage(String message) {
        if(out == null) return;
        if(message.equals(lastMessage)) return;
        out.println(message);
        //System.out.println("Info Sent " + message);
        lastMessage = message;
        try {
            checkForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


