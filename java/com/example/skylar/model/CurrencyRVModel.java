package com.example.skylar.model;

public class CurrencyRVModel {
    private String currencyName;
    private String symbol;
    private double balanceStellar;
    private double prize;
    private double balanceDollar;



    public CurrencyRVModel(String currencyName,String symbol, double balanceStellar, double prize, double balanceDollar) {
        this.currencyName = currencyName;
        this.balanceStellar = balanceStellar;
        this.prize = prize;
        this.balanceDollar = balanceDollar;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getBalanceStellar() {
        return balanceStellar;
    }

    public void setBalanceStellar(double balanceStellar) {
        this.balanceStellar = balanceStellar;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public double getBalanceDollar() {
        return balanceDollar;
    }

    public void setBalanceDollar(double balanceDollar) {
        this.balanceDollar = balanceDollar;
    }
}
