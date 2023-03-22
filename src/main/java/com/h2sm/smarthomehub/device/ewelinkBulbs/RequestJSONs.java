package com.h2sm.smarthomehub.device.ewelinkBulbs;

import java.util.List;

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

    public static final String g = "{\n" +
            " \"data\":{\n" +
            " \"ltype\": \"color\",\n" +
            " \"color\": {\"br\":100, \"r\":110, \"g\": 255, \"b\": 0}\n" +
            " }\n" +
            "}";

    public static String changeColor(List<String> brgb) {//brightness,red,green,blue
        String brightness = brgb.get(2);
        String red = brgb.get(3);
        String green = brgb.get(4);
        String blue = brgb.get(5);

        return "{\n" +
                " \"data\":{\n" +
                " \"ltype\": \"color\",\n" +
                " \"color\": {\"br\":" + brightness + ", \"r\":" + red + ", \"g\": " + green+ ", \"b\": " + blue + "}\n" +
                " }\n" +
                "}";
    }


}
