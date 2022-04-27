package io.beaniejoy.springdocexample.domain.cafe

import io.beaniejoy.springdocexample.domain.cafe.dto.CafeResponseDto
import io.beaniejoy.springdocexample.domain.cafe.dto.MenuItemDto
import io.beaniejoy.springdocexample.domain.cafe.entity.Cafe
import io.beaniejoy.springdocexample.domain.cafe.entity.MenuItem
import io.beaniejoy.springdocexample.domain.cafe.repository.CafeRepository
import io.beaniejoy.springdocexample.domain.cafe.repository.MenuItemRepository
import mu.KLogging
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeService(
    private val cafeRepository: CafeRepository,
    private val menuItemRepository: MenuItemRepository
) {

    companion object: KLogging()

    @Transactional(readOnly = true)
    fun getCafeListWith(
        id: Long?,
        name: String?,
        address: String?,
        pageable: Pageable
    ): List<CafeResponseDto> {
        return emptyList()
    }

    @Transactional
    fun registerCafe(
        name: String,
        address: String,
        menuItems: List<MenuItemDto>,
        phoneNumber: String?
    ) {
        val cafe = Cafe(name = name, address = address, phoneNumber = phoneNumber)

        val savedCafe = cafeRepository.save(cafe)

        val menuItemList = menuItems.map {
            MenuItem(name = it.name!!, price = it.price!!, cafe = savedCafe)
        }

        menuItemRepository.saveAll(menuItemList)
    }

    @Transactional(readOnly = true)
    fun getCafeById(id: Long): CafeResponseDto {
        return CafeResponseDto(
            id = id,
            name = "example cafe",
            address = "cafe address",
            menuItems = emptyList(),
            phoneNumber = "02-777-7777"
        )
    }

    @Transactional
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