package com.example.administrator.doinmvp.view;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.doinmvp.R;
import com.example.administrator.doinmvp.presenter.WeatherPresenter;
public class WeatherView extends Activity implements IWeatherView {
    Button btn;
    EditText input;
    TextView show;
    WeatherPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_view);
        initWidget();
        presenter = new WeatherPresenter(this);
        btn.setOnClickListener(new mOnclickListener());
    }
    private void initWidget() {
        btn = (Button) findViewById(R.id.btn);
        input = (EditText) findViewById(R.id.input);
        show = (TextView) findViewById(R.id.show);
    }
    private class mOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            presenter.loadWeatherData(input.getText().toString());
        }
    }
    @Override
    public void setWeatherData(String weather) {
        show.setText(weather);
    }
}