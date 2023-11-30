package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.example.basiccalculator.databinding.ActivityMainBinding


enum class Operation(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    NOTHING("")
}

const val FIRST_TERM = "first_term"
const val SECOND_TERM = "second_term"
const val OPERATION = "operation"


class MainActivity : AppCompatActivity() {

    private var operation = Operation.NOTHING
    private var firstTerm = ""
    private var secondTerm = ""

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*if (savedInstanceState != null) {
            firstTerm = savedInstanceState.getString(FIRST_TERM, "")
            secondTerm = savedInstanceState.getString(SECOND_TERM, "")
        }*/

        binding.displayEditText.inputType = InputType.TYPE_NULL


        binding.button1.setOnClickListener {
            clickDigitButton(binding.button1.text.toString())
        }

        binding.button2.setOnClickListener {
            clickDigitButton(binding.button2.text.toString())
        }

        binding.button3.setOnClickListener {
            clickDigitButton(binding.button3.text.toString())
        }

        binding.button4.setOnClickListener {
            clickDigitButton(binding.button4.text.toString())
        }

        binding.button5.setOnClickListener {
            clickDigitButton(binding.button5.text.toString())
        }

        binding.button6.setOnClickListener {
            clickDigitButton(binding.button6.text.toString())
        }

        binding.button7.setOnClickListener {
            clickDigitButton(binding.button7.text.toString())
        }

        binding.button8.setOnClickListener {
            clickDigitButton(binding.button8.text.toString())
        }

        binding.button9.setOnClickListener {
            clickDigitButton(binding.button9.text.toString())
        }

        binding.button0.setOnClickListener {
            clickDigitButton(binding.button0.text.toString())
        }

        binding.dotButton.setOnClickListener {
            clickDotButton()
        }

        binding.clearButton.setOnClickListener {
            binding.displayEditText.setText("")
            binding.displayEditText.hint = "0"
            firstTerm = ""
            secondTerm = ""
            operation = Operation.NOTHING
        }

        binding.addButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            if (secondTerm == "") secondTerm = firstTerm
            operation = Operation.ADD
        }

        binding.subtractButton.setOnClickListener {
            if (!isUnaryMinus()) {
                firstTerm = clickOperationButton(firstTerm, operation)
                if (secondTerm == "") secondTerm = firstTerm
                operation = Operation.SUBTRACT
            } else {
                binding.displayEditText.setText("-")
            }
        }

        binding.multiplyButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            if (secondTerm == "") secondTerm = firstTerm
            operation = Operation.MULTIPLY
        }

        binding.divideButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            if (secondTerm == "") secondTerm = firstTerm
            operation = Operation.DIVIDE
        }

        binding.equalButton.setOnClickListener {
            secondTerm = findSecondTerm(secondTerm)

            firstTerm = clickEqualButton(firstTerm, secondTerm, operation)

        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(FIRST_TERM, firstTerm)
        outState.putString(SECOND_TERM, secondTerm)

    }*/

    private fun clickDigitButton(digit: String) {
        val displayText = binding.displayEditText.text.toString()

        if (displayText == "0") {
            binding.displayEditText.setText(digit)
        } else {
            binding.displayEditText.setText(displayText + digit)
        }
    }

    private fun clickDotButton() {
        val displayText = binding.displayEditText.text.toString()
        val dotsNumber = displayText.count { it == '.' }

        if (dotsNumber > 0) {
            return
        } else {
            if (displayText == "0" || displayText == "") {
                binding.displayEditText.setText("0.")
            } else {
                binding.displayEditText.setText(displayText + ".")
            }
        }
    }

    private fun clickOperationButton(firstTerm: String, operation: Operation): String {
        val newFirstTerm: String

        // n + n + n
        /*if (firstTerm != "") {
            result = clickEqualButton(firstTerm, operation)
        } else {
            val displayText =
                if (binding.displayEditText.text.toString() == "") {
                    binding.displayEditText.hint.toString()
                } else {
                    binding.displayEditText.text.toString()
                }

            binding.displayEditText.setText("")

            result = if (isDouble(displayText)) {
                displayText.toDouble().toString()
            } else {
                displayText.toInt().toString()
            }

            binding.displayEditText.hint = result
        }*/

        val displayText =
            if (binding.displayEditText.text.toString() == "") {
                binding.displayEditText.hint.toString()
            } else {
                binding.displayEditText.text.toString()
            }


        binding.displayEditText.setText("")

        newFirstTerm = if (isDouble(displayText)) {
            displayText.toDouble().toString()
        } else {
            displayText.toDouble().toInt().toString()
        }

        binding.displayEditText.hint = newFirstTerm

        return newFirstTerm
    }

    private fun clickEqualButton(
        firstTerm: String,
        secondTerm: String,
        operation: Operation
    ): String {
        /*val secondTerm: String

        if (binding.displayEditText.text.toString() == "") {

            // I'm never here

            secondTerm = "0"

        } else {
            secondTerm = binding.displayEditText.text.toString()
        }*/

        val result = when (operation) {
            Operation.ADD -> firstTerm.toDouble() + secondTerm.toDouble()
            Operation.SUBTRACT -> firstTerm.toDouble() - secondTerm.toDouble()
            Operation.MULTIPLY -> firstTerm.toDouble() * secondTerm.toDouble()
            Operation.DIVIDE -> firstTerm.toDouble() / secondTerm.toDouble()
            Operation.NOTHING -> secondTerm.toDouble()
        }

        binding.displayEditText.setText("")

        val resultString: String = if (isDouble(result.toString())) {
            result.toString()
        } else {
            result.toInt().toString()
        }

        binding.displayEditText.hint = resultString

        return resultString
    }

    private fun isUnaryMinus(): Boolean = binding.displayEditText.text.toString() == ""

    private fun isDouble(number: String): Boolean {
        val rem = number.toDouble() - number.toDouble().toInt()
        return rem != 0.0
    }

    private fun findSecondTerm(secondTerm: String): String {
        var newSecondTerm = secondTerm

        if (binding.displayEditText.text.toString() != "") {
            newSecondTerm = binding.displayEditText.text.toString()
        }

        return newSecondTerm
    }

}


















