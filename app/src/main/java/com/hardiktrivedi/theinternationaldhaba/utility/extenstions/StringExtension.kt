package com.hardiktrivedi.theinternationaldhaba.utility.extenstions

internal fun String.formatCookingInstruction(): String {
    return this.split(".", "\r\n", "\r\n\r\n").filter {
        it.isNotEmpty() // To remove any empty spaces
    }.mapIndexed { index, string ->
        "${index.plus(1)}. ${string.trim()}.\n" // Add numbering followed by cooking instruction and new line character.
    }.joinToString("\n")
}
