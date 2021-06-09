package com.example.coinstats.Model;

public class CoinStructure {

    public String id;
    public String icon;
    public String name;
    public String symbol;
    public String price;
    public String priceBtc;
    public String priceChange1h;
    public String priceChange1d;
    public String priceChange1w;

    public CoinStructure(String id, String icon, String name, String symbol, String price, String priceBtc, String priceChange1h, String priceChange1d, String priceChange1w) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.priceBtc = priceBtc;
        this.priceChange1h = priceChange1h;
        this.priceChange1d = priceChange1d;
        this.priceChange1w = priceChange1w;
    }

    public CoinStructure(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String getPriceChange1h() {
        return priceChange1h;
    }

    public void setPriceChange1h(String priceChange1h) {
        this.priceChange1h = priceChange1h;
    }

    public String getPriceChange1d() {
        return priceChange1d;
    }

    public void setPriceChange1d(String priceChange1d) {
        this.priceChange1d = priceChange1d;
    }

    public String getPriceChange1w() {
        return priceChange1w;
    }

    public void setPriceChange1w(String priceChange1w) {
        this.priceChange1w = priceChange1w;
    }
}
