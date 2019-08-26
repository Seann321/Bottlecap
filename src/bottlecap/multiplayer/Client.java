package bottlecap.multiplayer;

import bottlecap.states.Handler;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Client");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    public String IP = "", line = "";

    private Handler handler;

    public Client(Handler handler) {
        this.handler = handler;
    }

    public void connectToServer() {
        IP = JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Connector",
                JOptionPane.QUESTION_MESSAGE);
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

    public void checkForMessage() throws IOException {
        if (in == null)
            return;
        if (in.ready())
            line = in.readLine();
        else return;
        if (line.startsWith("PING")) {
            sendMessage("PONG");
        }
    }

    public void sendMessage(String message) {
        if(out == null) return;
        out.println(message);
        try {
            checkForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


