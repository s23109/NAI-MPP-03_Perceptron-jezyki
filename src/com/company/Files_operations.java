package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Files_operations {

    List<Double> scan_for_letters (Path path){
        BufferedReader treningdata = null;
        List<Double> elements = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            elements.add(0.0);
        }

        try {
            treningdata = new BufferedReader(new FileReader(String.valueOf(path)));
            String line = null;
            char[] temp = null;
            while ((line = treningdata.readLine())!=null) {
                line = line.toUpperCase(Locale.ROOT);
                temp = line.toCharArray();

                for (char testowe : temp
                ) { if (testowe >= 'A' || testowe <= 'Z') {
                        elements.add((int) testowe - 65 , 1.0);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }

    public static List <Path> find_files_in_folder ( Path folder_path){
        List <Path> found_files = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(folder_path)){
            found_files = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found_files;

    }


}
