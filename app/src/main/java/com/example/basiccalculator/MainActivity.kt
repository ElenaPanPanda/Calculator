package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.example.basiccalculator.databinding.ActivityMainBinding

class Display() {

}

enum class Operation(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    NOTHING("")
}

data class EqualButton(
    val result: String,
    val secondTern: String
)


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.displayEditText.inputType = InputType.TYPE_NULL

        var operation = Operation.NOTHING
        var firstTerm = ""

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
            operation = Operation.NOTHING
        }

        binding.addButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            operation = Operation.ADD
        }

        binding.subtractButton.setOnClickListener {
            if (!isUnaryMinus()) {
                firstTerm = clickOperationButton(firstTerm, operation)
                operation = Operation.SUBTRACT
            } else {
                binding.displayEditText.setText("-")
            }
        }

        binding.multiplyButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            operation = Operation.MULTIPLY
        }

        binding.divideButton.setOnClickListener {
            firstTerm = clickOperationButton(firstTerm, operation)
            operation = Operation.DIVIDE
        }

        binding.equalButton.setOnClickListener {
            firstTerm = clickEqualButton(firstTerm, operation)
        }
    }

    private fun clickDigitButton(digit: String) {
        val displayText = binding.displayEditText.text.toString()

        if (displayText == "0") {
            binding.displayEditText.setText(digit)
        } else {
            binding.displayEditText.setText(displayText + digit)
        }

        //Log.d("dot in digit button", "text = ${binding.displayEditText.text}")
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

        //Log.d("dot in dot button", binding.displayEditText.text.toString())
    }

    private fun clickOperationButton(firstTerm: String, operation: Operation): String {
        val result: String

        // n + n + n
        /*if (firstTerm != "") {
            Log.d("div", "firstTerm != \"\"")

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

        //Log.d("dot", "firstTerm = $firstTerm")
        //Log.d("dot", "operation = $operation")

        val displayText =
            if (binding.displayEditText.text.toString() == "") {
                binding.displayEditText.hint.toString()
            } else {
                binding.displayEditText.text.toString()
            }

        //Log.d("dot", "displayText = $displayText")

        binding.displayEditText.setText("")


        if (isDouble(displayText)) {
            result = displayText.toDouble().toString()
        } else {
            result = displayText.toDouble().toInt().toString()
        }


        binding.displayEditText.hint = result

        return result
    }

    private fun clickEqualButton(firstTerm: String, operation: Operation): String {
        val displayText: String = if (binding.displayEditText.text.toString() == "") {

            // I'm never here

            "0"

        } else {
            binding.displayEditText.text.toString()

        }

        //Log.d("div", "firstTerm = $firstTerm")
        //Log.d("div", "operation = $operation")
        //Log.d("div", "displayText = $displayText")

        val result = when (operation) {
            Operation.ADD -> firstTerm.toDouble() + displayText.toDouble()
            Operation.SUBTRACT -> firstTerm.toDouble() - displayText.toDouble()
            Operation.MULTIPLY -> firstTerm.toDouble() * displayText.toDouble()
            Operation.DIVIDE -> firstTerm.toDouble() / displayText.toDouble()
            Operation.NOTHING -> displayText.toDouble()
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

}


// 1 + 2 = + 6 = + 1.5 = * 2 = /


















