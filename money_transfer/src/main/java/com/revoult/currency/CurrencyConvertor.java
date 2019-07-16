package com.revoult.currency;

import java.util.HashMap;
import java.util.Map;

import com.revoult.entity.Currency;

public class CurrencyConvertor {

	private static final Map<Currency, Double> CURRENCY_RATES_INR = new HashMap<>();

	static {
		CURRENCY_RATES_INR.put(Currency.USD, 0.015);
		CURRENCY_RATES_INR.put(Currency.GBP, 0.012);
		CURRENCY_RATES_INR.put(Currency.CAD, 0.019);
		CURRENCY_RATES_INR.put(Currency.AUD, 0.021);
		CURRENCY_RATES_INR.put(Currency.EUR, 0.013);
	}


	public static double getConversionRate(Currency fromCurrency, Currency toCurrency) {
		if (fromCurrency.equals(toCurrency)) {
			return 1;
		}
		return CURRENCY_RATES_INR.get(toCurrency)/CURRENCY_RATES_INR.get(fromCurrency);
	}

}