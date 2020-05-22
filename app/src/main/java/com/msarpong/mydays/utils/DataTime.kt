package com.msarpong.mydays.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


val LOCALE = Locale.setDefault(Locale.ITALIAN)

fun String.formatDateTime(originalFormat: String, ouputFormat: String): String {
    val date = LocalDateTime.parse(this, DateTimeFormatter.ofPattern(originalFormat))
    return date.format(DateTimeFormatter.ofPattern(ouputFormat))
}

fun getDate(pattern: String): String {
    val currentDate = LocalDate.now()
    return currentDate.format(DateTimeFormatter.ofPattern(pattern))
}

fun getTime(pattern: String): String {
    val currentTime: LocalTime = LocalTime.now()
    return currentTime.format(DateTimeFormatter.ofPattern(pattern))
}

fun getDateTime(pattern: String): String {
    val date = LocalDate.now()
    val time = LocalTime.now()
    val dateTime = LocalDateTime.of(date, time)
    return dateTime.format(DateTimeFormatter.ofPattern(pattern)).toString()

}

fun calendarDate(year: Int, month: Int, dayOfMonth: Int): String {
    val dateTime = LocalDate.of(year, month + 1, dayOfMonth)
    return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

fun capitalize(line: String): String {
    return Character.toUpperCase(line[0]) + line.substring(1)
}