package com.mm;

import java.io.Serializable;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Integer>, Serializable
{
    private static final ScoreComparator SCORE_COMPARATOR = new ScoreComparator();

    @Override
    public int compare(Integer o1, Integer o2)
    {
        return (o1 < o2) ? 1 : ((o1 == o2) ? 0 : -1);
    }

    public static ScoreComparator getScoreComparator()
    {
        return SCORE_COMPARATOR;
    }
}
