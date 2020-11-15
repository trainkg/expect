package com.barley.finance.formula.excel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.barley.finance.formula.ArgumentFormula;

/**
 * ROUND_UP ROUND_DOWN
 * 
 * @author peculiar.1@163.com
 * @version $ID: Rounds.java, V1.0.0 2020年10月11日 上午8:35:57 $
 */
public class Rounds extends ArgumentFormula {

	public static final String ROUND_HALF = "ROUND_HALF";
	public static final String ROUND_DOWN = "ROUND_DOWN";
	public static final String ROUND_UP = "ROUND_UP";

	private static final String _INTER_FORMULA = "_INTER_FORMULA";
	private static final String _INTER_FORMULA_ARGS = "_INTER_FORMULA_ARGS";

	private Map<String, Object> analysisArgument(Object... args) {
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("arguments is empty.");
		}

		Map<String, Object> context = new HashMap<String, Object>();
		context.put(_INTER_FORMULA, String.valueOf(args[2]));
		context.put(_INTER_FORMULA_ARGS, new Object[] { args[0], args[1] });
		return context;
	}
	
	public static BigDecimal computed(Object... args) {
		return new Rounds()._computed(args);
	}

	public BigDecimal _computed(Object... args) {
		Map<String, Object> context = analysisArgument(args);
		Object[] objs 		= (Object[]) context.get(_INTER_FORMULA_ARGS);
		BigDecimal amount 	= (BigDecimal) objs[0];
		switch (String.valueOf(context.get(_INTER_FORMULA))) {
		case ROUND_UP:
			return roundUp(amount);
		case ROUND_DOWN:
			return roundDown(amount);
		case ROUND_HALF:
			return roundHalf(amount);
		default:
			break;
		}
		;
		return null;
	}

	private static BigDecimal roundUp(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}

	private static BigDecimal roundDown(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}

	private static BigDecimal roundHalf(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}

}
