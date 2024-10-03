package ir.hmb72.libraryappcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hmb72.libraryappcompose.data.repostory.BookRepository
import ir.hmb72.libraryappcompose.data.room.BookEntity
import kotlinx.coroutines.launch

class BookViewModel(val repository: BookRepository) :ViewModel() {

    fun addBook(book: BookEntity){
        viewModelScope.launch {
            repository.addBookToRoom(book )

        }
    }

    val books = repository.getAllBook()

    fun deleteBook(book: BookEntity){
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }

    fun updateBook(book: BookEntity){
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }
}