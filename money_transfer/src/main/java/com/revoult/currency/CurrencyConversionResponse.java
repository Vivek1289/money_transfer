package com.revoult.currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConversionResponse {

    private String base;
    private String date;

    private Map<String, String> rates = new HashMap<String, String>();

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}