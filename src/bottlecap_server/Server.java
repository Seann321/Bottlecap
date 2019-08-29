package bottlecap_server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Handler;

public class Server {

    static String input = "";
    static JFrame frame = new JFrame("BOTTLECAP SERVER");
    static JTextField textField = new JTextField(40);
    static JTextArea messageArea = new JTextArea(8, 40);
    private static final int PORT = 9001;

    private static HashSet<PrintWriter> writers = new HashSet<>();

    public static void main(String[] args) throws Exception {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        messageArea.append("Server is running on port " + PORT + "\n");

        //Layout GUI
        textField.setEditable(true);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        textField.addActionListener(e -> {
            input = (textField.getText());
            textField.setText("");
            if (input.startsWith("/")) {
                for (PrintWriter writer : writers) {
                    writer.println("SERVER " + input.substring(1));
                }
                messageArea.append("{SERVER}" + input.substring(1) + "\n");
            }
            input = "";
        });

        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }

    }

    private static class Handler extends Thread {

        private Socket socket;

        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void sendMessage(String message) {

            for (PrintWriter writer : writers) {
                writer.println(message);
            }

        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                messageArea.append("New Client: " + socket.getInetAddress() + "\n");

                writers.add(out);

                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals("")) {
                        continue;
                    } else if (input.startsWith("1"))
                        out.println("PING");
                    else if (input.startsWith("PONG")) {
                        System.out.println("Client has Ponged");
                    } else {
                        sendMessage(input);
                    }
                }
            } catch (IOException e) {
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
            }
        }

    }
}




