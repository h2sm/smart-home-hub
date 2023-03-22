package com.h2sm.smarthomehub.service;

import com.h2sm.smarthomehub.device.DeviceDTO;
import com.h2sm.smarthomehub.device.ewelinkBulbs.TurnON;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PostService {
    private RestTemplate restTemplate;

    public void sendRequest(DeviceDTO deviceDTO){
        restTemplate.postForObject(deviceDTO.getLocalIpAddress() + ":" + deviceDTO.getPort(), TurnON.class, String.class);
    }
}
