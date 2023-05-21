package ort.tp3.database.daos

import androidx.room.Dao
import androidx.room.Query
import ort.tp3.database.entities.User

@Dao
interface UserDao : BaseDao<User> {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}