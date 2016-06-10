package com.ziqisu.acc20;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ziqisu on 6/3/16.
 */
public class collectData extends Thread implements SensorEventListener{
    protected static SampleData SampleData = new SampleData();
    protected static SampleData SampleData1 = new SampleData();
    protected static SampleData SampleData2 = new SampleData();
    final static BlockingQueue<SampleData> blockingQueue = new ArrayBlockingQueue<SampleData>(3);
    protected static int Switch =0;
    protected SampleData s;


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (SampleData.num == 0 && SampleData1.num == 0 &&
                SampleData2.num == 0 ) {
            Switch = 0;
                //FFT.addElement(event.values);
        }else if (SampleData.num == 256 && SampleData1.num == 0 ) {
            Switch = 1;
            //FFT.SampleData2.num=0;
            if(SampleData2.num==512){
                try{
                    s=SampleData2;
                    blockingQueue.put(s);
                    SampleData2.num=0;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //s=SampleData2;
               // SampleData2.num=0;
            }
            //FFT.addElement(event.values);
        }else if (SampleData.num == 512 && SampleData1.num == 256 ) {
            try{
                s = SampleData;
                blockingQueue.put(s);
                SampleData.num=0;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Switch = 2;
            //FFT.addElement(event.values);
        }else if (SampleData1.num== 512 && SampleData2.num == 256 ) {
            try{
                s = SampleData1;
                SampleData1.num = 0;
                blockingQueue.put(s);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Switch = 3;
            //FFT.SampleData1.num = 0;
            //FFT.addElement(event.values);
        }


        addElement(event.values);




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static void Addition (float[] S,SampleData data, int a){
        try{
            /*Log.i("S[0]:",Float.toString(S[0]));
            Log.i("S[1]:",Float.toString(S[1]));
            Log.i("S[2]:",Float.toString(S[2]));*/
            data.dataX[a] = S[0];
            data.dataY[a] = S[1];
            data.dataZ[a] = S[2];
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }

    public static void addElement(float[] S){
        if(Switch ==0){
            Addition(S,SampleData,SampleData.num);
            SampleData.num++;
            //Log.i("Num: ", String.valueOf(SampleData.num));
            //FFT.Switch = 0;
        }if(Switch==1){
            Addition(S,SampleData,SampleData.num);
            Addition(S,SampleData1,SampleData1.num);
            SampleData.num++;
            SampleData1.num++;
            //Log.i("Num1: ", String.valueOf(SampleData1.num));
            //Log.i("Num: ", String.valueOf(SampleData.num));
            //FFT.Switch =1;
        }if(Switch==2) {

            Addition(S,SampleData1,SampleData1.num);
            Addition(S,SampleData2,SampleData2.num);
            SampleData1.num++;
            SampleData2.num++;

            //Log.i("Num1: ", String.valueOf(SampleData1.num));
            //Log.i("Num2: ", String.valueOf(SampleData2.num));
            //FFT.Switch =2;
        }if(Switch==3){

            Addition(S,SampleData2,SampleData2.num);
            Addition(S,SampleData,SampleData.num);
            SampleData2.num++;
            SampleData.num++;
            //Log.i("Num2: ", String.valueOf(SampleData2.num));
            //Log.i("Num3: ", String.valueOf(SampleData3.num));
            //FFT.Switch =3;
        }
    }
}
