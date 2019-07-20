package com.mm;

public class Pair
{
    private int score;
    private String key;

    private Pair(int score, String key)
    {
        this.score = score;
        this.key = key;
    }

    public static Pair of(int score, String key)
    {
        return new Pair(score, key);
    }

    int getScore()
    {
        return score;
    }

    String getKey()
    {
        return key;
    }

    String[] getParsedKeys()
    {
        return key.split("_");
    }
}
