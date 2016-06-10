package com.ziqisu.acc20;

/**
 * Created by ziqisu on 6/3/16.
 */
public class Entropy {
    static double EntropySum;
    public static double computeEntropy(float[] S){
        EntropySum=0;
        double sum = 0;
        for(int j=0; j<S.length;j++){
            sum=sum+S[j]*S[j];
        }
        sum=sum/257;
        for(int i =0; i<S.length;i++){
            double d =(double) S[i];
            d =d/sum;
            EntropySum= EntropySum-(  (double)d * (  (double)Math.log( Math.abs(d) )/(double)Math.log(2) )  );

        }
        return EntropySum;
    }
}
