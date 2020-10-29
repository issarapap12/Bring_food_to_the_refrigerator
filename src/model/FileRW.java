package model;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class FileRW {
    private String dir;
    private String fileName;

    public FileRW(String dir, String fileName) {
        this.dir = dir;
        this.fileName = fileName;
    }
    public void write(Collection<Food> collection) {
        // create directory
        File file = new File(dir);
        if (!file.exists()) file.mkdir();

        // write file in directory
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (Food each : collection) {
                writer.write(each.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(TreeMap<String,ArrayList<ArrayList<Food>>> collection) {
        // create directory
        File file = new File(dir);
        if (!file.exists()) file.mkdir();

        // write file in directory
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (ArrayList<ArrayList<Food>> arr : collection.values()) {
                for (ArrayList<Food> arr2 : arr) {
                    if (arr2.isEmpty()) continue;
                    for (Food each : arr2) {
                        writer.write(each.toString() + "\n");
                        System.out.println("write: " + each.toString());
                    }
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Collection<Food> read() {
        Collection<Food> output = new ArrayList<>();

        try {
            // create directory
            File file = new File(dir);
            if (!file.exists()) file.mkdir();

            // read file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while (((line = reader.readLine()) != null)) {
                String[] split = line.split(",");
                Food food = new Food(split[0], Integer.parseInt(split[1]),split[2], Utility.dateFormat.parse(split[3]));
                output.add(food);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return output;
    }

}
