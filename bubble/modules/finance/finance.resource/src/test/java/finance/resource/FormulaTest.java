package finance.resource;

import java.math.BigDecimal;

import com.barley.finance.formula.excel.Rounds;
import com.barley.finance.formula.groovy.Logics;
import com.barley.finance.formula.impl.LoanInterest;

public class FormulaTest {
	
	public static void main(String[] args) {
		System.out.println(new LoanInterest().computed());
		System.out.println(Rounds.computed(new BigDecimal("200.126"),
												 2,
												 Rounds.ROUND_UP));
		System.out.println(Logics.computed(1));
	}	
}
