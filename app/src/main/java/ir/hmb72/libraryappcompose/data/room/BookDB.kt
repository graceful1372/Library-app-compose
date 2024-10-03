package ir.hmb72.libraryappcompose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//1
@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDB : RoomDatabase() {
    //2
    abstract fun bookDAO(): BookDAO

    //3 Companion object
    companion object {
        @Volatile
        private var INSTANCE: BookDB? = null

        fun getInstance(context: Context): BookDB {
            synchronized(this) { // -->??
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, BookDB::class.java,
                        "books_db"
                    ).build()
                    INSTANCE = instance //-->??
                }
                return instance
            }

        }
    }
}