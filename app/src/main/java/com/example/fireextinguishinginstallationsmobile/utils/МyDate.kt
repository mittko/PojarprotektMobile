package com.example.fireextinguishinginstallationsmobile.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class MyDate {
    fun GetReversedSystemDate(): String {
        // 1. Вземаме текущата дата и час
        val calendar = Calendar.getInstance()

// 2. Дефинираме желания формат (bg за България, за да няма изненади с локализацията)
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale("bg"))

// 3. Форматираме датата
        return formatter.format(calendar.time)
    }
}
