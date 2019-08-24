package bottlecap;

import bottlecap.states.Handler;

import java.awt.*;

public class Square {

    private Boolean grabbed = false;
    private Rectangle player;
    private Handler handler;

    public Square(Rectangle player, Handler handler) {
        this.player=player;
        this.handler=handler;
    }

    public void tick() {

        movement(inBoundary());

    }

    public void render(Graphics g) {

        g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        g.drawRect(player.x,player.y,player.width,player.height);

    }

    public boolean inBoundary(){

        return player.contains(handler.getMM().getMouseX(),handler.getMM().getMouseY());

    }

    public void movement(Boolean inBoundary){

        if(inBoundary&&handler.getMM().isLeftPressed()) grabbed=true;
        if(!handler.getMM().isLeftPressed()) grabbed=false;
        if(grabbed) {
            player.x=handler.getMM().getMouseX()-player.width/2;
            player.y=handler.getMM().getMouseY()-player.height/2;
        }

    }

}
