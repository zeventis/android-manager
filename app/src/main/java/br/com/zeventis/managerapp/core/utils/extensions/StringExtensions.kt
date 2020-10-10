package br.com.zeventis.managerapp.core.utils.extensions

fun String.theAbbreviateMonth(): String {
    val monthNames = arrayOf(
        "JAN",
        "FEV",
        "MAR",
        "ABR",
        "MAI",
        "JUN",
        "JUL",
        "AGO",
        "SET",
        "OTU",
        "NOV",
        "DEZ"
    )

    val dateString = this



    return monthNames[Integer.parseInt(this)]
}