package com.example.administrator.doinmvp.presenter;
import android.os.Handler;
import android.os.Message;
import com.example.administrator.doinmvp.model.IWeatherModel;
import com.example.administrator.doinmvp.model.WeatherModel;
import com.example.administrator.doinmvp.view.IWeatherView;
/**
 * Created by Administrator on 2016/4/24.
 */
public class WeatherPresenter {
    private IWeatherView mWeatherView;
    private IWeatherModel mWeatherModel;

    Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            String content = msg.obj.toString();
            mWeatherView.setWeatherData(content);
        }
    };
    public WeatherPresenter(IWeatherView view) {
        mWeatherModel = new WeatherModel();
        mWeatherView = view;
    }
    public void loadWeatherData(final String cityName) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String content = mWeatherModel.requestData(cityName);
                Message message = new Message();
                message.obj = content;
                handler.sendMessage(message);//发送message信息
            }
        }).start();
    }

}
