package br.com.zeventis.producer.core.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date

fun String.formatDateToBackendFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    val date: Date = simpleDateFormat.parse(this.replace(" ", ""))

    val formattedDate = simpleDateFormat.format(date)
    val dateArray = formattedDate.split("/")

    return "${dateArray[2]}-${dateArray[1]}-${dateArray[0]}"
}

fun String.unmask(): String {
    return this.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
        .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
        .replace("[)]".toRegex(), "").replace(" ".toRegex(), "")
        .replace(",".toRegex(), "")
}