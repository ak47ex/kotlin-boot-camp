package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
fun max(values: List<Int>): Int {
    if (values.isEmpty()) throw IllegalArgumentException("Values should be contain atleast one element")
    var max = values.first()
    values.forEach { if (it > max) max = it }
    return max
}

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long {
    if (values.isEmpty()) return 0

    var sum: Long = 0
    values.forEach { sum += it }

    return sum
}