package io.beaniejoy.springframeworkbasic.config.bean

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor
import java.beans.PropertyEditorSupport
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*

// https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection
class CustomDateEditorRegistrar : PropertyEditorRegistrar {
    override fun registerCustomEditors(registry: PropertyEditorRegistry) {
        registry.registerCustomEditor(
            Date::class.java,
            CustomDateEditor(SimpleDateFormat("yyyy-MM-dd"), false)
        )
    }
}

class CustomBigDecimalEditor : PropertyEditorSupport() {
    override fun setAsText(text: String?) {
        if (text == null) {
            throw RuntimeException("BigDecimal에 대한 입력값이 null 입니다.")
        }

        val splitNumbers = text.split(",").toMutableList()
        if (splitNumbers[0].length > 3 || splitNumbers[0].isEmpty()) {
            throw RuntimeException("올바르지 않는 형식입니다.")
        }

        splitNumbers.removeAt(0)
        splitNumbers.removeAt(splitNumbers.size - 1)

        splitNumbers.forEach {
            if (it.length > 3 || it.length < 1)
                throw RuntimeException("올바르지 않는 형식입니다.")
        }
    }
}