package com.priv.tanzhen.ml.model;

import java.io.Serializable;

/**
 * Created by TanZhen on 2016/11/20.
 */
public interface IModel extends Serializable {


    public void train(double[] example);
    public double score(double[] inst);
    public void addInstance(double[] example);
    public String debug();
}
