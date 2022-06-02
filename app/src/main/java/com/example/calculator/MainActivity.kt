package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private  var tvInput: TextView? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnZero: Button? = null
    private var btnDecimal: Button? = null

    private var btnAdd: Button? = null
    private  var btnSubtract: Button? = null
    private var btnMultiply: Button? = null
    private var btnDivide: Button? = null
    private var btnClear: Button? = null
    private var btnEqual: Button? = null

    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnZero = findViewById(R.id.btnZero)
        btnDecimal = findViewById(R.id.btnDecimal)

        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEqual = findViewById(R.id.btnEqual)
        btnClear = findViewById(R.id.btnClear)


    }

    fun onDigit(view: View) {


        tvInput?.append((view as Button).text)

        lastNumeric = true
    }


    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    val two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    val one = splitValue[0]
                    val two = splitValue[1]

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    val one = splitValue[0]
                    val two = splitValue[1]

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    val one = splitValue[0]
                    val two = splitValue[1]

                    if (two != "0")
                        tvInput?.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    else {
                        Toast.makeText(this, "Invalid Operand", Toast.LENGTH_SHORT).show()
                    }
                } else if (tvValue.contains("^")) {
                    val splitValue = tvValue.split("^")
                    val one = splitValue[0]
                    val two = splitValue[1]
tvInput?.text=Math.pow(one.toDouble(),two.toDouble()).toString()
                   // tvInput?.text = power(one.toInt(), two.toInt()).toString()

                }
            }
                catch (e: ArithmeticException) {
                    e.printStackTrace()
                }

        }
    }
         /*   fun power(a:Int,b:Int):Int{
                var p=1
                for(i in 1..b){
                    p*=a
                }
                return p

            }*/


    private fun removeZeroAfterDot(result: String): String {
        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)

        }
        return value

    }


    fun onClear(view: View) {
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onCut(view:View){
        var value=tvInput?.text.toString()
        if(value.isNotEmpty()) {
            if (value.endsWith("/") || value.endsWith("*") || value.endsWith("-")
                || value.endsWith("+")
            ) {
                lastNumeric = true



            }
         else   if (value.endsWith(".")) {
                lastNumeric = true
                lastDot = false
            }

            tvInput?.text = value.substring(0, value.length - 1)

            if(tvInput?.text.toString().isEmpty()){
                lastNumeric=false
                lastDot=false
            }
        }



    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric) {
                if (!isOperatorAdded(it.toString())) {
                    tvInput?.append((view as Button).text)
                    lastNumeric = false
                    lastDot = false
                }
            }else{
                var s=tvInput?.text.toString()
                if(s.isEmpty()&&(view as Button).text=="-" ){
                    tvInput?.append((view as Button).text)
                }
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")

        }
    }
    fun onTrigonometric(view: View){
        var value=0.00
        var  value1 = (view as Button).text
        if(lastNumeric){
        when(value1) {


            "Sin" -> {
                value = tvInput?.text.toString().toDouble()
                tvInput?.text = Math.sin(Math.toRadians(value)).toString()
            }
            "Cos" -> {
                value = tvInput?.text.toString().toDouble()
                tvInput?.text = Math.cos(Math.toRadians(value)).toString()

            }
            "Tan" -> {
                value = tvInput?.text.toString().toDouble()
                tvInput?.text = Math.tan(Math.toRadians(value)).toString()

            }
            "Log" -> {
                value = tvInput?.text.toString().toDouble()
                tvInput?.text = Math.log10(value).toString()

            }
            "ln" -> {

                value = tvInput?.text.toString().toDouble()
                if(value>=0.0)
                tvInput?.text = Math.log(value).toString()

            }
        }
        }

    }
    fun onFact(view: View){
        var value = tvInput?.text.toString().toInt()
        var fact=factorial(value)
        tvInput?.text=fact.toString()

    }
    fun factorial(v:Int):Int {
        return if (v == 1) {
            1
        } else {
            v * factorial(v - 1)
        }
    }
    fun onPi(view: View){
        tvInput?.text="3.142"


    }fun onSqrt(view: View){
        var value = tvInput?.text.toString().toDouble()
        tvInput?.text = Math.sqrt(value).toString()
    }
  fun onPercentage(view: View){
      var value = tvInput?.text.toString().toDouble()
      tvInput?.text =(value/100).toString()
  }
    fun onPower(view: View){
        if(lastNumeric){
            tvInput?.append("^")
            lastNumeric=false
        }
    }

}



