package com.revoult.currency.convertor.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.revoult.currency.Currency;
import com.revoult.currency.convertor.CurrencyConvertor;

public class DayBasedCurrencyCommissionDecorator extends
		CurrencyConvertorCommissionDecorator {
	
	private static final double WEEKEND_RATE = 0.9995;
	
	private static final double WEEKDAY_RATE = 1;

	public DayBasedCurrencyCommissionDecorator(CurrencyConvertor convertor) {
		super(convertor);
	}

	@Override
	public double getConvertedAmount(Currency fromCurrency,
			Currency toCurrency, double amount) {
		
		double convertedAmount = super.getConvertedAmount(fromCurrency, toCurrency, amount);
		LocalDate todayDate = LocalDate.now();
		if(todayDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
				todayDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			return WEEKEND_RATE * convertedAmount;
		} 
		return WEEKDAY_RATE * convertedAmount;
	}

}
