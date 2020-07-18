package com.wht.demo.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * desc.
 *
 * @author wht
 */
public class ConsumerDemo {

    static  final  String topic = "test";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
        props.put("group.id", "1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);


        consumer.subscribe(Collections.singletonList(topic));

        ConsumerRecords<String, String> msgList=consumer.poll(1000);

        if (msgList == null){
            System.out.println("list为空");
        }

        for (ConsumerRecord<String, String> record : msgList) {
            System.out.println(record.key() + ":" + record.value());
        }

        consumer.close();

    }
}
