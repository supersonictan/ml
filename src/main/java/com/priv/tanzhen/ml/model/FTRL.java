package com.priv.tanzhen.ml.model;

import java.util.List;

/**
 * Created by TanZhen on 2016/11/20.
 */
public class FTRL implements IModel {
    private static final long serialVersionUID = -7762112720648622188L;
    private double alpha = 0.1D;
    private double beta = 1.0D;
    private double lambdaOne = 1.0D;
    private double lambdaTwo = 1.0D;
    private double[] z; //z
    private double[] weights; //learning rate
    private double[] n; //n
    private int featureNum = 0;


    public FTRL(double alpha, double beta, double lambdaOne, double lambdaTwo, int featureNum) {
        this.alpha = alpha;
        this.beta = beta;
        this.lambdaOne = lambdaOne;
        this.lambdaTwo = lambdaTwo;
        this.featureNum = featureNum;
        z = new double[featureNum];
        weights = new double[featureNum];
        n = new double[featureNum];
    }

    public synchronized void train(double[] example){
        if (example == null || example.length == 0){
            return;
        }
        double label = example[featureNum];


    }

    public void mTrain(List<Double[]> examples){

    }
    public double score(double[] inst) {
        return 0;
    }

    /*cal sigmoid*/
    private double sigmoid(double[] features){
        if (features == null || features.length == 0){
            return 0.0d;
        }
        double sum = 0.0;
        for (int i=0; i<features.length; i++){
            sum += features[i] * weights[i];
        }

        return 1.0 / (1.0 + Math.exp(-sum));
    }
    public String debug() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println((int)0.0D);
    }
}
