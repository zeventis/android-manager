package br.com.zeventis.managerapp.core.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date

fun String.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    val date: Date = simpleDateFormat.parse(this)

    val formattedDate = simpleDateFormat.format(date)
    val dateArray = formattedDate.split("/")

    return "${dateArray[2]}-${dateArray[1]}-${dateArray[0]}"
}