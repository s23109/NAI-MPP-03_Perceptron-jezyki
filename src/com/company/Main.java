package com.company;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("Items\\lang\\Polish");
        List<Path > testFiles = Files_operations.find_files_in_folder(path);

        for (Path e: testFiles
             ) {
            System.out.println(e);
            Files_operations.scan_for_letters(e).stream().forEach( ee -> {
                System.out.print(ee + " " );
            });
            System.out.print("\n");
        }


    }
}
