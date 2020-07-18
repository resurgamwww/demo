package com.wht.demo.mq.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * desc.
 *
 * @author wht
 */
public class ProducerDemo {

    static final String topic = "test";

    public static void main(String[] args) {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092,localhost:9093");//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.ACKS_CONFIG, "all");
        //p.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 10 * 32 * 1024 * 1024L);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        try {
            int i = 0;
            while (true) {
                long begin = System.currentTimeMillis();
                //System.out.println("开始了");
                String s = String.valueOf(i);
                String msg = "Hellosdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff," + s;
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,5,s, msg);
                kafkaProducer.send(record);
                kafkaProducer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.printf("%d, %d, %d  \r\n", recordMetadata.partition(), recordMetadata.offset(), recordMetadata.serializedValueSize());
                    }
                });
                //kafkaProducer.flush();
                //System.out.println("cost:" + (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - begin)));
                System.out.println("消息发送成功:");

                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}
