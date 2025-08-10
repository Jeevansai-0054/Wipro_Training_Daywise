package com.wipro.uber.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RideProducer {

    private static final String TOPIC = "U2R";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStatusUpdate(Long rideId, String status) {
        StatusDTO dto = new StatusDTO(rideId, status);
        String message = new Gson().toJson(dto);
        kafkaTemplate.send(TOPIC, message);
    }

    private static class StatusDTO {
        private Long rideId;
        private String status;

        public StatusDTO(Long rideId, String status) {
            this.rideId = rideId;
            this.status = status;
        }
    }
}