package com.example.profileapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.profileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tag = "LIFECYCLE"

    private var name = ""
    private var mssv = ""
    private var className = ""
    private var email = ""
    private var avatarUri: Uri? = null

    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val d = result.data
            name = d?.getStringExtra(EditActivity.KEY_NAME) ?: ""
            mssv = d?.getStringExtra(EditActivity.KEY_MSSV) ?: ""
            className = d?.getStringExtra(EditActivity.KEY_CLASS) ?: ""
            email = d?.getStringExtra(EditActivity.KEY_EMAIL) ?: ""
            render()
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
        }
    }

    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            avatarUri = uri
            binding.imgAvatar.setImageURI(uri)
        } else {
            Toast.makeText(this, "Người dùng đã hủy chọn ảnh", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("NAME", name)
        outState.putString("MSSV", mssv)
        outState.putString("CLASS", className)
        outState.putString("EMAIL", email)
        outState.putString("AVATAR", avatarUri?.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(tag, "MainActivity: onCreate()")

        savedInstanceState?.let {
            name = it.getString("NAME", "")
            mssv = it.getString("MSSV", "")
            className = it.getString("CLASS", "")
            email = it.getString("EMAIL", "")
            avatarUri = it.getString("AVATAR")?.let(Uri::parse)
        }
        avatarUri?.let { binding.imgAvatar.setImageURI(it) }
        render()

        binding.imgAvatar.setOnClickListener { selectImageLauncher.launch("image/*") }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra(EditActivity.KEY_NAME, name)
                putExtra(EditActivity.KEY_MSSV, mssv)
                putExtra(EditActivity.KEY_CLASS, className)
                putExtra(EditActivity.KEY_EMAIL, email)
            }
            editLauncher.launch(intent)
        }
    }

    private fun render() {
        val empty = "Chưa có thông tin"
        binding.tvName.text = "Họ tên: ${name.ifBlank { empty }}"
        binding.tvMssv.text = "MSSV: ${mssv.ifBlank { empty }}"
        binding.tvClass.text = "Lớp: ${className.ifBlank { empty }}"
        binding.tvEmail.text = "Email: ${email.ifBlank { empty }}"
    }

    override fun onStart() { super.onStart(); Log.d(tag, "MainActivity: onStart()") }
    override fun onResume() { super.onResume(); Log.d(tag, "MainActivity: onResume()") }
    override fun onPause() { super.onPause(); Log.d(tag, "MainActivity: onPause()") }
    override fun onStop() { super.onStop(); Log.d(tag, "MainActivity: onStop()") }
    override fun onRestart() { super.onRestart(); Log.d(tag, "MainActivity: onRestart()") }
    override fun onDestroy() { super.onDestroy(); Log.d(tag, "MainActivity: onDestroy()") }
}