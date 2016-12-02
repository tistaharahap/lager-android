package com.porkandlager.travelonbudget.mvp.Home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.crashlytics.android.answers.Answers
import com.porkandlager.travelonbudget.R
import io.fabric.sdk.android.Fabric

class HomeActivity : AppCompatActivity(), HomeView, SeekBar.OnSeekBarChangeListener {

    private var presenter: HomePresenter? = null

    @BindView(R.id.fullscreen_content) internal var mContentView: View? = null
    @BindView(R.id.fullscreen_content_controls) internal var mControlsView: View? = null
    @BindView(R.id.budget) internal var mBudgetText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)

        presenter = HomePresenterImpl(this)

        // Budget Slider
        (findViewById(R.id.budget_slider) as SeekBar).setOnSeekBarChangeListener(this)

        presenter!!.makeFullScreen(supportActionBar, mControlsView)

        Fabric.with(this, Answers())
    }

    @OnClick(R.id.go_button) internal fun goButtonClicked() {
        presenter!!.onGoButtonClicked(this, mBudgetText)
    }

    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
        presenter!!.onBudgetSliderChanged(mBudgetText, i)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    override fun makeFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            } else {
                mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        } else {
            mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        val actionBar = actionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.hide()
        }
    }

    override fun getActivity(): Activity {
        return this
    }
}
