package com.revoult.currency.convertor;

import com.revoult.currency.Currency;

public interface CurrencyConvertor {

	public double getConvertedAmount(Currency fromCurrency, Currency toCurrency, double amount);
}
