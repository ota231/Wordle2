package com.example.wordle2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import com.example.wordle2.FourLetterWordList.FourLetterWordList

class MainActivity : AppCompatActivity() {
    var guessNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInput = findViewById<EditText>(R.id.editTextEnterGuess)
        val buttonGuess = findViewById<Button>(R.id.buttonGuess)
        val textView1 = findViewById<TextView>(R.id.textView1)
        val textView1C = findViewById<TextView>(R.id.textView1C)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView2C = findViewById<TextView>(R.id.textView2C)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView3C = findViewById<TextView>(R.id.textView3C)
        val answer = findViewById<TextView>(R.id.textViewWord)

        buttonGuess.setOnClickListener(){
            guessNumber++
            var userGuess = userInput.getText().toString().uppercase()
            hideSoftKeyboard()
            if (guessNumber == 1) {
                textView1.text = userGuess
                textView1C.text = checkGuess(userGuess)
                textView1.visibility = View.VISIBLE
                textView1C.visibility = View.VISIBLE
                userInput.getText().clear()
            }
            else if (guessNumber == 2){
                textView2.text = userGuess
                textView2C.text = checkGuess(userGuess)
                textView2.visibility = View.VISIBLE
                textView2C.visibility = View.VISIBLE
                userInput.getText().clear()
            }
            else if (guessNumber == 3){
                textView3.text = userGuess
                textView3C.text = checkGuess(userGuess)
                textView3.visibility = View.VISIBLE
                textView3C.visibility = View.VISIBLE
                userInput.getText().clear()
                answer.text = wordToGuess
                answer.visibility = View.VISIBLE
                Toast.makeText(it.context, "You have exceeded 3 guesses :(", Toast.LENGTH_LONG).show()
                buttonGuess.setBackgroundColor(-7829368)
                buttonGuess.isEnabled = false
                userInput.isEnabled = false
            }
        }
    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    fun Activity.hideSoftKeyboard(){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}