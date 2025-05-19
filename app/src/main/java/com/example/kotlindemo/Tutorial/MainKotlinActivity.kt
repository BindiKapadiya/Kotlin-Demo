package com.example.kotlindemo.Tutorial

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.kotlindemo.BaseActivity
import com.example.kotlindemo.R

class MainKotlinActivity : AppCompatActivity() {

    val TAG = MainKotlinActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.load("https://www.pixelstalk.net/wp-content/uploads/images6/Aesthetic-Wallpaper-Neon-Phone.jpg") {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        callMe()
    }

    fun callMe() {
//        A01_Variables().main()
//        A02_Operators().main()
//        A03_IfElse().main()
//        A04_Range().main()
//        A05_When().main()
//        A06_Loops().main()
//        A07_Functions().main()
//        A08_Arrays().main()
//        A09_Classes().main()
//        A10_GetterSetter().main()
//        A11_Inheritance().main()
//        A12_Polimorphism().main()
//        A13_Abstract().main()
//        A14_Interface().main()
//        A15_Casting().main()
//        A16_Modifiers().main()
//        A17_ObjectKeyword().main()
//        A18_Companion().main()
//        A19_Factory().main()
//        A20_DataClasses().main()
//        A21_EnumSealed().main()
//        A22_NullSafety().main()
//        A23_Exceptions().main()
//        A24_Collections().main()
//        A25_Lambdas().main()
//        A26_CollectionFunctions().main()
//        A27_Extensions().main()
//        A28_ScopeFunctions().main()
//        A29_Generics().main()
//        A30_Nesting().main()
        A31_Inline().main()
//        A32_Interview().main()

//        plusMinus(arrayOf(-4, 3, -9, 0, 4, 1))
    }

    fun plusMinus2(arr: Array<Int>) {
        val counts = arr.filter { it in -100..100 }.groupBy {
            when {
                it > 0 -> "positive"
                it < 0 -> "negative"
                else -> "zero"
            }
        }.mapValues { it.value.size }

        println("$TAG Positive : ${counts["positive"]?.toDouble() ?: 0.0 / arr.size}")
        println("$TAG Negative : ${counts["negative"]?.toDouble() ?: 0.0 / arr.size}")
        println("$TAG Zero : ${counts["zero"]?.toDouble() ?: 0.0 / arr.size}")
    }

    fun plusMinus(arr: Array<Int>): Unit {
        // Write your code here

        var zero = 0
        var positive = 0
        var negative = 0
        val size = arr.size

        arr.forEach {
            if (it in -100..100) {
                when {
                    it > 0 -> positive++
                    it < 0 -> negative++
                    else -> zero++
                }
            }
        }

        println("%.6f".format(positive.toDouble() / size))
        println("%.6f".format(negative.toDouble() / size))
        println("%.6f".format(zero.toDouble() / size))
    }
}