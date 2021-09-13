package com.devesh.smartshows.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.devesh.smartshows.dto.MyBookingDto
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

class SlotBookingDataRepository {
    private val database = Firebase.database
    private val moviesDatabaseRef = database.getReference("movies")


    //    private val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: "u5"
    private val userDatabase = Firebase.database.getReference("users")

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateData2(
        buildingID: String,
        floorID: String,
        pillerID: String,
        boxID: String,
        hoursToAdd: Long = 1, currentUserUid: String
    ) {

        val time = Calendar.getInstance().timeInMillis


        val hourValue: Long = TimeUnit.HOURS.toMillis(hoursToAdd)
        val toTime: Long = time + hourValue

        val databaseRef =
            moviesDatabaseRef.child(buildingID).child(floorID).child(pillerID).child(boxID)
        val map: MutableMap<String, Any> = HashMap<String, Any>()
        map["available"] = false
        map["time"] = time
        map["user_id"] = currentUserUid
        databaseRef.updateChildren(map)


        val myBookingDatabaseRef =
            userDatabase.child(currentUserUid).child("myBooking").push()

        val data = MyBookingDto(
            mallId = buildingID,
            floorId = floorID,
            pillarId = pillerID,
            boxId = boxID,
            time = time,
            toTime = toTime,
            checkedOut = false,
            id = myBookingDatabaseRef.key
        )
        myBookingDatabaseRef.setValue(data)

    }


}