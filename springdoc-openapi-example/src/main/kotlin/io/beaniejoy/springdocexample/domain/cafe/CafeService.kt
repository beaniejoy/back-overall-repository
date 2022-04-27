package io.beaniejoy.springdocexample.domain.cafe

import io.beaniejoy.springdocexample.domain.cafe.dto.CafeResponseDto
import io.beaniejoy.springdocexample.domain.cafe.dto.MenuItemDto
import mu.KLogging
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CafeService {

    companion object: KLogging()

    fun getCafeListWith(
        id: Long?,
        name: String?,
        address: String?,
        pageable: Pageable
    ): List<CafeResponseDto> {
        return emptyList()
    }

    fun registerCafe(
        name: String,
        address: String,
        menuItems: List<MenuItemDto>,
        phoneNumber: String?
    ) {
        logger.info { "Create New Cafe: $name, $address, $phoneNumber" }
    }

    fun getCafeById(id: Long): CafeResponseDto {
        return CafeResponseDto(
            id = id,
            name = "example cafe",
            address = "cafe address",
            menuItems = emptyList(),
            phoneNumber = "02-777-7777"
        )
    }

    fun updateCafe(
        id: Long,
        name: String?,
        address: String?,
        phoneNumber: String?
    ): Long {
        logger.info { "Update Cafe[$id]: $name, $address, $phoneNumber" }

        return id
    }

}