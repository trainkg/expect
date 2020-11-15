package com.barley.finance.formula.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.barley.CalendarUtil;

import com.barley.finance.formula.LifecyleFormula;

/**
 * 贷款利息
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoanInterest.java, V1.0.0 2020年9月28日 下午8:38:33 $
 */
public class LoanInterest extends LifecyleFormula {

	private BigDecimal principal;
	private BigDecimal rate;
	private Date calcDate;
	private Date lastBalanceDate;

	@Override
	public void initialize() {
		//
		principal = new BigDecimal("100");
		rate = new BigDecimal("0.06");
		lastBalanceDate = new Date();
		calcDate = DateUtils.addDays(lastBalanceDate, 3);
	}

	@Override
	public BigDecimal computed() {
		if (principal == null) {
			throw new IllegalArgumentException("principal is empty");
		}
		if (rate == null) {
			throw new IllegalArgumentException("rate is empty");
		}
		return principal
				.multiply(BigDecimal.ONE.add(rate.divide(new BigDecimal("365"), 9, BigDecimal.ROUND_HALF_DOWN))
						.pow(CalendarUtil.daysBetween(calcDate, lastBalanceDate, Calendar.getInstance())))
				.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

}
