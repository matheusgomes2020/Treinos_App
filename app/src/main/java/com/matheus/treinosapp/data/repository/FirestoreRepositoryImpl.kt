package com.matheus.treinosapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.matheus.treinosapp.domain.model.Exercise
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.AddExerciseResponse
import com.matheus.treinosapp.domain.repository.AddWorkoutResponse
import com.matheus.treinosapp.domain.repository.FirestoreRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirestoreRepositoryImpl @Inject constructor(
    @Named("workouts")
    private val workoutsRef: CollectionReference,
    @Named("exercises")
private val exercisesRef: CollectionReference
) : FirestoreRepository{
    override fun getWorkouts()= callbackFlow {
        val snapshotListener = workoutsRef.addSnapshotListener { snapshot, e ->
            val workoutsResponse = if (snapshot != null) {
                val workouts = snapshot.toObjects(Workout::class.java)
                Response.Success( workouts )
            } else {
                Response.Failure(e)
            }
            trySend( workoutsResponse )
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
        userId: String,
        userName: String
    ): AddWorkoutResponse = try {
        val idFirebase = workoutsRef.document().id
        val workout = Workout(
            id = id,
            description = description,
            name = name,
            timestamp = timestamp,
            userId = userId,
            username = userName,
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


    override fun getExercises()= callbackFlow {
        val snapshotListener = exercisesRef.addSnapshotListener { snapshot, e ->
            val exercisesResponse = if (snapshot != null) {
                val exercises = snapshot.toObjects(Exercise::class.java)
                Response.Success( exercises )
            } else {
                Response.Failure(e)
            }
            trySend( exercisesResponse )
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addExercise(
        id: String,
        name: String,
        imageUrl: String,
        observations: String,
        userId: String,
        idWorkout: String
    ): AddExerciseResponse = try {
        val idFirebase = exercisesRef.document().id
        val exercise = Exercise(
            id = id,
            name = name,
            imageUrl = imageUrl,
            observations = observations,
            idWorkout = idWorkout,
            idFirebase = idFirebase,
            userId = userId
        )
        exercisesRef.document(idFirebase).set(exercise).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun deleteExercise(id: String) = try {
        exercisesRef.document(id).delete().await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

}