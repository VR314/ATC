package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

//GPlane: time, id, gate, TOdirection, leaveGateTime, TOtime, leaveAirspaceTime

//APlane: time, id, angle, LANDtime, atGateTime, leaveGateTime, TOtime, leaveAirspaceTime

//TODO: implement gate determination, if 0 - algorithm decides

public class Scenario implements Runnable { //TODO: fix spawn times, angles - make realistic
    public Time time;
    HashMap Gs = new HashMap<Integer, GPlane>();
    HashMap As = new HashMap<Integer, APlane>();
    private String filepath = "App\\src\\app\\scenarios.csv";
    private Airport airport;
    private Airspace airspace;
    
    public Scenario(Time t, Airport airport, Airspace airspace) {
        this.time = t;
        this.airport = airport;
        this.airspace = airspace;
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
                LinkedList<String> line = new LinkedList<String>(Arrays.asList(sLine.split(cvsSplitBy))); //find lastTime as parsing and saving each line in array
                int time = Integer.parseInt(line.remove(0).trim()); //key
                if (line.remove(0).trim().equals("G")) {
                    int id = Integer.parseInt(line.remove(0).trim());
                    int gate = Integer.parseInt(line.remove(0).trim());
                    GPlane.Direction d;
                    if (line.remove(0).trim().equals("N")) {
                        d = GPlane.Direction.NORTH;
                    } else {
                        d = GPlane.Direction.SOUTH;
                    }
                    int leaveGateTime = Integer.parseInt(line.remove(0).trim());
                    int TOtime = Integer.parseInt(line.remove(0).trim());
                    int LEAVEtime = Integer.parseInt(line.remove(0).trim());
                    GPlane g = new GPlane(airport, gate, d, airspace, id, new int[]{-100, time, leaveGateTime, TOtime, LEAVEtime}); //NEGATIVE = NULL
                    g.time = this.time;
                    Gs.put(time, g);
    
                } else {
                    int id = Integer.parseInt(line.remove(0).trim());
                    int angle = Integer.parseInt(line.remove(0).trim());
                    int LANDtime = Integer.parseInt(line.remove(0).trim());
                    int atGatetime = Integer.parseInt(line.remove(0).trim());
                    int leaveGatetime = Integer.parseInt(line.remove(0).trim());
                    int TOtime = Integer.parseInt(line.remove(0).trim());
                    int LEAVEtime = Integer.parseInt(line.remove(0).trim());
                    APlane a = new APlane(angle, airport, airspace, id, new int[]{LANDtime, atGatetime, leaveGatetime, TOtime, LEAVEtime});
                    a.time = this.time;
                    As.put(time, a);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
