package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {

    private RockMusic music1;
    private ClassicalMusic music2;

    @Autowired
    public MusicPlayer(@Qualifier("someRockMusic") RockMusic music1,
                       @Qualifier("someClassicalMusic") ClassicalMusic music2)
    {
        this.music1 = music1;
        this.music2 = music2;
    }

    public String playMusic(Genre genre) {
        Random random = new Random();
        switch (genre) {
            case ROCK:
                return "Playing: " + music1.songs[random.nextInt(3)];
            case CLASSICAL:
                return "Playing: " + music2.songs[random.nextInt(3)];
            default:
                return "Noting to play";
        }
    }
}
