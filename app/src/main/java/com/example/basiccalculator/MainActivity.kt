package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
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
       val result: String

       if (firstTerm != "") {
           result = clickEqualButton(firstTerm, operation)
       } else {
           val displayText =
               if (binding.displayEditText.text.toString() != "") {
                   binding.displayEditText.text.toString()
               } else
                   binding.displayEditText.hint.toString()


           result = if (displayText == "") "0.0" else displayText
           binding.displayEditText.setText("")
           binding.displayEditText.hint = result.toDouble().toString()
       }

       return result
    }

    /*private fun clickOperationButton(): String {
        val displayText: String =
            if (binding.displayEditText.text.toString() != "") {
                binding.displayEditText.text.toString()
            } else
                binding.displayEditText.hint.toString()

        var firstTerm = ""

        firstTerm = if (displayText == "") "0.0" else displayText
        binding.displayEditText.setText("")
        binding.displayEditText.hint = firstTerm.toDouble().toString()

        return firstTerm
    }*/

    private fun clickEqualButton(firstTerm: String, operation: Operation): String {
        val displayText = binding.displayEditText.text.toString()

        val result = when (operation) {
            Operation.ADD -> firstTerm.toDouble() + displayText.toDouble()
            Operation.SUBTRACT -> firstTerm.toDouble() - displayText.toDouble()
            Operation.MULTIPLY -> firstTerm.toDouble() * displayText.toDouble()
            Operation.DIVIDE -> firstTerm.toDouble() / displayText.toDouble()
            Operation.NOTHING -> displayText
        }

        binding.displayEditText.setText("")
        binding.displayEditText.hint = result.toString()

        return result.toString()
    }

    private fun isUnaryMinus(): Boolean = binding.displayEditText.text.toString() == ""
}





















