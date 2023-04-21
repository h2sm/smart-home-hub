package com.h2sm.smarthomehub.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthHubDTO implements Serializable {
    private String hubUuid;
    private String hubSecret;
}
