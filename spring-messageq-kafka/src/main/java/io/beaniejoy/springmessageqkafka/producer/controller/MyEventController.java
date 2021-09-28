package io.beaniejoy.springmessageqkafka.producer.controller;

import io.beaniejoy.springmessageqkafka.producer.service.MyEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyEventController {

    private final MyEventService myEventService;

    @PostMapping("/event")
    public ResponseEntity<String> publishEvent() {

        myEventService.publishEvent();
        return ResponseEntity.ok("message publish complete");
    }
}
