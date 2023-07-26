package com.example.bisdiv_burgerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var input_FullName:EditText
    private lateinit var input_Address:EditText
    private lateinit var input_Number:EditText
    private lateinit var confirm_Button:Button

    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input_FullName = findViewById(R.id.input_FullName)
        input_Address = findViewById(R.id.input_Address)
        input_Number = findViewById(R.id.input_Number)
        confirm_Button = findViewById(R.id.confirm_Button)

        confirm_Button.setOnClickListener {
            val inputData1 = input_FullName.text.toString()
            val inputData2 = input_Address.text.toString()
            val inputData3 = input_Number.text.toString()

            if (inputData1.isNotBlank() && inputData2.isNotBlank() && inputData3.isNotBlank()){

                sendDataToFirestore(inputData1, inputData2, inputData3)
            }else {
                Toast.makeText(this, "Please Enter Information", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun sendDataToFirestore(name: String, address: String, phone: String) {

        val collectionName = "Customer"

        val documentId = "A1"

        val dataToSave = hashMapOf(
            "name" to "Noven Rey Gumnad",
            "address" to "Tagum City",
            "phone" to "09953652726"
        )
     db.collection(collectionName).document(documentId)

         .set(dataToSave)
         .addOnSuccessListener {
             Toast.makeText(this, "Order has been sent!", Toast.LENGTH_SHORT).show()
         }
         .addOnFailureListener { e -> Toast.makeText(this, "Error: ${e.message}",
         Toast.LENGTH_SHORT).show()
         }
    }
}