package com.priv.tanzhen.ml.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by TanZhen on 2016/11/20.
 */
public class FTRL implements IModel {
    private static final Logger logger = LogManager.getLogger(FTRL.class);
    private static final long serialVersionUID = -7762112720648622188L;
    private double alpha = 0.1D; //learning rate param
    private double beta = 1.0D; //learning rate param
    private double lambdaOne = 0.7D;
    private double lambdaTwo = 1.0D;
    private double[] z; //z
    private double[] weights; //weights
    private double[] n; //n
    private int featureNum = 0;


    public FTRL(int featureNum){
        this.featureNum = featureNum;
        z = new double[featureNum];
        weights = new double[featureNum];
        n = new double[featureNum];
    }
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
        /* get example class label*/
        double label = example[featureNum];

        /* cal weight*/
        for (int i=0; i<featureNum; i++){
            double zi = z[i];
            double ni = n[i];
            if (Math.abs(zi) > lambdaOne) {
                int sgn = zi > 0 ? 1 : -1;
                double w = -(zi - sgn * lambdaOne) / ((beta + Math.sqrt(ni))/alpha + lambdaTwo);
                weights[i] = w;
            }
        }

        /* update param*/
        double pScore = sigmoid(example, true);
        for (int i=0; i<featureNum; i++) {
            double gradient = (pScore - label) * example[i];
            double sigma = (Math.sqrt(n[i] + gradient*gradient) - Math.sqrt(n[i])) / alpha;
            double zi = z[i] + gradient - sigma*weights[i];
            double ni = n[i] + gradient*gradient;
            z[i] = zi;
            n[i] = ni;
        }

    }
    public void mTrain(List<Double[]> examples){


    }

    /*predict*/
    public double score(double[] inst) {
        return sigmoid(inst, false);
    }

    public void addInstance(double[] example) {

    }

    /*cal sigmoid. if inputfeature is trainingSet hasLabel=true*/
    private double sigmoid(double[] features, boolean hasLabel){
        if (features == null || features.length == 0){
            return 0.0d;
        }
        double sum = 0.0;
        int len = hasLabel ? features.length-1 : features.length;
        for (int i=0; i<len; i++){
            sum += features[i] * weights[i];
        }

        return 1.0 / (1.0 + Math.exp(-sum));
    }
    public String debug() {
        StringBuffer sb = new StringBuffer();
        for (Double d: weights){
            sb.append(d).append("\t");
        }
        logger.info(sb.toString());
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
