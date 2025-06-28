package com.brunadev.rapidApi

import org.junit.Test

class MainActivityTest {

    @Test
    fun givenHomeActivityWhenClickButtonCheckDisplayedItens() {
        withMainActivity {
            launchEmptyActivity()
        } actions {
            clickAtSearchView()
        } verify {
            checkIsDisplayed()
            checkAppName()
        }
    }


    @Test
    fun givenHomeActivityWhenClickEventCheckDisplayedItens() {
        withMainActivity {
            launchEmptyActivity()
        } actions {
            shimmerView()
            scrollLayout()
        } verify {
            checkDetailsIsDisplayed()
        }
    }
}