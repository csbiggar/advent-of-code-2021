package helpers

fun <T> List<T>.second(): T {
    if (this.size < 2) throw NoSuchElementException("List has less than 2 elements")
    return this[1]
}