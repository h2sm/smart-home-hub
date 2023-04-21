package com.h2sm.smarthomehub.device.ewelinkBulbs;

import java.util.List;
import java.util.Map;

public class RequestJSONs {
    public static String TURN_ON = "{\n" +
            "    \"data\":{\n" +
            "        \"switch\": \"on\"\n" +
            "    }\n" +
            "}";

    public static String TURN_OFF = "{\n" +
            "    \"data\":{\n" +
            "        \"switch\": \"off\"\n" +
            "    }\n" +
            "}";

    public static String changeColor(Map<String, String> brgb) {//brightness,red,green,blue
        String brightness = brgb.get("brightness");
        String red = brgb.get("r");
        String green = brgb.get("g");
        String blue = brgb.get("b");

        return "{\n" +
                " \"data\":{\n" +
                " \"ltype\": \"color\",\n" +
                " \"color\": {\"br\":" + brightness + ", \"r\":" + red + ", \"g\": " + green+ ", \"b\": " + blue + "}\n" +
                " }\n" +
                "}";
    }


}
