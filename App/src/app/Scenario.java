package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scenario extends Thread {
    private String filepath = "app/scenarios.csv";

    public void run() {


    }

    public void setup() {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] objectData = line.split(cvsSplitBy);

                //setup object - don't forget changing datatypes and such
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
