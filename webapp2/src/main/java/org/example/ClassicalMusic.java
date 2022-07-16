package org.example;

import org.springframework.stereotype.Component;

@Component("someClassicalMusic")
public class ClassicalMusic implements Music {

    public String[] songs = new String[] {"c_first", "c_second", "c_third"};

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
