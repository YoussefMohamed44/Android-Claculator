package com.example.calculator

//import android.graphics.Path
//import android.graphics.Region
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listOfNumbers:List<Button>
    private lateinit var textView: TextView
    private lateinit var addition:Button
    private lateinit var subtraction:Button
    private lateinit var multiplication:Button
    private lateinit var division:Button
    private lateinit var equal:Button
    private lateinit var listOfOperations:List<Button>
    private lateinit var leftSide:String
    private lateinit var rightSide:String
    private lateinit var op:Op
//    private var preResult:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val num0=findViewById<Button>(R.id.btn0)
        val num1=findViewById<Button>(R.id.btn1)
        val num2=findViewById<Button>(R.id.btn2)
        val num3=findViewById<Button>(R.id.btn3)
        val num4=findViewById<Button>(R.id.btn4)
        val num5=findViewById<Button>(R.id.btn5)
        val num6=findViewById<Button>(R.id.btn6)
        val num7=findViewById<Button>(R.id.btn7)
        val num8=findViewById<Button>(R.id.btn8)
        val num9=findViewById<Button>(R.id.btn9)
        textView=findViewById(R.id.result)
//        preResult=findViewById(R.id.pre_result)
        addition=findViewById(R.id.btn_plus)
        subtraction=findViewById(R.id.btn_sub)
        multiplication=findViewById(R.id.btnX)
        division=findViewById(R.id.btn_div)
        equal=findViewById(R.id.btn_res)
        listOfOperations= listOf(addition,subtraction,multiplication,division)
        listOfNumbers= listOf(num0,num1,num2,num3,num4,num5,num6,num7,num8,num9)
        displayNumbers()
        operations()
        equal.setOnClickListener {
            rightSide=textView.text.toString()
            val res = when(op){
                Op.Plus -> leftSide.toDouble() + rightSide.toDouble()
                Op.Sub-> leftSide.toDouble() - rightSide.toDouble()
                Op.Multi -> leftSide.toDouble() * rightSide.toDouble()
                Op.Div -> leftSide.toDouble() / rightSide.toDouble()
            }
            textView.text=res.toString()
        }
    }
    private fun displayNumbers():String{
        for (btn in listOfNumbers){
            btn.setOnClickListener{view ->
                val myBtn=view as? Button
                myBtn?.let {
                    val resText:String= /*if(textView.text=="0") myBtn.text.toString() else textView.text.toString() + */ myBtn.text.toString()
                    textView.text=resText
                }
            }
        }
        return textView.text.toString()
    }
    private fun operations(){
        listOfOperations.forEach{ btn ->
            btn.setOnClickListener {
                leftSide = textView.text.toString()
                textView.text=btn.text
                op= Op.entries.find { it.op==btn.text.toString()}?:Op.Plus
            }
        }
    }
    enum class Op(val op :String){
        Plus("+"),
        Multi("X"),
        Sub("-"),
        Div("÷")
    }
}