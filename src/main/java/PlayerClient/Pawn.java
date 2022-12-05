package PlayerClient;

import java.io.Serializable;

public class Pawn implements Serializable {
    int position=-1; //Posicion del peon -1 esta en base diferente de -1 esta en el path;
    Player player;

    public int squaresMoved=0;

    public Pawn(int position, Player player) {
        this.position = position;
        this.player = player;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
