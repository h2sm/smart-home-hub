package com.h2sm.smarthomehub.device.ewelinkBulbs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TurnOnLights {

    private Map<String, String> data = new HashMap<String, String>() {{
        put("switch", "on");
    }};

}
