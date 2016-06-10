package com.ziqisu.acc20;

import java.util.Arrays;

/**
 * Created by ziqisu on 6/3/16.
 */
public class SampleData {
    protected String activity;
    float[] dataX= new float[512];
    float[] dataY= new float[512];
    float[] dataZ= new float[512];
    float[] FFTXreal= new float[257];
    float[] FFTYreal = new float[257];
    float[] FFTZreal = new float[257];

    protected  float[] FFTX = new float[512];
    protected  float[] FFTY = new float[512];
    protected  float[] FFTZ = new float[512];
    protected float meanX;
    protected float meanY;
    protected float meanZ;
    protected float energyX;
    protected float energyY;
    protected float energyZ;
    protected double entropyX;
    protected double entropyY;
    protected double entropyZ;
    protected float correlationXY;
    protected float correlationYZ;
    protected float correlationXZ;
    protected int num =0;




    public SampleData( float[] dataX,float[] dataY,float[] dataZ,
                       String activity,
                       float[] FFTX,float[] FFTY,float[] FFTZ,
                       float meanX,float meanY,float meanZ,
                       float energyX,float energyY,float energyZ,
                       float correlationXY,float correlationYZ,float correlationXZ,
                       double entropyX, double entropyY,double entropyZ) {
        Arrays.fill(dataX,0);
        Arrays.fill(dataY,0);
        Arrays.fill(dataZ,0);
        this.dataX = dataX;
        this.dataY = dataY;
        this.dataZ = dataZ;
        this.activity = activity;
        this.FFTX= FFTX;
        this.FFTY = FFTY;
        this.FFTZ = FFTZ;
        this.meanX = meanX;
        this.meanY =meanY;
        this.meanZ =meanZ;
        this.energyX = energyX;
        this.energyY = energyY;
        this.energyZ =energyZ;
        this.correlationXY = correlationXY;
        this.correlationXZ = correlationXZ;
        this.correlationYZ = correlationYZ;
        this.entropyX = entropyX;
        this.entropyY = entropyY;
        this.entropyZ = entropyZ;


    }

    public SampleData() {
    }

    public float[] getdataX() {
        return dataX;
    }

    public void setdataX(float[] data) {
        this.dataX = dataX;
    }
    public void changedataX(float a, int b){
        this.dataX[b]=a;
    }
    public void changedataY(float a, int b){
        this.dataY[b]=a;
    }
    public void changedataZ(float a, int b){
        this.dataZ[b]=a;
    }
    public float[] getdataY() {
        return dataY;
    }


    public void setdataY(float[] data) {
        this.dataY = dataY;
    }
    public float[] getdataZ() {
        return dataZ;
    }

    public void setdataZ(float[] dataZ) {
        this.dataZ = dataZ;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity){
        this.activity =activity;
    }

    public float[] getFFTX(){
        return FFTX;
    }
    public void setFFTX(float[] FFTX){
        this.FFTX=FFTX;
    }
    public float[] getFFTY(){
        return FFTY;
    }
    public void setFFTY(float[] FFTY){
        this.FFTY=FFTY;
    }
    public float[] getFFTZ(){
        return FFTZ;
    }
    public void setFFTZ(float[] FFTZ){
        this.FFTZ=FFTZ;
    }

    public float getMeanX(){
        return meanX;
    }
    public void setMeanX(float meanX)
    {
        this.meanX = meanX;
    }
    public float getMeanY(){
        return meanY;
    }
    public void setMeanY(float meanY)
    {
        this.meanY = meanY;
    }
    public float getMeanZ(){
        return meanZ;
    }
    public void setMeanZ(float meanZ)
    {
        this.meanZ = meanZ;
    }

    public float getEnergyX(){
        return energyX;
    }
    public void setEnergyX(float energyX){
        this.energyX = energyX;
    }
    public float getEnergyY(){
        return energyY;
    }
    public void setEnergyY(float energyY){
        this.energyY = energyY;
    }
    public float getEnergyZ(){
        return energyZ;
    }
    public void setEnergyZ(float energyZ){
        this.energyZ = energyZ;
    }

    public double getEntropyX(){
        return entropyX;
    }
    public void setEntropyX(double entropyX){
        this.entropyX= entropyX;
    }
    public double getEntropyY(){
        return entropyY;
    }
    public void setEntropyY(double entropyY){
        this.entropyY= entropyY;
    }
    public double getEntropyZ(){
        return entropyZ;
    }
    public void setEntropyZ(double entropyZ){
        this.entropyZ= entropyZ;
    }

    public float getCorrelationXY(){
        return correlationXY;
    }
    public float getCorrelationYZ(){
        return correlationYZ;
    }
    public float getCorrelationXZ(){
        return correlationXZ;
    }
    public void setCorrelationXY(float correlationXY){
        this.correlationXY = correlationXY;
    }
    public void setCorrelationYZ(float correlationYZ){
        this.correlationYZ = correlationYZ;
    }
    public void setCorrelationXZ(float correlationXZ){
        this.correlationXZ = correlationXZ;
    }

    public void setFFTXreal(float[] FFTXreal){
        this.FFTXreal=FFTXreal;
    }
    public float[] getFFTXreal(){
        return FFTXreal;
    }
    public void setFFTYreal(float[] FFTYreal){
        this.FFTYreal=FFTYreal;
    }
    public float[] getFFTYreal(){
        return FFTYreal;
    }
    public void setFFTZreal(float[] FFTZreal){
        this.FFTZreal=FFTZreal;
    }
    public float[] getFFTZreal(){
        return FFTZreal;
    }
}
