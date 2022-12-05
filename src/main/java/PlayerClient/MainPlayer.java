package PlayerClient;

import GUI.Board;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainPlayer {
    Socket socket;
    public ObjectInputStream receiver;
    public ObjectOutputStream sender;
    Game game = null;
    JFrame frame;

    public static void main(String[] args) {
        MainPlayer player = new MainPlayer();
        player.frame = new JFrame("Ludo");
        player.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            player.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (player.game == null) {
            JOptionPane.showMessageDialog(player.frame, "Waiting for other players to connect");
        }


    }

    private void displayGameWindow(Game game) {
        Board board = new Board(game);
        board.setMp(this);
        //  game.initAllPaths();
        //   board.initColors();
        frame.add(board);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread thread = new Thread(board);
        thread.start();
    }

    private void run() throws IOException, ClassNotFoundException {
        String serverAddress = "localhost";
        socket = new Socket(serverAddress, 9147);
        if (socket.isConnected()) {
            System.out.println("We are connected");
        } else {
            System.out.println("We are not connected");
        }
        sender = new ObjectOutputStream(socket.getOutputStream());
        receiver = new ObjectInputStream(socket.getInputStream());


        String username = getUsername();
        if (username.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Enter username");
            username = getUsername();
        }
        sender.writeObject(new Mapper("CONNECT", username));

        int i = 2;
        // Process server messages.
        while (game == null) {
            try {
                Mapper receipt = (Mapper) receiver.readObject();
                System.out.println(receipt.getKey());
                if (receipt.getKey().equals("CONNECTED")) {//player is the first to connect
                    String msg = receipt.getValue().toString();
                    String player = msg.split("/")[0];
                    int playersConnected = Integer.parseInt(msg.split("/")[1]);
                    int remaining = 4 - playersConnected;
                    frame.setTitle("LUDO - " + player);
                    JOptionPane.showMessageDialog(frame, "You've successfully connected to the lobby. You're the " + player + " player. " + remaining + " players remaining");
                } else if (receipt.getKey().equals("DENY")) {
                    JOptionPane.showMessageDialog(frame, receipt.getValue());
                } else if (receipt.getKey().equals("GAMEON")) {
                    game = (Game) receipt.getValue();
                    displayGameWindow(game);
                }
                i--;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private String getUsername() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter your  username:",
                "Username",
                JOptionPane.PLAIN_MESSAGE);
    }

    private String getLobbySize() {
        return JOptionPane.showInputDialog(
                frame,
                "You are the first player to connect to the lobby. Enter the size of the lobby",
                "Enter lobby size",
                JOptionPane.PLAIN_MESSAGE);
    }

    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "LUDO",
                JOptionPane.QUESTION_MESSAGE);
    }
}
