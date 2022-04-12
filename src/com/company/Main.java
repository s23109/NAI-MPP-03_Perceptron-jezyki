package com.company;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("Items\\lang");
        List<Path > testFiles = Files_operations.find_files_in_folder(path);

        for (Path e: testFiles
             ) {
            System.out.println(e);
        }


    }
}
