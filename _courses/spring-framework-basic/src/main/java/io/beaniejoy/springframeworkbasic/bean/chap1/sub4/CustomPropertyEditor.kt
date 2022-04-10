package io.beaniejoy.springframeworkbasic.bean.chap1.sub4

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor
import java.beans.PropertyEditorSupport
import java.text.SimpleDateFormat
import java.util.*

// https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection
class CustomDateEditorRegistrar : PropertyEditorRegistrar {
    override fun registerCustomEditors(registry: PropertyEditorRegistry) {
        registry.registerCustomEditor(
            Date::class.java,
            CustomDateEditor(SimpleDateFormat("yyyy/MM/dd"), false)
        )
    }
}

class CustomEmailEditor : PropertyEditorSupport() {
    companion object {
        val EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$".toRegex()
    }

    override fun setAsText(text: String?) {
        if (text == null || text.matches(EMAIL_REGEX).not()) {
            throw IllegalArgumentException("Email에 대한 올바른 입력값이 아닙니다.")
        }

        val splitEmail = text.split("@")

        value = Email(
            userId = splitEmail[0],
            address = splitEmail[1]
        )
    }
}

// 세 자리 단위 콤마(,) 형식의 화폐 형식 적용
// ex) "23,029,213,984.00"
//class CustomBigDecimalEditor : PropertyEditorSupport() {
//    companion object {
//        val ONLY_VALID_CURRENCY_REGEX = "[0-9,]*[0-9]{1,3}.[0-9]{2}".toRegex()
//        const val BIGDECIMAL_PATTERN = "#,##0.0#"
//    }
//
//    override fun setAsText(text: String?) {
//        if (text == null) {
//            throw IllegalArgumentException("BigDecimal에 대한 입력값이 null 입니다.")
//        }
//
//        if (text.matches(ONLY_VALID_CURRENCY_REGEX).not()) {
//            throw IllegalArgumentException("올바르지 않은 화폐 형식 입니다. (숫자 세 단위마다 콤마, 소수점 둘째자리까지 표시)")
//        }
//
//        val splitNumbers = text.split(",").toMutableList()
//
//        if (splitNumbers.size > 1 && splitNumbers[0].length > 3 || splitNumbers[0].isEmpty()) {
//            throw IllegalArgumentException("올바르지 않는 형식입니다.")
//        }
//
//        splitNumbers.removeAt(0)
//
//        splitNumbers.forEach {
//            if (it.contains(".") && it.length != 6) {
//                throw IllegalArgumentException("올바르지 않는 형식입니다.")
//            }
//
//            if (it.contains(".").not() && it.length != 3) {
//                throw IllegalArgumentException("올바르지 않는 형식입니다.")
//            }
//        }
//
//        val pattern = "#,##0.0#"
//        val decimalFormat = DecimalFormat(BIGDECIMAL_PATTERN)
//        decimalFormat.isParseBigDecimal = true
//
//        println(text)
//        value = decimalFormat.parse(text) as BigDecimal
//    }
//}