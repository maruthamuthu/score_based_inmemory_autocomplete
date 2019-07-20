package com.mm.sample;

import com.mm.Pair;

import java.util.ArrayList;
import java.util.List;

public class SamplePairs
{
    public static List<Pair> getSamplePairs()
    {
        ArrayList<Pair> pairs = new ArrayList<>();
        pairs.add(Pair.of(12, "abcd"));
        pairs.add(Pair.of(10, "abc"));
        pairs.add(Pair.of(3, "a_b"));
        pairs.add(Pair.of(1, "a_b_"));
        pairs.add(Pair.of(5, "ab"));
        pairs.add(Pair.of(8, "a_b_c"));
        pairs.add(Pair.of(8, "a_b_c_"));
        return pairs;
    }
}
