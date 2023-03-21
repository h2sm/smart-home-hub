package com.h2sm.smarthomehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SmarthomeHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthomeHubApplication.class, args);
    }

}
