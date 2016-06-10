package com.ziqisu.acc20;

/**
 * Created by ziqisu on 6/3/16.
 */
import android.os.Environment;
import android.os.Message;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.os.Message;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.lang.Object;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Runnable;




import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.SerializationHelper;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.classifiers.Evaluation;
import weka.core.FastVector;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.meta.FilteredClassifier;
import weka.filters.unsupervised.attribute.Remove;




public class Feature extends Thread {



    String[] activity = {"Run","Drive","Sit","Climb Stairs","Walk"};

    static int message;
    Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        public void run(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.activitytext.setText(activity[message]);
                }
            });
        }
    };







    public void run()  {
        J48 tree = new J48();

        if(true){
            try {

                BufferedReader reader = new BufferedReader(new FileReader("/sdcard/1.arff"));
                Instances data = new Instances(reader);
                Log.i("the first instance is:",data.firstInstance().toString());
                reader.close();

                // setting class attribute

                data.setClassIndex(data.numAttributes() - 1);
                //Classifier tree = new J48();
                //tree.setUnpruned(true);
                // train and make predictions
                tree.buildClassifier(data);
                // setting class attribute if the data format does not provide this information
                // For example, the XRFF format saves the class attribute information as well
                if (data.classIndex() == -1)
                    data.setClassIndex(data.numAttributes() - 1);
                Log.i("the tree is:",tree.toString());
                //Instances test = new Instances(new BufferedReader(new FileReader("/sdcard/test.arff")));
            } catch (IOException e) {
                e.printStackTrace();
            }catch( Exception e){
                e.printStackTrace();
            }
        }


        SampleData sampledata = new SampleData();


        Attribute Attribute1 = new Attribute("MeanX");
        Attribute Attribute2 = new Attribute("EnergyX");
        Attribute Attribute3 = new Attribute("EntropyX");
        Attribute Attribute4 = new Attribute("CorrletiondataXY");
        Attribute Attribute5 = new Attribute("CorrelationFFTXY");
        Attribute Attribute6 = new Attribute("MeanY");
        Attribute Attribute7 = new Attribute("EnergyY");
        Attribute Attribute8 = new Attribute("EntropyY");
        Attribute Attribute9 = new Attribute("CorrletiondataYZ");
        Attribute Attribute10 = new Attribute("CorrelationFFTYZ");
        Attribute Attribute11 = new Attribute("MeanZ");
        Attribute Attribute12 = new Attribute("EnergyZ");
        Attribute Attribute13 = new Attribute("EntropyZ");
        Attribute Attribute14 = new Attribute("CorrletiondataXZ");
        Attribute Attribute15 = new Attribute("CorrelationFFTXZ");
        FastVector fvClassVal = new FastVector(5);
        fvClassVal.addElement("Run");
        fvClassVal.addElement("Drive");
        fvClassVal.addElement("Sit");
        fvClassVal.addElement("ClimbStairs");
        fvClassVal.addElement("Walk");
        Attribute ClassAttribute = new Attribute("theClass", fvClassVal);
        // Declare the feature vector
        FastVector fvWekaAttributes = new FastVector(16);
        fvWekaAttributes.addElement(Attribute1);
        fvWekaAttributes.addElement(Attribute2);
        fvWekaAttributes.addElement(Attribute3);
        fvWekaAttributes.addElement(Attribute4);
        fvWekaAttributes.addElement(Attribute5);
        fvWekaAttributes.addElement(Attribute6);
        fvWekaAttributes.addElement(Attribute7);
        fvWekaAttributes.addElement(Attribute8);
        fvWekaAttributes.addElement(Attribute9);
        fvWekaAttributes.addElement(Attribute10);
        fvWekaAttributes.addElement(Attribute11);
        fvWekaAttributes.addElement(Attribute12);
        fvWekaAttributes.addElement(Attribute13);
        fvWekaAttributes.addElement(Attribute14);
        fvWekaAttributes.addElement(Attribute15);
        fvWekaAttributes.addElement(ClassAttribute);

        // Create an empty training set
        Instances isTrainingSet = new Instances("activityrecognition", fvWekaAttributes, 10);
        // Set class index
        isTrainingSet.setClassIndex(15);
        double result = -1;





        while (MainActivity.start) {
            isTrainingSet.delete();

            try{
                sampledata = collectData.blockingQueue.take();
            }catch(InterruptedException e){
                e.printStackTrace();
            }


            Instance inst = new DenseInstance(15);



            sampledata.FFTX = FFT.computeFFT(sampledata.dataX);
            sampledata.FFTY = FFT.computeFFT(sampledata.dataY);
            sampledata.FFTZ = FFT.computeFFT(sampledata.dataZ);
            sampledata.FFTXreal = FFT.getReal(sampledata.FFTX);
            sampledata.FFTYreal = FFT.getReal(sampledata.FFTY);
            sampledata.FFTZreal = FFT.getReal(sampledata.FFTZ);



            inst.setValue((Attribute)fvWekaAttributes.elementAt(0),Mean.computeMean(sampledata.dataX));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(1),Energy.computeEnergy(sampledata.FFTX));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(2),Entropy.computeEntropy(sampledata.FFTX));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(3),Correlation.computeCorrelation(sampledata.dataX, sampledata.dataY));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(4),Correlation.computeCorrelation(sampledata.FFTXreal,sampledata.FFTYreal));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(5),Mean.computeMean(sampledata.dataY));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(6),Energy.computeEnergy(sampledata.FFTY));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(7),Entropy.computeEntropy(sampledata.FFTY));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(8),Correlation.computeCorrelation(sampledata.dataY, sampledata.dataZ));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(9),Correlation.computeCorrelation(sampledata.FFTYreal,sampledata.FFTZreal));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(10),Mean.computeMean(sampledata.dataZ));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(11),Energy.computeEnergy(sampledata.FFTZ));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(12),Entropy.computeEntropy(sampledata.FFTZ));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(13),Correlation.computeCorrelation(sampledata.dataX, sampledata.dataZ));
            inst.setValue((Attribute)fvWekaAttributes.elementAt(14),Correlation.computeCorrelation(sampledata.FFTXreal,sampledata.FFTZreal));


            isTrainingSet.add(inst);



            try {

                double[] fDistribution;
                fDistribution = tree.distributionForInstance(isTrainingSet.firstInstance());

                if(fDistribution[0] > 0.6){
                    message=0;
                    new Thread(runnable).start();
                }else if(fDistribution[1]> 0.6){
                    message=1;
                    new Thread(runnable).start();
                }else if(fDistribution[2]> 0.6){
                    message=2;
                    new Thread(runnable).start();
                }else if(fDistribution[3]> 0.6){
                    message=3;
                    new Thread(runnable).start();
                }else if(fDistribution[4]> 0.6){
                    message=4;
                    new Thread(runnable).start();
                }else{
                    message=5;
                    new Thread(runnable).start();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }








        }
    }

}
