package com.example.consciente_te.pages

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun MediaPage(){
    val cards = listOf(
        CardItem(
            imageUrl ="https://media.cnn.com/api/v1/images/stellar/prod/220531190304-woman-meditation-stock.jpg?c=original",
            title = "Meditation",
            description = "Stretching exercises to improve everything related to concentration.",
                    videoUrl = "https://www.youtube.com/watch?v=IShkpOm63gg"
        ),
        CardItem(
            imageUrl ="https://media.glamour.mx/photos/642c5305347cb2132003b34a/16:9/w_2560%2Cc_limit/yoga_y_estiramientos_diferencias.jpg",
            title = "Yoga",
            description = "Yoga exercises for stress and depression management."
            ,
            videoUrl = "https://www.youtube.com/watch?v=zn6ostiHX0U"
        ),
        CardItem(
            imageUrl ="https://media.springernature.com/full/springer-static/image/art%3A10.1038%2Fs41407-022-0859-1/MediaObjects/41407_2022_859_Fig1_HTML.jpg",
            title = "Breathing",
            description = "Breathing exercises for control of the anxiety.",
            videoUrl = "https://www.youtube.com/watch?v=oeOXoK67ewE"
        ),
        CardItem(
            imageUrl ="https://static.vecteezy.com/system/resources/thumbnails/017/776/465/small/woman-during-a-mental-therapy-session-with-a-psychotherapist-or-psychologist-two-women-are-sitting-and-talking-mental-health-concept-illustration-vector.jpg",
            title = "Psychological session",
            description = "Therapeutic session with health professional.",
            videoUrl = "https://www.youtube.com/watch?v=RiGIvbHpluE"
        ),
        CardItem(
            imageUrl ="hhttps://www.flimp.net/wp-content/uploads/2022/05/improve-mental-health-workplace-five-ways.png",
            title = "Improve Mental Health",
            description = "Five Free Ways to Improve Mental Health In Your Workplace.",
            videoUrl = "https://www.youtube.com/watch?v=1qq7lDL-bzY"
        )


    )

    LazyColumn {


        items(cards){ card ->
            Spacer(modifier = Modifier.height(16.dp))
            StackedCard(card = card)

        }

    }
}

@Composable
fun StackedCard(card: CardItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp).clickable {
               openVideo(context,card.videoUrl)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = card.imageUrl)
                        .allowHardware(false)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = card.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = card.description)
            }
        }
    }
}

data class CardItem(
    val imageUrl: String,
    val title: String,
    val description: String,
    val videoUrl: String
)
@Preview
@Composable
fun StackedCardListPreview() {
    MediaPage()
}
fun openVideo(ctx: Context,videoUrl: String) {

    val intent=Intent(Intent.ACTION_VIEW,Uri.parse(videoUrl)).apply {
        `package`="com.google.android.youtube"
    }
        try {
            ctx.startActivity(intent)
        } catch (e:ActivityNotFoundException){
            e.printStackTrace()
        }

}
