package bottlecap.states.gameState.worldGenerator;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class WorldGenerator {

    private boolean debug = false;
    private Handler handler;
    private Tiles tiles;
    private String worldPath;
    public ArrayList<String> tileCharGrabber = new ArrayList<>();
    public ArrayList<WorldTiles> worldTiles;
    public int[][] tileGen = new int[32][64];

    public WorldGenerator(Handler handler, Tiles tiles, String worldPath) {
        this.worldPath = worldPath;
        this.handler = handler;
        this.tiles = tiles;
        worldTiles = new ArrayList<>();
        initReadFromFile();
        turnStringArrayToChar();
        turnCharArrayIntoTiles();
    }

    public Enum returnTileType(int cords[]){
        for (WorldTiles wt : worldTiles) {
            if(wt.bounds.contains(cords[0],cords[1])){
                return wt.tileType;
            }
        }
        return WorldTiles.TileType.BRIDGE;
    }

    public void tick() {
        for (WorldTiles wt : worldTiles) {
            wt.tick();
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_BACK_SLASH) && debug) {
            String temp = "";
            int i = 0;
            for (WorldTiles wt : worldTiles) {
                switch (wt.tileType) {
                    case GRASS:
                        temp += 0;
                        break;
                    case WATER:
                        temp += 1;
                        break;
                    case FOREST:
                        temp += 2;
                        break;
                    case DESSERT:
                        temp += 3;
                        break;
                    case BRIDGE:
                        temp += 4;
                        break;
                    case DESSERTTOWN:
                        temp += 5;
                        break;
                    case GRASSTOWN:
                        temp += 6;
                        break;
                }
                i++;
                if (i == 64) {
                    saveWorldAsFile(temp);
                    temp = "";
                    i = 0;
                }
            }
        }
    }

    private void saveWorldAsFile(String message) {

        try {
            FileWriter fileWriter = new FileWriter("src/bottlecap/assets/worlds/SavedWorld.txt", true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(message);
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + "src/bottlecap/assets/worlds/SavedWorld.txt" + "'");
        }

    }

    private void turnCharArrayIntoTiles() {
        for (int i = 0; i < 32; i++) {
            for (int ii = 0; ii < 64; ii++) {
                worldTiles.add(new WorldTiles(tiles, tiles.cords(ii + 1, i), tileGen[i][ii], handler));
                //System.out.println(tileGen[i][ii]);
                //System.out.println("X " + tiles.cords(i,ii)[0] + " Y " + tiles.cords(i,ii)[1] + " CODE " + tileGen[i][ii]);
            }
        }
    }

    private void turnStringArrayToChar() {
        int i = 0;
        for (String x : tileCharGrabber) {
            char[] temp = x.toCharArray();
            for (int ii = 0; ii < temp.length; ii++) {
                tileGen[i][ii] = Integer.parseInt("" + temp[ii]);
            }
            i++;
        }
    }

    private void initReadFromFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(worldPath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String temp = contentBuilder.toString();
        String tempArray[] = temp.split("\\r?\\n");
        int i = 0;
        for (String s : tempArray) {
            tileCharGrabber.add(s);
            i++;
        }
    }

    public void render(Graphics g) {
        for (WorldTiles wt : worldTiles) {
            wt.render(g);
        }
    }

}
