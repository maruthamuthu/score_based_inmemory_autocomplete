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

    public int getScore()
    {
        return score;
    }

    public String getKey()
    {
        return key;
    }

    public String[] getParsedKeys()
    {
        return key.split("_");
    }
}
