package com.h2sm.smarthomehub.stompService;

import com.h2sm.smarthomehub.device.ewelinkBulbs.RequestJSONs;
import com.h2sm.smarthomehub.dtos.messages.Action;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class StompService {
    private final RestTemplate restTemplate;

    public Action handleAction(Action action) {
        var type = action.getAction();
        switch (type) {
            case "TURN_ON": {
                return turnOnLights(action);
            }
            case "TURN_OFF": {
                return turnOffLights(action);
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
        return new Action(null, null);
    }

    private Action turnOnLights(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/switch";
        return sendCommand(RequestJSONs.TURN_ON, url);

    }

    private Action turnOffLights(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/switch";
        return sendCommand(RequestJSONs.TURN_OFF, url);

    }

    private void changeColor(Action action) {
        String url = "http://" + action.getMap().get("ip") + ":8081/zeroconf/dimmable";

        String requestJson = RequestJSONs.changeColor(action.getMap());
        sendCommand(requestJson, url);
    }

    private void getStateOfDevices(Action action) {

    }

    private Action sendCommand(String requestJson, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        var map = new HashMap<String, String>();
        map.put("STATUS", answer);
        return new Action("RESPONSE", map);
    }
}
