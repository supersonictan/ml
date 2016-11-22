package com.priv.tanzhen.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TanZhen on 2016/11/22.
 */
public class KafkaTest {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
        Date curDate = new Date(System.currentTimeMillis());    //获取当前时间
        String str = formatter.format(curDate);
        System.out.println(str);
    }
}
