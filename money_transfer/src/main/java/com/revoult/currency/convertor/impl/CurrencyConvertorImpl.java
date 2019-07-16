package com.revoult.currency.convertor.impl;

import com.revoult.currency.Currency;
import com.revoult.currency.convertor.CurrencyConvertor;

public class CurrencyConvertorImpl implements CurrencyConvertor {

	@Override
	public double getConvertedAmount(Currency fromCurrency,
			Currency toCurrency, double amount) {
		return (toCurrency.getExchangeRateWrtINR()/fromCurrency.getExchangeRateWrtINR()) * amount;
	}

}
