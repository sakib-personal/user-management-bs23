package bs23.practical.usermanagement.service;

import bs23.practical.usermanagement.payload.UserEvent;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Gson jsonConverter;

    public void produceUserEvent(UserEvent userEvent) {
        String jsonObject = jsonConverter.toJson(userEvent);
        kafkaTemplate.send("user-events", jsonObject);
        log.info("New user event sent to consumer. JSON: {}", jsonObject);
    }

    @KafkaListener(topics = "user-events")
    public void userEventConsumer(String message) {
        log.info("NEW USER EVENT => {}", message);
    }
}
