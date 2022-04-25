package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.generatePasswordButton)
        val plusButton: Button = findViewById(R.id.addCharacterButton)
        val minusButton: Button = findViewById(R.id.subtractCharacterButton)
        rollButton.setOnClickListener {
            generatePassword()
        }
        plusButton.setOnClickListener {
            plusLength()
        }
        minusButton.setOnClickListener {
            minusLength()
        }
        // supervise checking boxes
        val lowercaseLetters: CheckBox = findViewById(R.id.lowercaseLettersCheckBox)
        val uppercaseLetters: CheckBox = findViewById(R.id.uppercaseLettersCheckBox)
        val numerals: CheckBox = findViewById(R.id.numeralsCheckBox)
        val otherCharacters: CheckBox = findViewById(R.id.otherCharactersCheckBox)
        numerals.isChecked = true
        lowercaseLetters.setOnClickListener {
            checkBoxClicked(0)
        }
        uppercaseLetters.setOnClickListener {
            checkBoxClicked(1)
        }
        numerals.setOnClickListener {
            checkBoxClicked(2)
        }
        otherCharacters.setOnClickListener {
            checkBoxClicked(3)
        }
    }

    private fun setPasswordTextView(password: String) {
        val passwordTextView: TextView = findViewById(R.id.passwordTextView)
        passwordTextView.text = password
    }

    private fun getStringOfPossibleSymbols(): String {
        // checkBoxes
        val lowercaseLetters: CheckBox = findViewById(R.id.lowercaseLettersCheckBox)
        val uppercaseLetters: CheckBox = findViewById(R.id.uppercaseLettersCheckBox)
        val numerals: CheckBox = findViewById(R.id.numeralsCheckBox)
        val otherCharacters: CheckBox = findViewById(R.id.otherCharactersCheckBox)
        // booleans
        val lowercaseLettersBoolean: Boolean = lowercaseLetters.isChecked
        val uppercaseLettersBoolean: Boolean = uppercaseLetters.isChecked
        val numeralsBoolean: Boolean = numerals.isChecked
        val otherCharactersBoolean: Boolean = otherCharacters.isChecked
        // strings
        var stringOfPossibleSymbols: String = ""
        if (lowercaseLettersBoolean) {
            stringOfPossibleSymbols += "abcdefghijklmnopqrstuvwxyz"
        }
        if (uppercaseLettersBoolean) {
            stringOfPossibleSymbols += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        }
        if (numeralsBoolean) {
            stringOfPossibleSymbols += "1234567890"
        }
        if (otherCharactersBoolean) {
            stringOfPossibleSymbols += "!§$%&/()=?"
        }
        return stringOfPossibleSymbols
    }

    private fun setLength(addOn: Int) {
        val lengthTextView: TextView = findViewById(R.id.lengthTextView)
        val theString: String = lengthTextView.text.toString()
        var theInt: Int = theString.toInt() + addOn
        if (theInt < 1) {
            theInt = 1
        }
        lengthTextView.text = theInt.toString()
    }

    private fun getLength(): Int {
        val lengthTextView: TextView = findViewById(R.id.lengthTextView)
        val theString: String = lengthTextView.text.toString()
        return theString.toInt()
    }

    private fun minusLength() {
        setLength(-1)
    }

    private fun plusLength() {
        setLength(1)
    }

    private fun getRandomCharacterOfString(theString: String): String {
        val length: Int = theString.length
        val randomNumber: Int = Random.nextInt(1, length)
        return theString[randomNumber].toString()
    }

    private fun generatePassword() {
        val length: Int = getLength()
        var password: String = ""
        val possibleSymbols: String = getStringOfPossibleSymbols()
        for (i in 1..length) {
            password += getRandomCharacterOfString(possibleSymbols)
        }
        setPasswordTextView(password)
    }


    private fun checkBoxClicked(index: Int) {
        if (getStringOfPossibleSymbols() == "") {
            val lowercaseLetters: CheckBox = findViewById(R.id.lowercaseLettersCheckBox)
            val uppercaseLetters: CheckBox = findViewById(R.id.uppercaseLettersCheckBox)
            val numerals: CheckBox = findViewById(R.id.numeralsCheckBox)
            val otherCharacters: CheckBox = findViewById(R.id.otherCharactersCheckBox)
            when (index) {
                0 -> lowercaseLetters.isChecked = true
                1 -> uppercaseLetters.isChecked = true
                2 -> numerals.isChecked = true
                3 -> otherCharacters.isChecked = true
            }
        }
    }

}