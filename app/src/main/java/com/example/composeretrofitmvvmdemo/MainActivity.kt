package com.example.composeretrofitmvvmdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.composeretrofitmvvmdemo.dao.Movie
import com.example.composeretrofitmvvmdemo.ui.theme.ComposeRetrofitMVVMDemoTheme

class MainActivity : ComponentActivity() {
    private val mainVM: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRetrofitMVVMDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    /*mainVM.getPopularMovies().observe(this, {
                        if (it != null) {
                            Log.i("MOVIES", it.toString())
                            MovieList(movieList = it)
                        }
                    })*/

                    MovieList(movieList = mainVM.movieListResponse)
                    mainVM.getMovieList()

                }
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<Movie>) {
    val context = LocalContext.current
    LazyColumn {
        items(items = movieList) { item ->
            MovieItem(movie = item) { selectedMovie ->
                Toast.makeText(context, "${selectedMovie.title} selected", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieItem() {
    val movie = Movie(
        1,
        "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
        "https://howtodoandroid.com/images/coco.jpg",
        "12/12/2000",
        "COCO"
    )

    MovieItem(movie = movie) { i ->

    }
}

@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClick(movie) }
            .height(110.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {

            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500" + movie.posterPath,

                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.placeholder)
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = movie.overview,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
            )

            Column(
                //horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Text(
                    text = movie.title,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.releaseDate,
                )
                Text(
                    text = movie.overview,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }

}
