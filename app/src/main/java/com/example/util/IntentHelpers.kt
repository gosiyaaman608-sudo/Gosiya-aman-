package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.R

object IntentHelpers {

    fun openWhatsApp(context: Context, phone10Digits: String, message: String) {
        val cleanDigits = phone10Digits.filter { it.isDigit() }
        val fullNumber = if (cleanDigits.length == 10) "91$cleanDigits" else cleanDigits
        val encoded = Uri.encode(message)
        val uri = Uri.parse("https://wa.me/$fullNumber?text=$encoded")
        launchViewIntent(context, uri)
    }

    fun openInstagram(context: Context, url: String) {
        launchViewIntent(context, Uri.parse(url))
    }

    fun dialNumber(context: Context, phone10Digits: String) {
        val cleanDigits = phone10Digits.filter { it.isDigit() }
        val uri = Uri.parse("tel:+91$cleanDigits")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        runCatching {
            context.startActivity(intent)
        }.onFailure {
            Toast.makeText(
                context,
                context.getString(R.string.toast_no_app_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun shareText(context: Context, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser = Intent.createChooser(intent, subject)
        runCatching {
            context.startActivity(chooser)
        }.onFailure {
            Toast.makeText(
                context,
                context.getString(R.string.toast_no_app_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun copyToClipboard(context: Context, label: String, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboard?.setPrimaryClip(ClipData.newPlainText(label, text))
        Toast.makeText(context, context.getString(R.string.toast_copied), Toast.LENGTH_SHORT).show()
    }

    private fun launchViewIntent(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        runCatching {
            context.startActivity(intent)
        }.onFailure {
            Toast.makeText(
                context,
                context.getString(R.string.toast_no_app_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
