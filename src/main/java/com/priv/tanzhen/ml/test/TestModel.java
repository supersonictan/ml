package com.priv.tanzhen.ml.test;

import com.priv.tanzhen.ml.model.IModel;
import com.priv.tanzhen.ml.model.ftrl.FTRL;

import java.io.*;

/**
 * Created by TanZhen on 2016/11/21.
 */
public class TestModel {
    public static IModel ftrl;

    public static void FTRLTest(){
        int featureNum = 11;
        ftrl = new FTRL(featureNum-1);
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("D:\\training.csv")));
            String line = null;
            while ((line = br.readLine()) != null){
                String[] fields = line.split(",");
                if(fields.length != 11){
                    continue;
                }
                double label = Double.parseDouble(fields[0]);
                double[] example = new double[featureNum];
                example[featureNum-1] = label;
                for (int i=0; i<featureNum-1; i++){
                    example[i] = Double.parseDouble(fields[i]);
                }
                ftrl.train(example);
            }
            ftrl.debug();


            //double[] testSet = {0.997001499,34,1,0.616011505,2085,8,3,1,4,0};
            //double[] testSet = {0.273516159,63,0,0.217837622,7500,15,0,1,0,0};
            //System.out.println("Result:" + ftrl.score(testSet));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void calMSE() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("D:\\testSet.csv")));
        String line;
        int featureNum = 10;
        double mse = 0.0d;
        int n = 0;
        while ((line = br.readLine()) != null){
            n++;
            String[] fields = line.split(",");
            if(fields.length != 11){
                continue;
            }
            double label = Double.parseDouble(fields[0]);
            double[] example = new double[featureNum];
            for (int i=0; i<featureNum-1; i++){
                example[i] = Double.parseDouble(fields[i]);
            }
            double res = ftrl.score(example);
            mse += Math.pow((label-res),2);
            System.out.println(mse);
        }
        System.out.println("MSE=" + mse/n + ",n="+n + " ,mse=" + mse);
    }


    public static void main(String[] args) throws Exception {
        FTRLTest();
        calMSE();
    }
}
