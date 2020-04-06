package Utilities;

import equipment.Equipment;
import interfaces.EquipmentOld;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {


    public List<Equipment> parseEquipmentFile(String fileName){
        List<Equipment> items = new ArrayList<>();
        File inputFile = new File(fileName);


        try {
            Scanner inputReader = new Scanner(inputFile);
            inputReader.nextLine();
            while(inputReader.hasNext()){
                Equipment e = new Equipment(inputReader.nextInt(),inputReader.nextDouble(),inputReader.nextDouble(),inputReader.nextDouble(),inputReader.nextDouble(),inputReader.nextDouble());
                items.add(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return items;
    }
}
