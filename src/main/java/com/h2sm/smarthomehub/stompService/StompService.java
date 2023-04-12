package com.h2sm.smarthomehub.stompService;

import com.h2sm.smarthomehub.device.ewelinkBulbs.RequestJSONs;
import com.h2sm.smarthomehub.dtos.messages.Action;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class StompService {
    private final RestTemplate restTemplate;

    public void handleAction(Action action) {
        var type = action.getAction();
        switch (type) {
            case "TURN_ON": {
                turnOnLights(action);
                break;
            }
            case "TURN_OFF": {
                turnOffLights(action);
                break;
            }
            case "CHANGE_COLOR": {
                changeColor(action);
                break;
            }
            case "GET_STATE_ALL": {
                getStateOfDevices(action);
                break;
            }
        }
    }

    private void turnOnLights(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/switch";
        sendCommand(RequestJSONs.TURN_ON, url);

    }

    private void turnOffLights(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/switch";
        sendCommand(RequestJSONs.TURN_OFF, url);

    }

    private void changeColor(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/dimmable";

        //String requestJson = RequestJSONs.changeColor();
        //sendCommand(req);
    }

    private void getStateOfDevices(Action action) {

    }

    private void sendCommand(String requestJson, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);
    }
}
