package com.example.pcsgroup.util

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

fun String.toDateFormat(f: String): String {
    val simpleDateFormat = SimpleDateFormat(f, Locale.getDefault())
    return try {
        simpleDateFormat.parse(this) ?: toDateGlobalFormat() ?: Date()
        simpleDateFormat.format(this)
    } catch (e: ParseException) {
        simpleDateFormat.format(toDateGlobalFormat() ?: Date())
    }
}

fun String.toDateGlobalFormat(): Date? {
    val knownPatterns = ArrayList<SimpleDateFormat>()
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        )
    ) // ISO_LOCAL_DATE_TIME
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd' 'HH:mm:ss",
            Locale.getDefault()
        )
    ) // ISO_LOCAL_DATE_TIME
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssZ",
            Locale.getDefault()
        )
    ) // ISO_OFFSET_DATE_TIME
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )
    ) // ISO_INSTANT
    knownPatterns.add(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())) // ISO_INSTANT
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm.ss'Z'",
            Locale.getDefault()
        )
    ) // ISO_INSTANT (with single dot)
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
            Locale.getDefault()
        )
    ) // ISO_INSTANT (with single dot)
    knownPatterns.add(
        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SZZZZZ",
            Locale.getDefault()
        )
    ) // ISO_INSTANT (with single dot)
    knownPatterns.add(
        SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        )
    ) // ISO_INSTANT (with single dot)
    knownPatterns.add(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()))
    knownPatterns.add(SimpleDateFormat("d/M/yyyy", Locale.getDefault()))
    knownPatterns.add(SimpleDateFormat("yyyy-MM", Locale.getDefault()))
    knownPatterns.add(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()))
    knownPatterns.add(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()))
    for (pattern in knownPatterns) {
        try {
            pattern.parse(this)?.let {
                return it
            }
        } catch (pe: Exception) {
            pe.printStackTrace()
        }
    }
    return null
}