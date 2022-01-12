package io.beaniejoy.resttemplatebasic.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    private final Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Value("${back.uri}")
    private String backUri;

    public <T> T getObject(String api, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        logger.info("==========================================");
        logger.info("RestTemplate getForObject " + api);
        logger.info("==========================================");

        try {
            return restTemplate.getForObject(backUri + api, responseType);
        } catch (HttpStatusCodeException e) {
            // api에서 에러 발생시 4xx, 5xx 에러코드 반환
            logger.error(e.getMessage());
            logger.error("Error Status [{}]", e.getStatusCode().toString());
            logger.error("Error Body :: {}", e.getResponseBodyAsString());
            return null;
        }
    }
}
