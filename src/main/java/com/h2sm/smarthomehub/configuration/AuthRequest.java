package com.h2sm.smarthomehub.configuration;

import com.h2sm.smarthomehub.dtos.auth.AuthHubDTO;
import com.h2sm.smarthomehub.dtos.auth.TokenDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthRequest {

    public static TokenDTO authHub() {
        RestTemplate restTemplate = new RestTemplate();
        var url = "http://localhost:8082/api/auth/hub";
        return restTemplate.postForObject(url,new AuthHubDTO("666", "lol"), TokenDTO.class);
    }
}
