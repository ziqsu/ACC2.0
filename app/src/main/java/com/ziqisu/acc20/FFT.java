package com.ziqisu.acc20;

/**
 * Created by ziqisu on 6/3/16.
 */
import org.jtransforms.fft.FloatFFT_1D;
public class FFT {
    public static float[] computeFFT (float[] DATA){
        FloatFFT_1D fft = new FloatFFT_1D(DATA.length);
        float[] FFT = new float[DATA.length];
        for(int i =0; i<DATA.length;i++){
            FFT[i]=DATA[i];
        }
        fft.realForward(FFT);
        //Log.i("fft is:",fft.toString());
        return FFT;

    }

    public static float[] getReal(float[] S){
        float[] K = new float[S.length/2+1];
        for(int i=0;i<S.length;i++){
            if((i%2)==0){
                K[i/2]=S[i];
            } else if(i==1){
                K[S.length/2]=S[1];
            }
        }
        return K;
    }
}
