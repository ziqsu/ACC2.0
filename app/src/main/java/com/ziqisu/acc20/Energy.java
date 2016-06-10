package com.ziqisu.acc20;

/**
 * Created by ziqisu on 6/3/16.
 */
public class Energy {
    protected static float EnergySum;
    public static float computeEnergy(float[] X){
        EnergySum=0f;
        for(int i=0; i<X.length;i++){
            EnergySum=EnergySum+X[i]*X[i];

            //EnergySum=EnergySum+X[i]*X[i];
        }
        return EnergySum/512;

    }
}
