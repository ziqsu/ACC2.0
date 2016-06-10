package com.ziqisu.acc20;

import android.util.Log;

/**
 * Created by ziqisu on 6/3/16.
 */
public class Correlation {
    static float crossCorrelation;



    public static float computeCorrelation(float[] A, float[] B){
        /*
        the function I use is (the sum of (Xi-X)*(Yi-Y))/(  sqrt(the sum of (Xi-X)^2)*sqrt(the sum of (Yi-Y)^2) )
        where X and Y are mean and i from 0 to n
        it is the simple cross correlation
         */
        float DivisorX =0f;
        float DivisorY =0f;
        float Dividend =0f;
        float meanA = Mean.computeMean(A);
        float meanB = Mean.computeMean(B);
        float crossCorrelation =0;
        if(A.length==B.length){
            for(int i=0;i<A.length;i++){
                DivisorX=DivisorX+(A[i]-meanA)*(A[i]-meanA);
                DivisorY=DivisorY+(B[i]-meanB)*(B[i]-meanB);
                Dividend=Dividend+(A[i]-meanA)*(B[i]-meanB);
            }
            crossCorrelation=Dividend/(DivisorX*DivisorY);
        }else{
            Log.i("Error:","float[] A and float[]B have different length");
        }
        return crossCorrelation;
    }
}
