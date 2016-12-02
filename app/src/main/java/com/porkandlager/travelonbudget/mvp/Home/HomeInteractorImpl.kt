package com.porkandlager.travelonbudget.mvp.Home

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent

/**
 * Created by tista on 11/11/16.
 */

internal class HomeInteractorImpl : HomeInteractor {

    override fun logGoButtonClicked(budget: String) {
        Answers.getInstance().logCustom(CustomEvent("Go Button Clicked")
                .putCustomAttribute("Budget", budget))
    }

}
