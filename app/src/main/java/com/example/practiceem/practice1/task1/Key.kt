package com.example.practiceem.practice1.task1


/*
Возможные проблемы:

    1) Ключ (hashCode) будет генерироваться по field1 и field2, но field2 может быть
    изменен (так как var) после добавления объекта в HashMap. В результате объект нельзя будет найти
    или удалить по ключу, так как изменился hashCode.

    2) Также field3 не будет использоваться в генерации hashCode() и equals(), так как не входит в
    первичный конструктор, поэтому два объекта с одинаковыми field1 и field2, но разными field3
    будут равны.

*/

data class Key(
val field1: Int,
var field2: String
) {
    var field3: String? = null
}