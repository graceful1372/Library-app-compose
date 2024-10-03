package ir.hmb72.libraryappcompose.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {
    @Insert
    suspend fun addBook(bookEntity: BookEntity)

    @Query("Select * from BookEntity")
    fun getAllBook():Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)


    @Update
    suspend fun updateBook(bookEntity: BookEntity)




}