package com.ziqisu.acc20;

/**
 * Created by ziqisu on 6/3/16.
 */
public class Mean {
    static float Meansum;
    public static float computeMean(float[] S){
        Meansum=0f;
        for(int i=0; i<S.length;i++){
            Meansum=Meansum+S[i];
        }
        return Meansum/S.length;
    }
}
