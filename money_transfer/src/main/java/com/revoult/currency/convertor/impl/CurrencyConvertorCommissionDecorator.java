package com.revoult.currency.convertor.impl;

import com.revoult.currency.Currency;
import com.revoult.currency.convertor.CurrencyConvertor;

public abstract class CurrencyConvertorCommissionDecorator implements CurrencyConvertor {

	private CurrencyConvertor convertor;
	
	private static final double BASE_COMMISSION = 1;
	
	public CurrencyConvertorCommissionDecorator(CurrencyConvertor convertor) {
		super();
		this.convertor = convertor;
	}

	@Override
	public double getConvertedAmount(Currency fromCurrency,
			Currency toCurrency, double amount) {
		return BASE_COMMISSION * convertor.getConvertedAmount(fromCurrency, toCurrency, amount);
	}


}
