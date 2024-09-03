package org.example.creditinterestapp;

import javafx.application.Application;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Banks {

    protected String bankName, creditType1 = "Personal Finance", creditType2 = "Housing", creditType3 = "Transport";
    protected ImageView bankLogo;
    protected int creditTypeNumber;
    protected double creditType1Interest, creditType2Interest, creditType3Interest;


    private static Banks[] banks = new Banks[]{new ZiraatBankasi(), new GarantiBankasi(), new IsBankasi()};


    public static double calculatePayableAmount(double amount, double term, double interest) {
        //(amount/100)*(interest/term)*term, payable amount=amount+amount*interest*monthlyterm/12/100
        double interestAmount = 0, newAmount = 0;
        interestAmount = ((amount * (interest)) * (term / 12) / (100));
        newAmount = amount + interestAmount;
        return newAmount;

    }

    public static double calculateInterestAmount(double amount, double term, double interest) {

        double interestAmount = 0;
        interestAmount = ((amount * (interest)) * (term / 12) / (100));
        return interestAmount;

    }

    public static double calculateCost(double amount, double term, double interest) {

        double interestAmount = 0, costRate = 0;
        interestAmount = ((amount * (interest)) * (term / 12) / (100));
        costRate = interestAmount / amount * (100);
        return costRate;

    }

    public static ArrayList<String> getBankName() {

        ArrayList<String> bankList = new ArrayList<>();
        for (Banks bank : banks) {
            bankList.add(bank.bankName);
        }
        return bankList;

    }

    // 0 refer ziraat, 1 refer garanti, 2 refer is
    public static ArrayList<String> getCreditTypeZiraat() {
        ArrayList<String> creditTypeListZiraat = new ArrayList<>();
        creditTypeListZiraat.add(banks[0].creditType1);
        creditTypeListZiraat.add(banks[0].creditType2);
        creditTypeListZiraat.add(banks[0].creditType3);
        return creditTypeListZiraat;
    }

    public static ArrayList<String> getCreditTypeGaranti() {
        ArrayList<String> creditTypeListGaranti = new ArrayList<>();
        creditTypeListGaranti.add(banks[1].creditType1);
        creditTypeListGaranti.add(banks[1].creditType2);
        creditTypeListGaranti.add(banks[1].creditType3);

        return creditTypeListGaranti;
    }

    public static ArrayList<String> getCreditTypeIs() {
        ArrayList<String> creditTypeListIs = new ArrayList<>();
        creditTypeListIs.add(banks[2].creditType1);
        creditTypeListIs.add(banks[2].creditType2);
        creditTypeListIs.add(banks[2].creditType3);

        return creditTypeListIs;
    }

    public static Double getCreditInterestZiraat(int index) {
        ArrayList<Double> creditTypeInterestZiraat = new ArrayList<>();
        creditTypeInterestZiraat.add(banks[0].creditType1Interest);
        creditTypeInterestZiraat.add(banks[0].creditType2Interest);
        creditTypeInterestZiraat.add(banks[0].creditType3Interest);
        return creditTypeInterestZiraat.get(index);
    }


    public static Double getCreditInterestGaranti(int index) {
        ArrayList<Double> creditTypeInterestGaranti = new ArrayList<>();
        creditTypeInterestGaranti.add(banks[1].creditType1Interest);
        creditTypeInterestGaranti.add(banks[1].creditType2Interest);
        creditTypeInterestGaranti.add(banks[1].creditType3Interest);
        return creditTypeInterestGaranti.get(index);
    }

    public static Double getCreditInterestIs(int index) {
        ArrayList<Double> creditTypeInterestIs = new ArrayList<>();
        creditTypeInterestIs.add(banks[2].creditType1Interest);
        creditTypeInterestIs.add(banks[2].creditType2Interest);
        creditTypeInterestIs.add(banks[2].creditType3Interest);
        return creditTypeInterestIs.get(index);
    }

    public static ImageView getBankLogo(String bankName) {
        try {
            if (bankName == "Ziraat Bankasi")
                return banks[0].bankLogo;
            else if (bankName == "Garanti Bankasi")
                return banks[1].bankLogo;
            else if (bankName == "Is Bankasi")
                return banks[2].bankLogo;
            return null;
        } catch (Exception e) {
            System.out.println("Logo could not load");
        }
        return null;
    }


}
