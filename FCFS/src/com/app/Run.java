package com.app;
import java.util.LinkedList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args){
        LinkedList<Drivable> drive = new LinkedList<Drivable>();


    }

    public static String ask(String out){
        Scanner s = new Scanner(System.in);
        System.out.print(out);
        return s.nextLine();
    }
}
