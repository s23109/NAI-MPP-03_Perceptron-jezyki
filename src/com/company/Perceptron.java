package com.company;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    /*

    Dla 1 - Neuron activation
    Dla 0 - Neuron ignored

     */

    final double stala_uczenia ;
    final double akceptowalny_procent_bladow ;
    double prog_odchylenia = 3 ;
    private List<Double> wagi = new ArrayList<>();
    private int number_of_wagi ;
    String Perceptron_name;

    public Perceptron(int number_of_wagi , double stala_uczenia , double procent_bledow , String perceptron_name) {
        this.number_of_wagi = number_of_wagi;
        System.out.print("Wagi Perceptronu: ");
        for (int i = 0; i < number_of_wagi; i++) {
            wagi.add((Math.random())*(Math.random()>0.5?-1:1));
            System.out.print(wagi.get(i) + " ");
        }
        this.stala_uczenia=stala_uczenia;
        this.akceptowalny_procent_bladow=procent_bledow;
        this.Perceptron_name = perceptron_name;
        System.out.println("\nPerceptron "+ this.Perceptron_name + " created\n------------");
    }

    public void wypisz_wagi (){
        System.out.print("Wagi : ");
        for (Double e: wagi
        ) {
            System.out.print(e + " ");
        }

        System.out.print("\nProg odchylenia: "+ prog_odchylenia + "\n");
    }

    public void learn (List<Double> tested , int szacowany , int prawdziwy ) {

        List<Double> nowa_waga = new ArrayList<>();

        for (int i = 0; i < tested.size(); i++) {

            nowa_waga.add(wagi.get(i)+(stala_uczenia*(prawdziwy-szacowany))*tested.get(i));

        }

        this.wagi = nowa_waga;
        this.prog_odchylenia= prog_odchylenia-stala_uczenia*(prawdziwy-szacowany);

    }

    public int guess (List<Double> tested){

        Double net = 0.0;

        for (int i = 0; i < tested.size(); i++) {
            net+=tested.get(i)*wagi.get(i);
        }

        net+=prog_odchylenia;

        if (net >= 0) {
            // neuron activation
            return 1;
        }
        //not recognised
        return 0;
    }

    private Double dlugosc (){
        double dlugosc = 0;

        for (Double ele: wagi
             ) {
            dlugosc+= (ele*ele);
        }
        dlugosc= Math.sqrt(dlugosc);

        return dlugosc;

    }

    public void normalizuj_wagi (){

        List<Double> nowa_waga = new ArrayList<>();
        double Dlugosc = dlugosc();

        for (Double ele :wagi
             ) {
            nowa_waga.add(ele/Dlugosc);
        }

        this.wagi=nowa_waga;

    }

    public String getPerceptron_name() {
        return Perceptron_name;
    }


}
