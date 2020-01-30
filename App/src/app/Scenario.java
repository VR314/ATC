package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Scenario implements Runnable {
    HashMap Gs = new HashMap<Integer, GPlane>();
    HashMap As = new HashMap<Integer, APlane>();
    private String filepath = "App\\src\\app\\scenarios.csv";
    private Airport airport;
    private Airspace airspace;

    private Time time;
    private int lastTime = 0;

    public Scenario(Time t, Airport airport, Airspace airspace) {
        this.time = t;
        this.airport = airport;
        this.airspace = airspace;
    }

    public void run() {
        setup();
        ((APlane) As.get(100)).spawn();
        ((GPlane) Gs.get(30)).spawn();
    }

    public void setup() {
        String cvsSplitBy = ";";
        String sLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            while ((sLine = br.readLine()) != null) {
                ArrayList<String> line = new ArrayList<String>(Arrays.asList(sLine.split(cvsSplitBy))); //find lastTime as parsing and saving each line in array
                int time = Integer.parseInt(line.remove(0)); //key
                if (line.remove(0).trim().equals("G")) {
                    int id = Integer.parseInt(line.remove(0));
                    int gate = Integer.parseInt(line.remove(0));
                    GPlane.Direction d;
                    if (line.remove(0).trim().equals("N")) {
                        d = GPlane.Direction.NORTH;
                    } else {
                        d = GPlane.Direction.SOUTH;
                    }
                    int TOtime = Integer.parseInt(line.remove(0));
                    int LEAVEtime = Integer.parseInt(line.remove(0));
                    GPlane g = new GPlane(airport, gate, d, airspace, id);
                    //TODO: add TO and LEAVEtime to object
                    Gs.put(time, g);

                } else {
                    int id = Integer.parseInt(line.remove(0));
                    int angle = Integer.parseInt(line.remove(0));
                    int LANDtime = Integer.parseInt(line.remove(0));
                    int GATEtime = Integer.parseInt(line.remove(0));
                    int atGATEtime = Integer.parseInt(line.remove(0));
                    int TOtime = Integer.parseInt(line.remove(0));
                    int LEAVEtime = Integer.parseInt(line.remove(0));
                    APlane a = new APlane(angle, airport, airspace, id);
                    //TODO: add times to object
                    As.put(time, a);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
