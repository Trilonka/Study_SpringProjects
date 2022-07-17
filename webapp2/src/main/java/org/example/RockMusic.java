package org.example;

import org.springframework.stereotype.Component;

//@Component("someRockMusic")
public class RockMusic implements Music {

    public String[] songs = new String[] {"r_first", "r_second", "r_third"};

    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
