package com.example.project261.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project261.domain.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    fun loadCategory(): LiveData<List<CategoryModel>> {
        val listData = MutableLiveData<List<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (item in snapshot.children) {
                    val model = item.getValue(CategoryModel::class.java)
                    if (model != null) {
                        list.add(model)
                    }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO(reason = "not yet implemented")
            }
        })
        return listData
    }
}


// needs to be updated
/*
================================================================================
  CODE EXPLANATION: MainRepository & Firebase Realtime Database
================================================================================

  SUMMARY:
  This class acts as the "Source of Truth" for your app's Category data.
  It fetches data from the Firebase Realtime Database and wraps it in a
  LiveData object so your UI (Jetpack Compose) can observe it and update
  automatically whenever the database changes.

  ------------------------------------------------------------------------------
  LINE-BY-LINE BREAKDOWN:
  ------------------------------------------------------------------------------

  1. private val firebaseDatabase = FirebaseDatabase.getInstance()
     - This gets the entry point to your connected Firebase database.
     - .getInstance() ensures you are using the single, shared connection
       created by the Firebase SDK (Singleton pattern).

  2. fun loadCategory(): LiveData<List<CategoryModel>> { ... }
     - This function is called by your ViewModel.
     - It returns 'LiveData'. This is crucial because reading from the internet
       is asynchronous (takes time).
     - Instead of returning a List directly (which would be empty immediately),
       we return a "box" (LiveData) that we will fill with data later.

  3. val listData = MutableLiveData<List<CategoryModel>>()
     - We create the empty "box".
     - 'MutableLiveData' means we (the Repository) have the power to change
       the content of the box. The UI only sees it as 'LiveData' (read-only).

  4. val ref = firebaseDatabase.getReference("Category")
     - This points to a specific node in your Firebase JSON tree.
     - It looks for a folder named "Category".

  5. ref.addValueEventListener(object : ValueEventListener { ... })
     - This sets up a "listener" or "watcher" on that specific database node.
     - "addValueEventListener" is special: it triggers immediately to give you
       current data, AND it stays active. If you change data in the Firebase Console,
       this code runs again instantly (Realtime!).

  6. override fun onDataChange(snapshot: DataSnapshot) { ... }
     - This function fires whenever Firebase successfully retrieves data.
     - 'snapshot': Think of this as a picture of your data at that exact moment.
       It contains the JSON data found under "Category".

  7. val list = mutableListOf<CategoryModel>()
     - We create a temporary empty list to hold the new data we are about to process.

  8. for (item in snapshot.children) { ... }
     - 'snapshot.children' gives us access to the sub-nodes (e.g., Category1, Category2).
     - We loop through every child found under "Category".

  9. val model = item.getValue(CategoryModel::class.java)
     - This is the "Magic" of Firebase.
     - It takes the JSON data (Key-Value pairs like "name": "Pizza") and automatically
       converts it into your Kotlin 'CategoryModel' object.
     - Note: Your CategoryModel properties must match the Firebase keys exactly for this to work.

  10. if (model != null) { list.add(model) }
      - Safety check. If the conversion worked, add the object to our temporary list.

  11. listData.value = list
      - THIS IS THE CRITICAL UPDATE STEP.
      - We take our temporary 'list' (now full of data) and put it into the 'listData' "box".
      - As soon as this line runs, any UI observing this LiveData will instantly refresh
        to show the new items.

  12. override fun onCancelled(error: DatabaseError) { ... }
      - This function fires if the read fails (e.g., no internet, or permission denied).
      - Currently, it has a TODO, so the app might crash if an error occurs here.

  13. return listData
      - This line actually runs BEFORE the data is fetched (because the internet is slow).
      - It returns the empty "box" to the ViewModel immediately. The "box" gets filled
        later by the code inside 'onDataChange'.
================================================================================
*/