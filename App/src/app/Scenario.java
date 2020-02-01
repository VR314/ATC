package app;

import javax.swing.*;
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
    private JLabel label;
    private Time time;

    public Scenario(Time t, Airport airport, Airspace airspace, JLabel label) {
        this.time = t;
        this.airport = airport;
        this.airspace = airspace;
        this.label = label;
    }

    public void run() {
        setup();
        long mins = -999;
        while (true) {//As.size() > 0 && Gs.size() > 0 ){
            if (time.getMins() != mins) {
                mins = time.getMins();
                System.out.println(time.format());
                if (As.containsKey((int) mins)) {
                    ((APlane) As.remove((int) mins)).spawn();
                }
                if (Gs.containsKey((int) mins)) {
                    ((GPlane) Gs.remove((int) mins)).spawn();
                }
            }
        }
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
                    int leaveGateTime = Integer.parseInt(line.remove(0));
                    int TOtime = Integer.parseInt(line.remove(0));
                    int LEAVEtime = Integer.parseInt(line.remove(0));
                    GPlane g = new GPlane(airport, gate, d, airspace, id, new int[]{-100, time, leaveGateTime, TOtime, LEAVEtime}); //NEGATIVE = NULL
                    Gs.put(time, g);

                } else {
                    int id = Integer.parseInt(line.remove(0));
                    int angle = Integer.parseInt(line.remove(0));
                    int LANDtime = Integer.parseInt(line.remove(0));
                    int atGatetime = Integer.parseInt(line.remove(0));
                    int leaveGatetime = Integer.parseInt(line.remove(0));
                    int TOtime = Integer.parseInt(line.remove(0));
                    int LEAVEtime = Integer.parseInt(line.remove(0));
                    APlane a = new APlane(angle, airport, airspace, id, new int[]{LANDtime, atGatetime, leaveGatetime, TOtime, LEAVEtime});
                    As.put(time, a);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
