package com.zeroillusion.allaboutstarwarsapp.presentation.general_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeroillusion.allaboutstarwarsapp.R
import com.zeroillusion.allaboutstarwarsapp.domain.model.Person

@Composable
fun PersonCard(
    person: Person,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSecondary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Name:",
                        style = TextStyle.Default.copy(
                            fontSize = 9.sp
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = person.name,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Gender:",
                        style = TextStyle.Default.copy(
                            fontSize = 9.sp
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = person.gender,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Starships count:",
                        style = TextStyle.Default.copy(
                            fontSize = 9.sp
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = person.starshipsCount.toString(),
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                var favorite by remember { mutableStateOf(person.favorite) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                    IconButton(
                        onClick = {
                            favorite = !favorite
                            onFavoriteClick()
                        }
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonCardPreview() {
    PersonCard(
        person = Person(
            name = "Name",
            gender = "Gender",
            starshipsCount = 0,
            films = emptyList(),
            url = "",
            favorite = false
        ),
        onFavoriteClick = {}
    )
}