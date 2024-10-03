package ir.hmb72.libraryappcompose.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

//When we don't use table name it will take the class name as table name
@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val title:String
)
