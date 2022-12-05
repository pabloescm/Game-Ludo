package Server;

import GUI.Square;
import PlayerClient.Game;
import PlayerClient.Mapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 9147;
    public static Lobby currentLobby = new Lobby();
    public static ConcurrentHashMap<String, ObjectOutputStream> activeClients = new ConcurrentHashMap<String, ObjectOutputStream>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server is up and running...");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = listener.accept(); // new client connected
                System.out.println("New client connected" + socket.getInetAddress().getHostName());
                new Handler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Handler extends Thread {
        private final Socket socket;
        private ObjectInputStream Receiver;
        private ObjectOutputStream Sender;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                // Create object streams for the socket.
                Sender = new ObjectOutputStream(socket.getOutputStream());
                Receiver = new ObjectInputStream(socket.getInputStream());
                activeClients.put(UUID.randomUUID().toString(), Sender);// key =92192311928219, value =Sender
                //loop for allowing connections

                while (true) {
                    Mapper receipt = (Mapper) Receiver.readObject();
                    System.out.println(receipt.getKey());
                    if (receipt.getKey().equals("CONNECT")) {//player is attempting to connect
                        if (!currentLobby.isFull()) {//lobby is not full so allow the player
                            String player = currentLobby.addPlayer(receipt.getValue().toString());
                            Mapper mapper = new Mapper("CONNECTED", player + "/" + currentLobby.getGame().numPlayers);
                            Sender.writeObject(mapper);
                            if (currentLobby.isFull()) {
                                for (String client : Server.activeClients.keySet()) {
                                    ObjectOutputStream broadCaster = Server.activeClients.get(client);
                                    Mapper msg = new Mapper("GAMEON", currentLobby.getGame());
                                    broadCaster.writeObject(msg);
                                }
                            }
                        } else {//the lobby is full so continue with the game
                            Mapper mapper = new Mapper("DENY", "THE LOBBY IS FULL");
                            Sender.writeObject(mapper);
                        }
                    } else if (receipt.getKey().equals("GRID")) {


                        ArrayList<Square> grid = (ArrayList<Square>) receipt.getValue();


                      for (String client : Server.activeClients.keySet()) {
                            ObjectOutputStream broadCaster = Server.activeClients.get(client);
                            Mapper msg = new Mapper("GRID", grid);
                            broadCaster.writeObject(msg);
                        }




                        /*
                          for(String client:Server.activeClients.keySet()) {
                           ObjectOutputStream broadCaster=Server.activeClients.get(client);
                           Mapper msg = new Mapper("GRID", grid);
                           broadCaster.writeObject(msg);
                        }

                        if (currentLobby.getGame().getTurn()==1){
                            activeClients.get("3").writeObject(new Mapper("GRID",grid));
                       }
                       if(currentLobby.getGame().getTurn()==2){
                            activeClients.get("0").writeObject(new Mapper("GRID",grid));
                        }
                       if (currentLobby.getGame().getTurn()==3){
                            activeClients.get("1").writeObject(new Mapper("GRID",grid));
                        }
                        if (currentLobby.getGame().getTurn()==4){
                            activeClients.get("2").writeObject(new Mapper("GRID",grid));
                        }
  */



                    } else {//the game is on going so take the received game object and broadcast it to other players
                        Game received = (Game) receipt.getValue();
                        currentLobby.setGame(received);
                        for (String client : Server.activeClients.keySet()) {
                            ObjectOutputStream broadCaster = Server.activeClients.get(client);
                            Mapper msg = new Mapper("PLAY", currentLobby.getGame());
                            broadCaster.writeObject(msg);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

