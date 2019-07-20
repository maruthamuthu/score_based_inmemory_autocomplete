package com.mm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

class AutoComplete implements Serializable
{
    // Generated using java serialver cmd
    private static final long serialVersionUID = 5698155672533153469L;

    private HashMap<String, TreeMap<Integer, TreeSet<String>>> autoCompleteData = new HashMap<String, TreeMap<Integer, TreeSet<String>>>();

    LinkedHashSet<String> search(String key)
    {
        final LinkedHashSet<String> suggestedMatches = new LinkedHashSet<String>(10);

        TreeMap<Integer, TreeSet<String>> matchedDetails = autoCompleteData.get(key);
        if (matchedDetails != null)
        {
            for (TreeSet<String> matchSets : matchedDetails.values())
            {
                for (String match : matchSets)
                {
                    if (suggestedMatches.size() >= 10)
                    {
                        break;
                    }
                    suggestedMatches.add(match);
                }

                if (suggestedMatches.size() == 10)
                {
                    break;
                }
            }
        }
        return suggestedMatches;
    }



    void init(List<Pair> scorePairs)
    {
        scorePairs.forEach(this::storePair);
    }

    private void storePair(Pair pair)
    {
        parseKey(pair.getKey(), pair);
        String[] keys = pair.getParsedKeys();
        if (keys.length > 1)
        {
            // i starts from 1 because the the first subset group parse was done through the whole key (line 53).
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
