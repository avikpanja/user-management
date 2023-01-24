package com.estock.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.estock.user.constant.Constants;

@Service
public class KafkaPublisher {

	// @Autowired
	// private KafkaTemplate<String, String> kfkTmp;
	
	// public void publish(String actualText) {
	// 	this.kfkTmp.send(Constants.TOPIC_TOKEN, actualText);
	// 	//System.out.println("Published successfully");
	// }
}
