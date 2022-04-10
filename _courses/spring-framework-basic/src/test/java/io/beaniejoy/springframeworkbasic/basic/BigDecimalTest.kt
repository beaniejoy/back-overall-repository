package io.beaniejoy.springframeworkbasic.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.text.DecimalFormat

@DisplayName("BigDecimal 관련 테스트")
class BigDecimalTest {

    companion object {
        val ONLY_VALID_CURRENCY_REGEX = "[0-9,]*[0-9]{1,3}.[0-9]{2}".toRegex()
    }

    @Test
    @DisplayName("화폐 콤마(,) format 변환 성공 테스트")
    fun currencyParsingTest() {
        val pattern = "#,##0.0#"
        val decimalFormat = DecimalFormat(pattern)
        decimalFormat.isParseBigDecimal = true // true if the parse method returns BigDecimal

        val strDecimal = "78,123,123,445,777.001"
        val parsedDecimal = decimalFormat.parse(strDecimal) as BigDecimal

        assertEquals(parsedDecimal, BigDecimal("78123123445777.001"))
    }

    @Test
    @DisplayName("올바르지 않은 화폐 형식 입력시 RuntimeException 발생 여부 테스트")
    fun throwExceptionWhenNotValidCurrencyFormat() {
        val input = "23,124,3.3.00"
        val input2 = "23,124,3.3.00"
        val input3 = "23,1-?,029.00"
        val input4 = "23,100,09.00"

        assertThrows<RuntimeException> {
            checkValidCurrencyFormat(input)
        }
        assertThrows<RuntimeException> {
            checkValidCurrencyFormat(input2)
        }
        assertThrows<RuntimeException> {
            checkValidCurrencyFormat(input3)
        }
        assertThrows<RuntimeException> {
            checkValidCurrencyFormat(input4)
        }
    }

    @Test
    @DisplayName("올바른 화폐 형식 입력 테스트")
    fun checkValidCurrencyFormat() {
        val input = "23,124,303.00"
        val input2 = "323,124,303.00"
        val input3 = "303.00"
        val input4 = "03.00"

        checkValidCurrencyFormat(input)
        checkValidCurrencyFormat(input2)
        checkValidCurrencyFormat(input3)
        checkValidCurrencyFormat(input4)
    }

    private fun checkValidCurrencyFormat(text: String?) {
        if (text == null) {
            throw RuntimeException("BigDecimal에 대한 입력값이 null 입니다.")
        }

        if (text.matches(ONLY_VALID_CURRENCY_REGEX).not()) {
            throw RuntimeException("올바르지 않은 화폐 형식 입니다. (숫자 세 단위마다 콤마, 소수점 둘째자리까지 표시)")
        }

        val splitNumbers = text.split(",").toMutableList()

        if (splitNumbers.size > 1 && splitNumbers[0].length > 3 || splitNumbers[0].isEmpty()) {
            throw RuntimeException("올바르지 않는 형식입니다.")
        }

        splitNumbers.removeAt(0)

        splitNumbers.forEach {
            if (it.contains(".") && it.length != 6) {
                throw RuntimeException("올바르지 않는 형식입니다.")
            }

            if (it.contains(".").not() && it.length != 3) {
                throw RuntimeException("올바르지 않는 형식입니다.")
            }
        }
    }
}