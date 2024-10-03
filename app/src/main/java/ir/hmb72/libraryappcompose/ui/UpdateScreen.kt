package ir.hmb72.libraryappcompose.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.hmb72.libraryappcompose.data.room.BookEntity
import ir.hmb72.libraryappcompose.viewmodel.BookViewModel


@Composable
fun UpdateScreen(viewModel: BookViewModel, bookId: String?, navController: NavController) {

    //1 -
    var inputBook by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = " Update name book ", modifier = Modifier.padding(top = 10.dp))

        OutlinedTextField(modifier = Modifier.padding(top = 10.dp),
            value = inputBook, onValueChange = { enteredText ->
                inputBook = enteredText
            },
            label = { Text(text = "Update Book Name") },
            placeholder = { Text(text = "New Book Name") }
        )


        Button(
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {
                var newBook = BookEntity(bookId!!.toInt(), inputBook)
                viewModel.updateBook(newBook)
                navController.popBackStack()
            }
        ) {
            Text(text = "Update book", color = Color.White)
        }
    }


}