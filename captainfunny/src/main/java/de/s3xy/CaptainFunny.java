package de.s3xy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CaptainFunny {

    private static Random randomGenerator = new Random();

    private static final List<String> jokes = Arrays.asList(
            "What do computers and air conditioners have in common? They both become useless when you open windows",
            "There are only 10 kinds of people in this world: those who know binary and those who don’t.",
            "Programming is 10% science, 20% ingenuity, and 70% getting the ingenuity to work with the science.",
            "How many programmers does it take to change a light bulb?\nNone – It’s a hardware problem");


    public static Joke tellJoke() {
        Joke j = new Joke();
        j.setContent(jokes.get(randomGenerator.nextInt(jokes.size())));
        return j;
    }
}
