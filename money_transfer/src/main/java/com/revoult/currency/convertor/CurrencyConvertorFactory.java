package com.revoult.currency.convertor;

import com.revoult.currency.convertor.impl.CurrencyBasedCommissionDecorator;
import com.revoult.currency.convertor.impl.CurrencyConvertorImpl;
import com.revoult.currency.convertor.impl.DayBasedCurrencyCommissionDecorator;

/**
 * 
 * @author Vivek Grewal
 *
 */
public final class CurrencyConvertorFactory {
	
	
	private CurrencyConvertorFactory() {
		
	}
	
	public static final CurrencyConvertor getCurrencyConvertor(boolean isCommissionFlagOn) {
		if(isCommissionFlagOn) {
			return new CurrencyBasedCommissionDecorator(new DayBasedCurrencyCommissionDecorator(
					new CurrencyConvertorImpl()));
		} else {
			return new CurrencyConvertorImpl();
		}
	}

}
