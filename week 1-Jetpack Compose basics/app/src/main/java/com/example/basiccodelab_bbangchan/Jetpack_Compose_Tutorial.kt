package com.example.basiccodelab_bbangchan

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.example.basiccodelab_bbangchan.ui.theme.BasicCodeLab_BBangChanTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Jetpack_Compose_Tutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {    // setContent 안에 XML 대신 레이아웃을 구성할
                        // Composable 함수가 들어갈 수 있다.
            BasicCodeLab_BBangChanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable // @Composable을 사용하면
            // Composable function으로 만들 수 있다.
private fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {
        // Surface를 통해 Background 색을 지정한다.
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
                                    // Modifier는 layout을 어떻게 표현할지 또는 동작할지에 대한 속성을 지정하는 것.
    }                               // UI를 Modifier를 통해서 구성하게 된다.
}
// Composable function에는 TextView 대신 사용할 수 있는
// Text 같은 Composable function들을 넣을 수 있다.

@Preview(showBackground = true) // @Preview를 사용하면 다양한 프리뷰를 볼 수 있다.
@Composable
fun DefaultPreview() {
    BasicCodeLab_BBangChanTheme {
        Greeting("Android")
    }
}

/* State in Compose
 - remember
 - mutableStateOf

 초기 Composition
 -> 데이터의 변경   -> 정확히는 State<T>가 변경될 때
 Remember Composable로 객체 저장
 수정할 때 mutableStateOf<T>
 -> ReComposition  변경된 뷰만 데이터 업데이트를 하게된다.
( 데이터가 변경될 때 Composition을 업데이트하기 위해 Composable을 다시 실행한다.)
 */
class Jetpack_Compose_Tutorial2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodeLab_BBangChanTheme {
                MessageCard(Message("Android", "Jetpack Compose"))
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // Padding을 추가하여 8dp 만큼에 공간을 띄워준다.
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // 이미지 size를 40dp로 설정한다.
                .size(40.dp)
                // 이미지 클립을 동그란 모양으로 설정한다.
                .clip(CircleShape)
                // 경계를 1.5dp 만큼 색깔을 secondary로 하고 동그란 모양으로 색상을 추가한다.
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // 이미지와 글씨 사이의 수평적 공간을 8dp 만큼 띄워준다.
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                // msg.author의 색상을 secondaryVariant로 바꾼다.
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // msg.author와 msg.body 사이의 세로 길이를 4dp 만큼 띄워준다.
            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp){
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.subtitle2
                )
            }

        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)


@Preview
@Composable
fun PreviewMessageCard() {
    BasicCodeLab_BBangChanTheme {
        MessageCard(
            msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    BasicCodeLab_BBangChanTheme{
        Conversation(SampleData.conversationSample)
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BasicCodeLab_BBangChanTheme{
//                Conversation(SampleData.conversationSample)
//            }
//        }
//    }
//}
//
//
//@Composable
//fun MessageCard(msg: Message) {
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(R.drawable.img),
//            contentDescription = null,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//
//        // 우리는 메시지가 변화되었는지를 확인한다.
//        // 변수
//        var isExpanded by remember { mutableStateOf(false) }
//        // surfaceColor는 점차적으로 다른 것들중 하나로 점차적으로 업데이트 될것이다.
//        val surfaceColor : Color by animateColorAsState(
//            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
//        )
//        // 우리가 Column을 클릭할 때 isExpanded 변수를 전환한다.
//        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
//            Text(
//                text = msg.author,
//                color = MaterialTheme.colors.secondaryVariant,
//                style = MaterialTheme.typography.subtitle2
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Surface(
//                shape = MaterialTheme.shapes.medium,
//                elevation = 1.dp,
//                // surface의 컬러가 primary color로 바뀌게된다.
//                color = surfaceColor,
//                // 애니메이션 Content size에 padding을 1.dp 만큼 띄워준다.
//                modifier = Modifier.animateContentSize().padding(1.dp)
//            ) {
//                Text(
//                    text = msg.body,
//                    modifier = Modifier.padding(all = 4.dp),
//                    // 메시지가 확장되면 그것의 전부를 보여준다.
//                    // 그렇지 않으면 우리는 첫번째 줄만 보여준다.
//                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
//                    style = MaterialTheme.typography.body2
//                )
//            }
//        }
//    }
//}
//data class Message(val author: String, val body: String)
//
//@Composable
//fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}
// _________________________________________________________________________________________________

