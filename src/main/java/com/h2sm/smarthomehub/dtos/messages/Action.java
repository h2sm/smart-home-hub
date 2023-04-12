package com.h2sm.smarthomehub.dtos.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action implements Serializable {
    private String action;
    private Map<String, String> map;
}