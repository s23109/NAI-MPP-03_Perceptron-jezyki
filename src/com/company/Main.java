package com.company;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

//        Path path = Path.of("Items\\lang\\Polish");
//        List<Path > testFiles = Files_operations.find_files_in_folder(path);
//
//        for (Path e: testFiles
//             ) {
//            System.out.println(e);
//            Files_operations.scan_for_letters(e).stream().forEach( ee -> {
//                System.out.print(ee + " " );
//            });
//            System.out.print("\n" + Perceptron.normalizuj_wagi(Files_operations.scan_for_letters(e)) + "\n");
//        }
        final double stala_uczenia = 0.025;
        final double akceptowalny_procent_bledow = 0.01;
        final int learn_loop_limit = 1000;

        Map<String,List<Path> > files_map = new HashMap<>();
        files_map.put("Polish" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang\\\\Polish")));
        files_map.put("English" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang\\\\English")));
        files_map.put("German" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang\\\\German")));

        files_map.put("Polish_Test" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang.test\\\\Polish")));
        files_map.put("English_Test" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang.test\\\\English")));
        files_map.put("German_Test" , Files_operations.find_files_in_folder(Path.of("Items\\\\lang.test\\\\German")));

        List<Perceptron> perceptronList = new ArrayList<>();
        //(int number_of_wagi , double stala_uczenia , double procent_bledow , String perceptron_name)
        perceptronList.add(new Perceptron(26, stala_uczenia , akceptowalny_procent_bledow,"Polish"));
        perceptronList.add(new Perceptron(26, stala_uczenia , akceptowalny_procent_bledow,"English"));
        perceptronList.add(new Perceptron(26, stala_uczenia , akceptowalny_procent_bledow,"German"));

        //nauka wszystkich , po kolei
        for (Perceptron perceptron: perceptronList) {
            double ile_przeszedl = 0.0;
            double ile_zgadl = 0.0;
            boolean while_bool = true;
            int ilosc_iteracji = 0;

            while (while_bool){
                // /learn loop
                ilosc_iteracji+=1;

                for (Map.Entry map_element: files_map.entrySet()) {
                    String key = (String) map_element.getKey();
                    if (key.contains("Test")) break;

                    List<Path> files = (List<Path>) map_element.getValue();

                    for (Path file_path: files
                         ) {
                        //dla każdego pliku z akt mapy
                        ile_przeszedl+=1.0;
                        //weź wagę z pliku
                        List<Double> wagi_z_pliku = Files_operations.scan_for_letters(file_path);
                        wagi_z_pliku= Perceptron.normalizuj_wagi(wagi_z_pliku);

                        //oblicz wymagane + aktualne zachowanie perceptronu
                        int strzal = perceptron.guess(wagi_z_pliku);
                        int rzeczywistosc = (perceptron.getPerceptron_name().contains(key)?1:0);

                        if (strzal==rzeczywistosc){
                            //perceptron trafił, gites
                            ile_zgadl+=1.0;
                        }
                        else {
                            //perceptron nie trafił, learn
                            perceptron.learn(wagi_z_pliku,rzeczywistosc,strzal);
                        }
                        // /files from 1 map entry
                    }

                    // / one map entry
                }


                if (ilosc_iteracji >= learn_loop_limit){
                    System.out.println("Perceptron " + perceptron.getPerceptron_name() + " osiągnął limit powtórzeń nauki \n Osiągnięta celność :" + ile_zgadl/ile_przeszedl);
                    while_bool=false;
                }

                if ((1 - ile_zgadl/ile_przeszedl) <= perceptron.akceptowalny_procent_bladow ){
                    System.out.println("Perceptron " + perceptron.getPerceptron_name() + " osiągnął minimalną celność\n Osiągnięta celność :" + ile_zgadl/ile_przeszedl);
                    while_bool=false;
                }

                // /learn loop
            }
            perceptron.wypisz_wagi();
            System.out.print("------------\n");


        }




    }
}
