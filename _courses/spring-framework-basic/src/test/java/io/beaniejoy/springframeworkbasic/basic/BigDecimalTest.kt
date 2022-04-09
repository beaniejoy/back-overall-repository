package io.beaniejoy.springframeworkbasic.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.regex.Pattern

class BigDecimalTest {

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
    fun containsOnlyCharsOfBigDecimal() {
        val pattern = ","

        val strDecimal = ",123,123,445,777.001"

        println(strDecimal.split(",")[0].length)

        val compile = Pattern.compile(pattern)
        val matcher = compile.matcher(strDecimal)
        println(matcher.find())
        println(matcher.find())
        println(matcher.find())
        println(matcher.find())
        println(matcher.find())

//        assertTrue(pattern(strDecimal))

//        val pattern2 = "^[0-9]{1,3},[]*$".toRegex()

    }
}