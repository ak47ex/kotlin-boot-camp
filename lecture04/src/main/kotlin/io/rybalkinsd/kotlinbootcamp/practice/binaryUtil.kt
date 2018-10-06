package io.rybalkinsd.kotlinbootcamp.practice

import java.math.BigInteger

/**
 * "8".toBinary() == "1000"
 *
 * @throws NumberFormatException if could not be parsed as Number
 * @throws IllegalArgumentException if target string is null
 */
fun String?.toBinary(): String = BigInteger(requireNotNull(this)).toString(2)