package com.example.profileapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.profileapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    companion object {
        const val KEY_NAME = "KEY_NAME"
        const val KEY_MSSV = "KEY_MSSV"
        const val KEY_CLASS = "KEY_CLASS"
        const val KEY_EMAIL = "KEY_EMAIL"
    }

    private lateinit var binding: ActivityEditBinding
    private val tag = "LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(tag, "EditActivity: onCreate()")

        if (savedInstanceState == null) {
            binding.edtName.setText(intent.getStringExtra(KEY_NAME))
            binding.edtMssv.setText(intent.getStringExtra(KEY_MSSV))
            binding.edtClass.setText(intent.getStringExtra(KEY_CLASS))
            binding.edtEmail.setText(intent.getStringExtra(KEY_EMAIL))
        }

        binding.btnSave.setOnClickListener {
            val result = Intent().apply {
                putExtra(KEY_NAME, binding.edtName.text.toString().trim())
                putExtra(KEY_MSSV, binding.edtMssv.text.toString().trim())
                putExtra(KEY_CLASS, binding.edtClass.text.toString().trim())
                putExtra(KEY_EMAIL, binding.edtEmail.text.toString().trim())
            }
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }

    override fun onStart() { super.onStart(); Log.d(tag, "EditActivity: onStart()") }
    override fun onResume() { super.onResume(); Log.d(tag, "EditActivity: onResume()") }
    override fun onPause() { super.onPause(); Log.d(tag, "EditActivity: onPause()") }
    override fun onStop() { super.onStop(); Log.d(tag, "EditActivity: onStop()") }
    override fun onDestroy() { super.onDestroy(); Log.d(tag, "EditActivity: onDestroy()") }
}