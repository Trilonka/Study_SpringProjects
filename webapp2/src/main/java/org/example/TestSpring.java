package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

//        Music music = context.getBean("someRockMusic", Music.class);
//
//        MusicPlayer musicPlayer = new MusicPlayer(music);
//
//        musicPlayer.playMusic();
//
//        Music music2 = context.getBean("someClassicalMusic", Music.class);
//
//        MusicPlayer classicalMusicPlayer = new MusicPlayer(music2);
//
//        classicalMusicPlayer.playMusic();

//        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
//        player.playMusic();

//        Computer computer = context.getBean("computer", Computer.class);
//        System.out.println(computer);

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(player.getName());
        System.out.println(player.getVolume());

        ClassicalMusic classicalMusic1 = context.getBean("classicalMusic", ClassicalMusic.class);
        ClassicalMusic classicalMusic2 = context.getBean("classicalMusic", ClassicalMusic.class);

        System.out.println(classicalMusic1 == classicalMusic2);

        context.close();
    }
}