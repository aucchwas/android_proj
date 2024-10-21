package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.ui.theme.MyApplicationTheme
import com.example.project.viewmodel.InPlayMatchesViewModel
import com.example.project.viewmodel.State.MatchesState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    TopBar()
                    Row (modifier = Modifier.padding(top = 20.dp)) {
                        Spacer(modifier = Modifier.width(30.dp))
                        Exam(modifier = Modifier.padding(start = 10.dp), pic = R.drawable.premierleague)
                        Spacer(modifier = Modifier.width(30.dp))
                        Exam(modifier = Modifier.weight(1f), pic = R.drawable.laliga)
                    }
                    Row (modifier = Modifier.padding(top = 20.dp)) {
                        Spacer(modifier = Modifier.width(30.dp))
                        Exam(modifier = Modifier.padding(start = 10.dp), pic = R.drawable.bundesliga)
                        Spacer(modifier = Modifier.width(30.dp))
                        Exam(modifier = Modifier.weight(1f), pic = R.drawable.seriaaa)
                    }
                    FetchData()
                }

            }
        }
    }
}
@Composable
fun FetchData(inPlayMatchesViewModel: InPlayMatchesViewModel = viewModel()){
    Column {
        when(val state =
            inPlayMatchesViewModel.inPlayMatchesState.collectAsState().value){
            is MatchesState.Empty -> Text(text = "No data")
            is MatchesState.Loading -> Text(text = "Loading....")
            is MatchesState.Success -> Text(text = "Success")
            is MatchesState.Error -> Text(text = state.error)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh Icon")
        }
        Text(text = "Scorer", style = MaterialTheme.typography.headlineMedium)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.modeicon),
                contentDescription = "Refresh Icon"
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    MyApplicationTheme {
        Exam(modifier = Modifier, pic = R.drawable.seriaaa)
    }
}

@Composable
fun ClickableCardSample(pic: Int) {
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.size(width = 160.dp, height = 90.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = pic),
                contentDescription = "Background Icon",
                alpha = 0.8f
            )

        }
    }
}

@Composable
fun Exam(modifier: Modifier, pic : Int) {
    Box {
        ClickableCardSample(pic)
    }
}
