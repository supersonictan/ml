package com.priv.tanzhen.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by TanZhen on 2016/11/22.
 */
public class ProducerTest {

    public static final String KAFKA_ZK = "hadoop1:2181/kafka,hadoop2:2181/kafka,hadoop3:2181/kafka,hadoop4:2181/kafka,hadoop5:2181/kafka,hadoop6:2181/kafka";
    public static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
    public static final String BROKER_LIST = "hadoop1:9092,hadoop2:9092,hadoop3:9092";
    public static final String ACK = "1";
    public static final String PARTITION_NUM = "6";


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("zk.connect", KAFKA_ZK);
        props.put("serializer.class", SERIALIZER_CLASS);    /*serializer.class为消息的序列化类*/
        props.put("metadata.broker.list", BROKER_LIST);     /*配置metadata.broker.list, 为了高可用, 最好配两个broker实例*/
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");     /*设置Partition类, 对队列进行合理的划分*/
        props.put("request.required.acks", ACK);    /*ACK机制, 消息发送需要kafka服务端确认*/
        props.put("num.partitions", PARTITION_NUM);

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        for (int i = 0; i < 10; i++) {
            // KeyedMessage<K, V>
            // 　　K对应Partition Key的类型
            // 　　V对应消息本身的类型
//　　 topic: "test", key: "key", message: "message"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss SSS");
            Date curDate = new Date(System.currentTimeMillis());    //获取当前时间
            String str = formatter.format(curDate);

            String msg = "idoall.org" + i + "=" + str;
            String key = i+"";
            producer.send(new KeyedMessage<String, String>("idoall_testTopic",key, msg));
        }
    }
}
