package io.rybalkinsd.kotlinbootcamp.practice

/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")

/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
val association: Map<Char, String> = alphabet.associateBy { it[0].toLowerCase() }

/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
fun String.encode(): String = fold("") { acc, l -> if (association.containsKey(l)) acc.plus(association[l]) else acc.plus(l) }

/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */
val reversedAssociation: Map<String, Char> = alphabet.associate { it -> Pair(it, it[0].toLowerCase()) }

/**
 * Extension function for String which decode it according to `reversedAssociation` mapping
 *
 * @return encoded string or null if it is impossible to decode
 *
 * Example:
 * "alphabravocharlie".decode() == "abc"
 * "charliee".decode() == null
 *
 */
fun String.decode(): String? {
    var result: String = ""
    var origin = this

    while (!origin.isBlank() && origin.isNotEmpty()) {
        val key = origin[0].toLowerCase()

        if (!association.containsKey(key)) {
            if (key.isDigit() || key.isWhitespace()) {
                result = result.plus(key.toString())
                origin = origin.substring(1)
                continue
            }
            return null
        }

        val found = association[key]
        val offset = found!!.length

        if (origin.length < offset || found != origin.substring(0, offset)) return null

        result = result.plus(found[0].toString())
        if (offset > origin.length) return null

        origin = origin.substring(offset)
    }
    return result.toLowerCase()
}
