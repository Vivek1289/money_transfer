package com.revoult.currency.convertor.impl;

import java.util.Arrays;
import java.util.List;

import com.revoult.currency.Currency;
import com.revoult.currency.convertor.CurrencyConvertor;

public class CurrencyBasedCommissionDecorator extends
		CurrencyConvertorCommissionDecorator {
	
	private static final double COMMISSION = 0.9995;
	
	private static final List<Currency> CURRENCIES_WITH_COMMISSION = Arrays.asList(Currency.CAD);
	
	private static final double MIN_COMMISSION_AMOUNT = 100;

	public CurrencyBasedCommissionDecorator(CurrencyConvertor convertor) {
		super(convertor);
	}

	@Override
	public double getConvertedAmount(Currency fromCurrency,
			Currency toCurrency, double amount) {
		double convertedAmount = super.getConvertedAmount(fromCurrency, toCurrency, amount);
		if(amount > MIN_COMMISSION_AMOUNT && CURRENCIES_WITH_COMMISSION.contains(toCurrency)) {
			return convertedAmount * COMMISSION;
		}
		return convertedAmount;
	}

}
