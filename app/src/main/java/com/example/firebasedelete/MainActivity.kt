package com.example.firebasedelete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasedelete.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDelete.setOnClickListener {
            val userName=binding.etUser.text.toString()

            if (userName.isNotEmpty()){
                deleteData(userName)
            }
            else{
                Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun deleteData(userName:String){
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).removeValue().addOnSuccessListener {

            binding.etUser.text.clear()
            Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this," Failed to Delete",Toast.LENGTH_LONG).show()
        }
    }
}