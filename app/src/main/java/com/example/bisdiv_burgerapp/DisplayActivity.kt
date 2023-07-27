package com.example.bisdiv_burgerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bisdiv_burgerapp.databinding.ActivityDisplayBinding
import com.example.bisdiv_burgerapp.databinding.ActivityLoginBinding
import com.example.bisdiv_burgerapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DisplayActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding

    private var firebaseDb = FirebaseFirestore.getInstance()
    val foodList = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveToFireStore("spagehetti", "pasta", "50", "for claim")

    }

    fun saveToFireStore(name: String, role : String, url: String, stat: String){
        FirebaseFirestore.setLoggingEnabled(true);
        val sampleUser: MutableMap<String, Any> = HashMap()
        sampleUser["titleImage"]      = name
        sampleUser["heading"] = role
        sampleUser["price"] = url
        sampleUser["status"] = stat

        firebaseDb.collection("food")
            .add(sampleUser)
            .addOnSuccessListener {
                Log.e("FIRE", "Success")
                getFirestore()
            }
            .addOnFailureListener { Log.e("FIRE", "Failed > " + it.toString()) }

    }
    fun updateToFireStore(docID: String, name: String, role : String, url: String, stat: String){
        FirebaseFirestore.setLoggingEnabled(true);
        val sampleUser: MutableMap<String, Any> = HashMap()
        sampleUser["titleImage"]      = name
        sampleUser["heading"] = role
        sampleUser["price"] = url
        sampleUser["status"] = stat

        firebaseDb.collection("food")
            .document(docID)
            .update(sampleUser)
            .addOnSuccessListener {
                Log.e("FIRE", "Success")
                getFirestore()
            }
            .addOnFailureListener { Log.e("FIRE", "Failed > " + it.toString()) }

    }

    fun getFirestore(){
        foodList.clear()
        firebaseDb.collection("food")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { snap ->
                        snap.forEach { docu ->
                            var docIdValue = ""
                            var titleImage = ""
                            var heading = ""
                            var price = ""
                            var status = ""
                            docIdValue = docu.id.toString()
                            docu.data.forEach {
                                when (it.key.toString()) {
                                    "titleImage" -> { titleImage = it.value.toString() }
                                    "heading" -> { heading = it.value.toString() }
                                    "price" -> { price = it.value.toString() }
                                    "status" -> { status  = it.value.toString() }
                                }

                            }
                            val newFood = Food(docIdValue,titleImage, heading, price, status)

                            foodList.add(newFood)
                            Log.e("CLOUD", "usersList > ${foodList.toString()}")
                        }
                        val foodAdapter  = FoodAdapter( foodList){
                        updateToFireStore(it.docId, it.titleImage, it.heading,"1000", "for pick up")
                        }
                        binding.rv.adapter = foodAdapter
                        binding.rv.layoutManager = LinearLayoutManager(this)
                    //                        usersLive.postValue(usersList)
//                        Log.e("CLOUD", "usersList > ${usersList.toString()}")
                    }
                }
            }

    }
}