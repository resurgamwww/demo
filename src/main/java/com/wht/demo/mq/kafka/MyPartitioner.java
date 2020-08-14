package com.wht.demo.mq.kafka;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

/**
 * desc.
 *
 * @author wht
 */
public class MyPartitioner extends DefaultPartitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return super.partition(topic, key, keyBytes, value, valueBytes, cluster);
    }
}
