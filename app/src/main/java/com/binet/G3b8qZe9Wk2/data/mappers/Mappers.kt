package com.binet.G3b8qZe9Wk2.data.mappers

import com.binet.G3b8qZe9Wk2.data.service.model.MedicationDTO
import com.binet.G3b8qZe9Wk2.domain.model.CategoryType
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import com.binet.G3b8qZe9Wk2.domain.model.MedicationMapper
import com.binet.G3b8qZe9Wk2.domain.model.searchCategoryTypeById

class MedicationMapperImpl : MedicationMapper<MedicationDTO> {

    override fun map(value: MedicationDTO): Medication = with(value) {


        return Medication(
            id = id ?: throw IllegalArgumentException("MedicationDTO doesn't have an id"),
            imageUrl = image?.replaceBefore(image.first(), imageUrlPrefix) ?: "",
            iconUrl = categories?.icon?.replaceBefore(categories.icon.first(), imageUrlPrefix)
                ?: "",
            name = name ?: throw IllegalArgumentException("MedicationDTO doesn't have a name"),
            description = description ?: "",
            categoryName = categories?.name
                ?: throw IllegalArgumentException("MedicationDTO doesn't have a category's name"),
            categoryType = searchCategoryTypeById<CategoryType>(
                categories.id
                    ?: throw IllegalArgumentException("MedicationDTO doesn't have a category's id")
            )
        )
    }

    private val imageUrlPrefix = "http://shans.d2.i-partner.ru"


}