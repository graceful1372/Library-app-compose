package ir.hmb72.libraryappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hmb72.libraryappcompose.data.room.BookDB
import ir.hmb72.libraryappcompose.data.room.BookEntity
import ir.hmb72.libraryappcompose.data.repostory.BookRepository
import ir.hmb72.libraryappcompose.ui.UpdateScreen
import ir.hmb72.libraryappcompose.ui.theme.LibraryAppComposeTheme
import ir.hmb72.libraryappcompose.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppComposeTheme {
                val mContext = LocalContext.current
                val db = BookDB.getInstance(mContext)
                val repository = BookRepository(db)
                val myViewModel = BookViewModel(repository = repository)

                //Navigation
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(viewModel = myViewModel, navController = navController)
                    }
                    composable("UpdateScreen/{bookId}") {
                        UpdateScreen(viewModel = myViewModel, bookId = it.arguments?.getString("bookId"), navController)
                    }

                }


//                MainScreen(myViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: BookViewModel, navController: NavHostController) {
    var inputBook by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 22.dp , start = 6.dp ,end = 6.dp )
    ) {
        Text(text ="Insert Books in Room DB" )
        OutlinedTextField(
            value = inputBook,
            onValueChange = { enteredText -> inputBook = enteredText },
            label = { Text(text = "Book Name") },
            placeholder = { Text(text = "Enter Your Book Name") },
        )

        Button(onClick = { viewModel.addBook(BookEntity(0, inputBook)) },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "Insert Book into DB")
        }

        //The books list
        BooksList(viewModel = viewModel, navController)
    }
}

@Composable
fun BookCard(viewModel: BookViewModel, book: BookEntity, navController: NavHostController) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "" + book.id, fontSize = 24.sp, modifier = Modifier.padding(start = 12.dp, end = 12.dp), color = Color.Blue)
            Text(text = book.title, fontSize = 24.sp, modifier = Modifier.fillMaxWidth(0.7f), color = Color.Black)

            IconButton(onClick = { viewModel.deleteBook(book) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
            IconButton(onClick = { navController.navigate("UpdateScreen/${book.id}") }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        }

    }
}

@Composable
fun BooksList(viewModel: BookViewModel, navController: NavHostController) {

    val books by viewModel.books.collectAsState(initial = emptyList())


    Column(Modifier.padding(16.dp)) {
        Text(text = "My Library", fontSize = 24.sp , color = Color.Red)

        LazyColumn() {
            items(items = books) { item ->
                BookCard(viewModel = viewModel, book = item, navController)

            }

        }
    }

}
