package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test
import java.util.*
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.overview.OverviewBody

class TopAppBarTest {

    @get : Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

//        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule
            .onNode(
            hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                    hasParent(
                        hasContentDescription(RallyScreen.Accounts.name)
                    ),
                    useUnmergedTree = true
        )
            .assertExists()
//        composeTestRule
//            .onNodeWithContentDescription(RallyScreen.Accounts.name)
//            .assertExists() // Still fails
    }


    @Test
    fun rallyTopAppBarTest_clickTabs(){
        val allScreens = RallyScreen.values().toList()
        var currentScreen: RallyScreen = RallyScreen.Overview // 현재 상태
        composeTestRule.setContent {
            // composeTestRule에 RallyApp 설정하기
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {screen -> currentScreen = screen},
                currentScreen = RallyScreen.Accounts)
        }
            // 모든 탭을 순회하면서 클릭하고 현재 상태를 확인한다.
            RallyScreen.values().forEach { screen ->
                composeTestRule
                    .onNodeWithContentDescription(screen.name)
                    .performClick()
                assert(currentScreen == screen )
            }
        }
    

    @Test
    fun rallyTopAppBarTest_clickTabs2(){
        var currentScreen:RallyScreen = RallyScreen.Overview
        composeTestRule.setContent {
            RallyApp(currentScreen){ screen-> currentScreen = screen }
        }

        RallyScreen.values().forEach { screen->
            composeTestRule
                .onNodeWithContentDescription(screen.name)
                .performClick()
            assert(currentScreen == screen)
        }
    }
}

