package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import bottlecap.states.gamestate.Square;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerManager {

        private ArrayList<Player> players = new ArrayList<>();
        private ArrayList<Player> newPlayers = new ArrayList<>();
        private Handler handler;


        public PlayerManager(Handler handler){
            this.handler=handler;
           newPlayers.add(new Player(new Rectangle(handler.getWidth()/2,handler.getHeight()/2,20,30),handler));
        }

        public void tick(){


            players.clear();
            players.addAll(newPlayers);
            Iterator<Player> it = players.iterator();
            while(it.hasNext()){
                Player x= it.next();
                x.tick();
            }

        }

        public void render(Graphics g){

            Iterator<Player> it = players.iterator();
            while(it.hasNext()){
                Player x= it.next();
                x.render(g);
            }
        }

    }



