package com.bubble.finance.formula

import com.barley.finance.formula.excel.Rounds

class GroovyFormula {
	def computed(BigDecimal amount1, BigDecimal amount2) {
		println(amount1)
		return Rounds.computed(amount1,2,Rounds.ROUND_UP);
	}
}
