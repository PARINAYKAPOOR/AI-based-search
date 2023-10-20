package com.example.chatgptretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.chatgptretrofit.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val inputChatGpt = binding.inputEdit.text.toString()
            val apiInterface = RetrofitClient.getInstance().create(ChatGptInterface::class.java)
            apiInterface.getResponse(inputChatGpt).enqueue(object:Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    binding.outputText.text = response.body().toString()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    binding.outputText.text = t.toString()
                }
            })





        }
    }


}