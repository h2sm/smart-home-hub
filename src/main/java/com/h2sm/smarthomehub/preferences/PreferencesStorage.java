package com.h2sm.smarthomehub.preferences;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.prefs.Preferences;

@Service
public class PreferencesStorage {
//    private static final Preferences pref = Preferences.systemRoot();

    @SneakyThrows
    public static String getUUID(){
//        String uuid = pref.get("UUID", null);
//        if (uuid == null) {
//            uuid = RandomStringUtils.random(8, "0123456789abcdefgh");
//            pref.put("UUID", uuid);
//            pref.flush();
//        }
//        return uuid;
        return "Text haha";
    }

    public static void setSecret(){}

    public static void getSecret(){}

}
