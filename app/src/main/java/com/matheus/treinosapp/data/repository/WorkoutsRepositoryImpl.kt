package com.matheus.treinosapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.AddWorkoutResponse
import com.matheus.treinosapp.domain.repository.WorkoutsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp
import javax.inject.Inject

class WorkoutsRepositoryImpl @Inject constructor(
    private val workoutsRef: CollectionReference
) : WorkoutsRepository{
    override fun getWorkouts()= callbackFlow {
        val snapshotListener = workoutsRef.addSnapshotListener { snapshot, e ->
            val moviesResponse = if (snapshot != null) {
                val movies = snapshot.toObjects(Workout::class.java)
                Response.Success( movies )
            } else {
                Response.Failure(e)
            }
            trySend( moviesResponse )
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addWorkout(
        id: String,
        name: String,
        description: String,
        timestamp: String,
        userId: String
    ): AddWorkoutResponse = try {
        val idFirebase = workoutsRef.document().id
        val workout = Workout(
            id = id,
            description = description,
            name = name,
            timestamp = timestamp,
            userId = userId,
            idFirebase = idFirebase
        )
        workoutsRef.document(idFirebase).set(workout).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun deleteWorkout(id: String) = try {
        workoutsRef.document(id).delete().await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }
}