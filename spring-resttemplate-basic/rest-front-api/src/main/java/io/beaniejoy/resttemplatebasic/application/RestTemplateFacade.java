package io.beaniejoy.resttemplatebasic.application;

import io.beaniejoy.resttemplatebasic.entity.ExampleGetterDto;
import io.beaniejoy.resttemplatebasic.entity.MemberDto;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateFacade {

    private final RestClient restClient;

    private static final String REST_BACK_API = "/api/back";

    public RestTemplateFacade(RestClient restClient) {
        this.restClient = restClient;
    }

    public MemberDto getMember() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path(REST_BACK_API + "/member")
                .build();
        return restClient.getObject(uriComponents.toString(), MemberDto.class);
    }

    public ExampleGetterDto getExampleGetter() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path(REST_BACK_API + "/example/getter")
                .build();
        return restClient.getObject(uriComponents.toString(), ExampleGetterDto.class);
    }
}
