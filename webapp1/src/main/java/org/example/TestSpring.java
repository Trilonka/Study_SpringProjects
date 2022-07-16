package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        boolean comparison = firstMusicPlayer==secondMusicPlayer;

        System.out.println(comparison);

        System.out.println(firstMusicPlayer);
        System.out.println(secondMusicPlayer);

        firstMusicPlayer.setVolume(10);

        System.out.println(firstMusicPlayer.getVolume());
        System.out.println(secondMusicPlayer.getVolume());

        ClassicalMusic classicalMusic1 = context.getBean("musicBean1", ClassicalMusic.class);
        System.out.println(classicalMusic1.getSong());

        ClassicalMusic classicalMusic2 = context.getBean("musicBean1", ClassicalMusic.class);
        System.out.println(classicalMusic2.getSong());

        System.out.println(classicalMusic1==classicalMusic2);

        context.close();
    }
}