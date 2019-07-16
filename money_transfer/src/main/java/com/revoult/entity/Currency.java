package com.revoult.entity;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public enum Currency {


    AUD, GBP, EUR, USD, CAD, INR;

    public static boolean contains(String test) {
    	return Arrays.asList(Currency.values()).stream().filter(currency 
    			-> StringUtils.equalsIgnoreCase(currency.name(), test)).count() == 1;
    }

}
