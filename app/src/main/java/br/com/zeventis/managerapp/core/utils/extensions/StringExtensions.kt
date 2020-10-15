package br.com.zeventis.managerapp.core.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date

fun String.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    val date: Date = simpleDateFormat.parse(this)

    return simpleDateFormat.format(date).replace("/", "-")
}