package ru.topbun.pawmate.entity

enum class PetType {
    CAT, DOG;

    override fun toString(): String {
        return when(this){
            CAT -> "Кошка"
            DOG -> "Собака"
        }
    }
}