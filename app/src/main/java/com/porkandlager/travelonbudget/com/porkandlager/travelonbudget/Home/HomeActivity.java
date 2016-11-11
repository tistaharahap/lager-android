package com.porkandlager.travelonbudget.com.porkandlager.travelonbudget.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.porkandlager.travelonbudget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeView,
        SeekBar.OnSeekBarChangeListener {

    private HomePresenter presenter;

    @BindView(R.id.fullscreen_content_controls) private View mControlsView;
    @BindView(R.id.budget) private TextView mBudgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new HomePresenterImpl(this);

        // Budget Slider
        ((SeekBar) findViewById(R.id.budget_slider)).setOnSeekBarChangeListener(this);
    }

    @OnClick(R.id.go_button) void goButtonClicked() {
        presenter.onGoButtonClicked(mBudgetText);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        presenter.makeFullScreen(getSupportActionBar(), mControlsView);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        presenter.onBudgetSliderChanged(mBudgetText, i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
