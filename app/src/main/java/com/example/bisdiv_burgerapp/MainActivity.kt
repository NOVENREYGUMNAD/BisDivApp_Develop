package com.example.bisdiv_burgerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var input_FullName: EditText
    private lateinit var input_Address: EditText
    private lateinit var input_Number: EditText
    private lateinit var confirm_Button: Button

    private var db = FirebaseFirestore.getInstance()


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

            val dataToSave = hashMapOf(
                "name" to inputData1,
                "address" to inputData2,
                "phone" to inputData3,
                "timestamp" to FieldValue.serverTimestamp()
            )

            val userId = FirebaseAuth.getInstance().currentUser!!.uid

            db.collection("Customer").document(userId).set(dataToSave)
                .addOnSuccessListener {
                    Toast.makeText(this, "Order has been submitted!", Toast.LENGTH_SHORT).show()

                    input_FullName.text.clear()
                    input_Address.text.clear()
                    input_Number.text.clear()
                }

                .addOnFailureListener {
                    Toast.makeText(this, "Failed to submitted!", Toast.LENGTH_SHORT).show()
                }
        }

    }
}

