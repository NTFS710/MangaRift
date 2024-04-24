package com.sephirita.mangarift.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.toDate(): String =
    OffsetDateTime.parse(
        this,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    ).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

fun Float.formatChapterNumber(): String =
    if (this % this.toInt() > 0) this.toString() else this.toInt().toString()

