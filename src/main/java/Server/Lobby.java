package Server;

import PlayerClient.Game;

public class Lobby {
    private Game game;
    private boolean isFull;
    public Lobby(){
        game=new Game();
        isFull=false;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isFull() {
        return game.numPlayers==4;
    }


    public String addPlayer(String username){
        if(isFull()){
            return null;
        }
        switch(game.numPlayers){
            case 0:
                game.blue.username=username;
               // game.blue=new Player(username,1);
                game.numPlayers++;
                return "blue";
            case 1:
                game.yellow.username=username;
              //  game.yellow=new Player(username,2);
                game.numPlayers++;
                return "yellow";
            case 2:
                game.red.username=username;
                //game.red=new Player(username,3);
                game.numPlayers++;
                return "red";
            case 3:
                game.green.username=username;

               // game.green=new Player(username,4);
                game.numPlayers++;
                return "green";
        }
        return null;
    }
}
