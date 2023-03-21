package com.h2sm.smarthomehub.rest;

import com.h2sm.smarthomehub.device.DeviceDTO;
import com.h2sm.smarthomehub.device.ewelinkBulbs.TurnOffLights;
import com.h2sm.smarthomehub.device.ewelinkBulbs.TurnOnLights;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class RequestHandler {
    private RestTemplate restTemplate;

    public void turnOnLights(DeviceDTO deviceReceiver) {
        var result = restTemplate.postForObject(getURL(deviceReceiver) + "/zeroconf/switch", TurnOnLights.class, String.class);
        System.out.println(result);
    }

    public void turnOffLights(DeviceDTO deviceReceiver) {
        var result = restTemplate.postForObject(getURL(deviceReceiver) + "/zeroconf/switch", TurnOffLights.class, String.class);
    }

    public void changeColor(DeviceDTO deviceReceiver) {

    }

    public Object getDeviceInfo(DeviceDTO deviceReceiver) {
        return null;
    }

    private String getURL(DeviceDTO deviceDTO){
        return deviceDTO.getLocalIpAddress() + ":" + deviceDTO.getPort();
    }

}
