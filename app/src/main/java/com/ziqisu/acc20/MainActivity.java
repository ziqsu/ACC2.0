package com.ziqisu.acc20;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.PowerManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private Sensor mySensor;
    private SensorManager SM;

    // start and stop button will change the boolean start to be true and false
    static boolean start = false;

    protected collectData cData = null;
    protected Feature f = null;
    protected Handler handler;

    //set up activitytext, it will take show on the screen which activity is been recording now
    public static TextView activitytext;
    private PowerManager.WakeLock mWakeLock;
    static String[] activity = {"Run","Drive","Sit","Climb Stairs","Walk","Cannot recognize activity"};









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        mWakeLock.acquire();


        activitytext = (TextView) findViewById(R.id.activitytext);
        activitytext.setVisibility(View.INVISIBLE);
        activitytext.setCursorVisible(false);


        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setupStartButton();
        setupStopButton();

        handler = new Handler();

    }


    public void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
    }

    private void setupStartButton() {
        //get a reference to the button
        Button walkbutton = (Button)findViewById(R.id.startbutton);
        //set the click listener to run my code
        walkbutton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //after press start, set start to true it will start store data
                        start = true;
                        //ComputeFFT = new FFT();
                        //ComputeFFT.start();
                        cData = new collectData();
                        cData.start();
                        SM.registerListener(cData,mySensor, SensorManager.SENSOR_DELAY_FASTEST);
                        f = new Feature();
                        f.start();
                        //it will show on screen that the accelerometer starts to collect data
                        activitytext.setText("Start Collecting Data");
                        activitytext.setVisibility(View.VISIBLE);

                    }
                }
        );
    }

    private void setupStopButton() {
        //get a reference to the button
        Button walkbutton = (Button)findViewById(R.id.stopbutton);
        //set the click listener to run my code
        walkbutton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //stop collecting data and unregister the sensor manager
                        try{
                            start = false;
                            SM.unregisterListener(cData);
                            activitytext.setText("Stop Collecting data");
                            activitytext.setVisibility(View.VISIBLE);
                        }catch(NullPointerException e){
                            e.printStackTrace();
                        }
                        /*start = false;
                        SM.unregisterListener(store);
                        activitytext.setText("Stop Collecting data");
                        activitytext.setVisibility(View.VISIBLE);*/
                    }
                }
        );
    }












}
