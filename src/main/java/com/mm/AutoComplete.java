package com.mm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class AutoComplete implements Serializable
{
    private HashMap<String, TreeMap<Integer, TreeSet<String>>> autoCompleteData = new HashMap<String, TreeMap<Integer, TreeSet<String>>>();

    public LinkedHashSet<String> search(String key)
    {
        final LinkedHashSet<String> result = new LinkedHashSet<String>();

        TreeMap<Integer, TreeSet<String>> matchedDetails = autoCompleteData.get(key);
        if (matchedDetails != null)
        {
            matchedDetails.forEach((k, v) -> {
                if (v != null)
                    result.addAll(v);
            });
        }
        return result;
    }



    public void init(List<Pair> scorePairs)
    {
        scorePairs.stream().forEach(this::storePair);
    }

    private void storePair(Pair pair)
    {
        parseKey(pair.getKey(), pair);
        String[] keys = pair.getParsedKeys();
        if (keys.length > 1)
        {
            for (int i = 1; i < keys.length; i++)
            {
                parseKey(keys[i], pair);
            }
        }
    }

    private void parseKey(String key, Pair pair)
    {
        int start = 0;
        for (int end = 1; end <= key.length(); end++)
        {
            storeKey(key.substring(start, end), pair);
        }
    }


    private void storeKey(String key, final Pair pair)
    {
        TreeMap<Integer, TreeSet<String>> treeMap = autoCompleteData.computeIfAbsent(key, k -> new TreeMap<>());
        treeMap.compute(pair.getScore(), (k, v) -> this.updateKeyList(v, pair.getKey()));
    }

    private TreeSet<String> updateKeyList(TreeSet<String> existingKeyList, String newKey)
    {
        if (existingKeyList == null)
        {
            existingKeyList = new TreeSet<>();
        }
        existingKeyList.add(newKey);
        return existingKeyList;
    }


}
