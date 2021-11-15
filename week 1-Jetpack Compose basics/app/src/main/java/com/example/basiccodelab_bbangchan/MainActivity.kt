package com.example.basiccodelab_bbangchan

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab_bbangchan.ui.theme.BasicCodeLab_BBangChanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodeLab_BBangChanTheme {
                // A surface container using the 'background' color from the theme
                MyApp() // MyApp을 함수로 설정해서 main문의 코드를 줄였다.
            }
        }
    }
}

//@Composable
//private fun MyApp(names : List<String> = listOf("World", "Compose")) { // Surface를 사용해서
//    Column (modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in names){  // for문을 사용해서 배열에 있는 것들을 Greeting에 name으로 데이터를 준다.
//            Greeting(name = name)
//        }
//    }
//}

@Composable
private fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

@Composable // State hoisting
/*
 상태를 호이스트 가능하게 만들면 상태 복제 및 버그 도입을 방지하고 Composable을 재사용하는 데 도움이 되며
 Composable을 훨씬 더 쉽게 테스트할 수 있습니다. 반대로 Composable의 부모에 의해 제어 될 필요가 없는 상태는
 호이스팅되지 않아야 합니다.
 */
fun OnboardingScreen(onContinueClicked : () -> Unit) {

   // var shouldShowOnboarding by remeberSaveable { mutableStateOf(true)}

    Surface {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) {"$it"}){
    // 인사말 하는 배열을 1000으로 만든다. 1000개의 인사말
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        // 스크롤 가능한 열을 표시하기 위해서 LazyColumn을 사용한다.
        // LazyColumn은 화면에 보이는 항목만 렌더링하므로 큰 목록을 렌더링할 때 성능이 향상된다.
        items(items = names){
           name -> Greeting(name = name)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview(){
    BasicCodeLab_BBangChanTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
private fun Greeting(name: String) {  //결과 값은 에뮬레이터 또는 미리보기로 확인할 수 있다.
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}
//val expanded = remember { mutableStateOf(false)}

//    val extraPadding by animateDpAsState(
//        if (expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        /* spring사양은 시간 관련 매개 변수를 사용하지 않는다. 대신 애니메이션을 보다 자연스럽게 만들기 위해
//         물리적 속성(감쇠 및 강성)에 의존한다.
//        */
//        )
//    )
//    // animateDpAsState를 사용하여 extraPadding 확장 상태에 따라 달라지는 애니메이션을 만든다.
//    // 람다식을 이용해서 expanded.value가 true 이면 48dp로 설정하고 false이면 0dp로 설정
//    // 즉 true이면 (한번 눌리면) 48dp로 커지고, false이면 (두번 또는 처음에) 0dp로 작아진다.
//    Surface(
//        color = MaterialTheme.colors.primary,
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        /* Surface는 배경색 위에 MaterialTheme.colors.primary의 색깔로 배경색을 바꾼다.
//        하지만 우리는 글자색을 흰색으로 바꾸지 않았는데 흰색이 되었다.
//        androidx.compose.material.Surface는 앱에서 원하는 일반적인 기능을 처리하여 더 나은 경험을 제공하도록 제작되었다.
//        Material은 대부분 앱에 공통적인 좋은 기본값과 패턴을 제공하기 때문에 독단적이다.
//        Surface 배경이 primary 색상으로 설정되면 그 위에 있는 모든 Text는 onPrimary는 테마에서도 정의된 생삭을 사용해야한다.
//        */
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//                // 아래를 padding을 추가한다. padding이 음수가 되면 앱이 충돌할 수 있다.
//            ) {
//                // fillMaxWidth를 사용하면 가로의 끝까지 채워지게 된다. padding한 정도를 빼고
//                // Modifier.padding을 24dp로 설정하면 테두리로부터 24dp 씩 띄워서 나타나게된다.
//                // Column을 선언해서 padding을 설정하고 그 안에 Text를 사용해서 줄을 구분한다.
//                Text(text ="Hello, ") // 첫번째 줄
//                Text(text = name, style = MaterialTheme.typography.h4) // 두번째 줄
//            }
//            OutlinedButton( // 버튼을 만드는 함수
//                onClick = { expanded.value = !expanded.value }
//                // 버튼이 클릭 되면 expanded.value을 토글 시켜준다.
//            ) {
//                Text(if (expanded.value) "Show less" else "Show more")
//                // expanded.value가 true면 Show less false면 Show more
//            }
//
//        }
//    }



@Composable
private fun CardContent(name: String){
    var expanded by remember { mutableStateOf(false)} // 재구성에서 상태를 유지하려면 remember를 사용

    Row (
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = {expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320) //widthDp는 Composable의 가로 크기의 치수를 설정하는 것이다.
// 미리보기를 사용하기 위해서는 @Preview로 사용
@Composable
private fun DefaultPreview1(){ // Jetpack_Compose_Tutorial에 이미 DefaultPreview가 있어서 1로사용
    BasicCodeLab_BBangChanTheme {
        MyApp()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview2() {
    BasicCodeLab_BBangChanTheme {
        Greetings()
    }
}


