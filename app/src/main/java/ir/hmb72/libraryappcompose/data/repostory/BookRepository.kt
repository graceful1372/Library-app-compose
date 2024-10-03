package ir.hmb72.libraryappcompose.data.repostory

import ir.hmb72.libraryappcompose.data.room.BookDB
import ir.hmb72.libraryappcompose.data.room.BookEntity

class BookRepository(private val bookDB: BookDB) {

    suspend fun addBookToRoom(bookEntity: BookEntity){
        bookDB.bookDAO().addBook(bookEntity)
    }

    fun getAllBook () = bookDB.bookDAO().getAllBook()

    suspend fun deleteBook(bookEntity: BookEntity){
        bookDB.bookDAO().deleteBook(bookEntity)
    }

    suspend fun updateBook(bookEntity: BookEntity){
        bookDB.bookDAO().updateBook(bookEntity)
    }


}