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
    public double Teta = 3 ;
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
        normalizuj_wagi();
        System.out.println("\nPerceptron "+ this.Perceptron_name + " created\n------------");
    }

    public void wypisz_wagi (){
        System.out.print("Wagi : ");
        for (Double e: wagi
        ) {
            System.out.print(e + " ");
        }

        System.out.print("\nProg odchylenia: "+ Teta + "\n");
    }

    public void learn (List<Double> tested , int d , int y ) {
        // d - oczekiwana
        // y - aktualnie szacowana
        List<Double> nowa_waga = new ArrayList<>();

        for (int i = 0; i < tested.size(); i++) {

            nowa_waga.add(wagi.get(i)+(stala_uczenia*(d-y))*tested.get(i));

        }

        this.wagi = nowa_waga;
        this.Teta= (Teta-(d-y)*stala_uczenia);

    }

    private double obliczNet(List<Double> amount_of_elements) {
        double net = 0;
        for (int i = 0; i < wagi.size(); i++) {
            net += wagi.get(i) * amount_of_elements.get(i);
        }
        return (net-this.Teta);
    }

    private boolean funkcja_aktywacji (Double net){
        return (net >= 0);
    }

    public int guess(List<Double> amount_of_elements) {
        if (funkcja_aktywacji(obliczNet(amount_of_elements))) {
            return 1;
        }
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

    public static List<Double> normalizuj_wagi (List<Double> wagi){

        List<Double> nowa_waga = new ArrayList<>();
        double Dlugosc = dlugosc(wagi);

        for (Double ele :wagi
        ) {
            nowa_waga.add(ele/Dlugosc);
        }

        return nowa_waga;

    }

    private static Double dlugosc (List<Double> wagi){
        double dlugosc = 0;

        for (Double ele: wagi
        ) {
            dlugosc+= (ele*ele);
        }
        dlugosc= Math.sqrt(dlugosc);

        return dlugosc;

    }

    public String getPerceptron_name() {
        return Perceptron_name;
    }


}
