package com.example.fireextinguishinginstallationsmobile.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.Locale

class SpeechToTextManager(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer? = null

    fun startListening(onResult: (String) -> Unit, onListeningStateChanged: (Boolean) -> Unit) {

        // Destroy existing instance if active
        // stopListening()


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onBeginningOfSpeech() {}

                override fun onBufferReceived(p0: ByteArray?) {}

                override fun onEndOfSpeech() {
                    onListeningStateChanged(false)
                }

                override fun onError(p0: Int) {
                    onListeningStateChanged(false)
                }

                override fun onEvent(p0: Int, p1: Bundle?) {}

                override fun onPartialResults(partialResults: Bundle?) {
                    // Optional: Stream real-time matching words into textfield

                    val matches =
                        partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        onResult(matches[0])// Return the best text match
                    }
                }

                override fun onReadyForSpeech(p0: Bundle?) {
                    onListeningStateChanged(true)
                }

                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        onResult(matches[0])
                    }
                }

                override fun onRmsChanged(p0: Float) {}

            })
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true) // For fast text updates
        }

        speechRecognizer?.startListening(intent)
    }

    fun stopListening() {
        speechRecognizer?.stopListening()
        speechRecognizer?.destroy()
        speechRecognizer = null
    }
}