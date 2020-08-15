package bottlecap;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.assets.images.ImageLoader;
import bottlecap.assets.images.Images;
import bottlecap.states.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bottlecap implements Runnable {

    private Display display;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private Handler handler;

    private int width, height;
    public String title;
    private boolean running = false;


    public Bottlecap(String title, int width, int height) {
        Text.Init();
        this.width = width;
        this.height = height;
        this.title = title;
        handler = new Handler(this);
    }

    private void init() throws IOException {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(handler.getKM());
        display.getFrame().addMouseListener(handler.getMM());
        display.getFrame().addMouseMotionListener(handler.getMM());
        display.getCanvas().addMouseListener(handler.getMM());
        display.getCanvas().addMouseMotionListener(handler.getMM());
        Images.init();
    }

    private void tick() {
        handler.getKM().tick();
        //if (handler.getKM().keyJustPressed(KeyEvent.VK_ESCAPE)) System.exit(0);
        handler.getCurrentState().tick();
    }



    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        //Everything below is what is drawn on the screen.
        handler.getCurrentState().render(g);
        //End Draw
        bs.show();

        g.dispose();
    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                if (ticks != fps) {
                    System.out.println("FPS: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }

        }

        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Thread getThread() {
        return thread;
    }

    public BufferStrategy getBs() {
        return bs;
    }

    public Graphics getG() {
        return g;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean isRunning() {
        return running;
    }


    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

}
