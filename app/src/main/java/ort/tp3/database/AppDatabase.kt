package ort.tp3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ort.tp3.database.daos.UserDao
import ort.tp3.database.entities.User
import ort.tp3.helpers.PasswordEncryptor

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "tp3.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val pass1 = PasswordEncryptor.encrypt("pass456")
                            val pass2 = PasswordEncryptor.encrypt("password123")
                            // Create "users" table and insert initial data
                            db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)")
                            db.execSQL(
                                String.format(
                                    "INSERT INTO users (email, password) VALUES ('%s', '%s')",
                                    "john@mail.com",
                                    pass1
                                )
                            )
                            db.execSQL(
                                String.format(
                                    "INSERT INTO users (email, password) VALUES ('%s', '%s')",
                                    "jane@mail.com",
                                    pass2
                                )
                            )
                        }

                        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                            super.onDestructiveMigration(db)
                            db.execSQL("DROP TABLE IF EXISTS users")
                        }
                    })
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}


