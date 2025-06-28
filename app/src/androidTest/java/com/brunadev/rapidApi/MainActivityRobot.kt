package com.brunadev.rapidApi

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.brunadev.rapidApi.repository.Repository
import com.brunadev.rapidApi.presenter.Main.MainActivity


fun MainActivityTest.withMainActivity(
    func: MainActivityRobot.() -> Unit
) = MainActivityRobot().apply(func)


class MainActivityRobot {

    private lateinit var repository: Repository

    fun launchEmptyActivity() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        ActivityScenario.launch<MainActivity>(intent)
    }

    fun clickAtSearchView() = R.id.search_view.click()
    fun shimmerView() = Thread.sleep(1000)
    fun scrollLayout() = R.id.rvlist.click()

    infix fun actions(func: MainActivityRobot.() -> Unit) = this.apply(func)

    infix fun verify(func: LogonActivityResult.() -> Unit) = LogonActivityResult().apply(func)
}

class LogonActivityResult {

    fun checkIsDisplayed() {
        R.id.search_view.isDisplayed()
        R.id.title_text.isDisplayed()
    }

    fun checkAppName() {
        "TM EVENTS".isTextDisplayed()
    }

    fun checkDetailsIsDisplayed() {
        R.id.title_data.isDisplayed()
        R.id.name_data.isDisplayed()
    }

}