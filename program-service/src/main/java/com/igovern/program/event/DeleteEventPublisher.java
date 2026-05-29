package com.igovern.program.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteEventPublisher {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    private static final String DELETE_QUEUE =
            "igovern.delete.events";

    public void publish(DeleteEvent event) {

        try {
            String message =
                    objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(
                    DELETE_QUEUE,
                    message
            );

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Failed to publish delete event: "
                            + ex.getMessage()
            );
        }
    }
}