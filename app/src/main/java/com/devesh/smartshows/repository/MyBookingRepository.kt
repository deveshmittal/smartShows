package com.devesh.smartshows.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.devesh.smartshows.dto.MyBookingDto
import com.devesh.smartshows.repository.listenerAndDatabaseModel.ListenerAndDatabaseReference

class MyBookingRepository {

//    private val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: "u5"


    private val database = Firebase.database.getReference("users")
    private val movieDatabaseRef = Firebase.database.getReference("movies")


    val listOfMyBooking = mutableStateOf<List<MyBookingDto>>(arrayListOf())

    fun getAllMyBooking(currentUserUid: String): ListenerAndDatabaseReference {
        Log.d("TAg", currentUserUid)
        val myBookingDatabaseRef = database.child(currentUserUid).child("myBooking")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val arrayList = arrayListOf<MyBookingDto>()

                for (bookingItemSnapShot in snapshot.children) {
                    val data = bookingItemSnapShot.getValue(MyBookingDto::class.java)
                    data?.id = bookingItemSnapShot?.key ?: "nan"
                    if (data != null) {
                        arrayList.add(
                            data
                        )
                    }
                }
                listOfMyBooking.value = arrayList
            }

            override fun onCancelled(error: DatabaseError) {}
        }

        myBookingDatabaseRef.addValueEventListener(listener)

        return ListenerAndDatabaseReference(database = myBookingDatabaseRef, listener = listener)
    }


    fun checkOut(
        buildingID: String,
        floorID: String,
        pillerID: String,
        boxID: String,
        bookingId: String,
        currentUserUid: String
    ) {
        val databaseRef =
            movieDatabaseRef.child(buildingID).child(floorID).child(pillerID).child(boxID)
        val map: MutableMap<String, Any> = HashMap<String, Any>()
        map["available"] = true
        map["user_id"] = "nan"
        databaseRef.updateChildren(map)

        val myBookingDatabaseRef =
            database.child(currentUserUid).child("myBooking").child(bookingId)
        val mapBooking: MutableMap<String, Any> = HashMap<String, Any>()
        mapBooking["checkedOut"] = true
        myBookingDatabaseRef.updateChildren(mapBooking)

    }


}