package com.revoult.currency;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public enum Currency {


    AUD(0.021), GBP(0.012), EUR(0.013), USD(0.015), CAD(0.019), INR(1);
    
    private double exchangeRateWrtINR;
    
    public double getExchangeRateWrtINR() {
		return exchangeRateWrtINR;
	}

	Currency(double exchangeRateWrtINR) {
    	this.exchangeRateWrtINR = exchangeRateWrtINR;
    }

    public static boolean contains(String test) {
    	return Arrays.asList(Currency.values()).stream().filter(currency 
    			-> StringUtils.equalsIgnoreCase(currency.name(), test)).count() == 1;
    }

}
