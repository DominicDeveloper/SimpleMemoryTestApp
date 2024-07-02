package com.asadbek.simplememorytestapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asadbek.simplememorytestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var gridLayout: GridLayout
    lateinit var linearLayoutParams: LayoutParams
    var numbersList = arrayListOf("nineteen","two","three")
    var startingIndex = 0
    var wordList = ArrayList<String>()
    var randomizedWords = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        gridLayout = binding.linearLayout2
        linearLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)


        binding.addViews.setOnClickListener {
            if (startingIndex <= numbersList.lastIndex){
                createViewsByQues(startingIndex)
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun createViewsByQues(index:Int){
        putItOnRandomizedWords(numbersList[index])
        numbersList[index].forEach {
            wordList.add("$it")
            val button = Button(this)
            var randomNumber = 0
            if (randomizedWords.isNotEmpty()){
                randomNumber = (0 .. randomizedWords.size-1).random()
                Toast.makeText(this,  "$randomNumber", Toast.LENGTH_SHORT).show()
            }else{
                randomNumber = 0
            }
            button.text = randomizedWords[randomNumber]
            randomizedWords.removeAt(randomNumber)
            gridLayout.addView(button,linearLayoutParams)
            button.setOnClickListener {
                Toast.makeText(this, "${button.text}", Toast.LENGTH_SHORT).show()
                if (button.text == wordList[0]){
                    gridLayout.removeView(button)
                    binding.myEdt.setText("${binding.myEdt.text}"+"${(button.text)}")
                    wordList.removeAt(0)
                    checkWordListIsEmpty()
                }
            }
        }

        startingIndex++
    }

    private fun putItOnRandomizedWords(s: String) {
        s.forEach {
            randomizedWords.add(it.toString())
        }
    }

    private fun checkWordListIsEmpty() {
        if (wordList.isEmpty()){
            Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show()
            Thread.sleep(500)
            binding.myEdt.text?.clear()
            createViewsByQues(startingIndex) // createViewsByQues ikkinchi ishlashidan boshlab xar next button bosilganida tekshirilishi kerak shunda testlarni birin ketin olish mumkin
        }
    }
}