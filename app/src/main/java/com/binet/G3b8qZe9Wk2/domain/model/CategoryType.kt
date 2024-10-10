package com.binet.G3b8qZe9Wk2.domain.model

enum class CategoryType(val id: Int) {
    Insecticides(6), DressingAgent(7);

}

fun<T: CategoryType> searchCategoryTypeById(id: Int): CategoryType {
    return CategoryType.entries.first { it.id == id }
}

