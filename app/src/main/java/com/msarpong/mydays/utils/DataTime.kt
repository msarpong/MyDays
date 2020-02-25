package com.msarpong.mydays.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDateTime(originalFormat: String, ouputFormat: String): String {
    val date =
        LocalDateTime.parse(this, DateTimeFormatter.ofPattern(originalFormat, Locale.ITALIAN))
    return date.format(DateTimeFormatter.ofPattern(ouputFormat, Locale.ITALIAN))
}

fun getDate(pattern: String): String {
    Locale.setDefault(Locale.ITALIAN)
    val current = SimpleDateFormat(pattern)
    val todayDate = current.format(Date())
    return capitalize(todayDate)
}

fun convertDate(
    DateString: String,
    oldPattern: String = "dd/MM/yyyy",
    newPattern: String = "dd MMMM yyyy"
): String {
    Locale.setDefault(Locale.ITALIAN)

     val newDateString: String

    val sdf = SimpleDateFormat(oldPattern)
    val d = sdf.parse(DateString)
    sdf.applyPattern(newPattern)
    newDateString = sdf.format(d)
    return newDateString

}

fun capitalize(line: String): String {
    return Character.toUpperCase(line[0]) + line.substring(1)
}
