package com.example.bt1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            showNumbers()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showNumbers() {
        val input = etNumber.text.toString()
        tvError.visibility = TextView.GONE
        if (input.isBlank() || input.toIntOrNull() == null) {
            tvError.text = "Vui lòng nhập số nguyên dương hợp lệ."
            tvError.visibility = TextView.VISIBLE
            return
        }

        val n = input.toInt()
        val numbers = when {
            rbEven.isChecked -> getEvenNumbers(n)
            rbOdd.isChecked -> getOddNumbers(n)
            rbSquare.isChecked -> getSquareNumbers(n)
            else -> emptyList()
        }

        if (numbers.isEmpty()) {
            tvError.text = "Không có số nào thỏa mãn."
            tvError.visibility = TextView.VISIBLE
        } else {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
