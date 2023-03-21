package com.h2sm.smarthomehub.device.ewelinkBulbs;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TurnOffLights {
    private Map<String, String> data = new HashMap<String, String>() {{
        put("switch", "on");
    }};
}
