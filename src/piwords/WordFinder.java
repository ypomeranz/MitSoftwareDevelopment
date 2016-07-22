package piwords;

import java.util.HashMap;
import java.util.Map;

public class WordFinder {
    /**
     * Given a String (the haystack) and an array of Strings (the needles),
     * return a Map<String, Integer>, where keys in the map correspond to
     * elements of needles that were found as substrings of haystack, and the
     * value for each key is the lowest index of haystack at which that needle
     * was found. A needle that was not found in the haystack should not be
     * returned in the output map.
     * 
     * @param haystack The string to search into.
     * @param needles The array of strings to search for. This array is not
     *                mutated.
     * @return The list of needles that were found in the haystack.
     */
    public static Map<String, Integer> getSubstrings(String haystack,
                                                     String[] needles) { 
        /*how this function works:
        for (string in needles){
            1. get string length
            2. go down haystack and compare string with substring of haystack
                if the strings match - save the index as y
                if you get to the end, save -1 as y
            if the index is not -1, add it to the hashmap
        }
        */
        HashMap<String, Integer> finalmap = new HashMap<String, Integer>();
        for (int i = 0; i < needles.length; i++){
            String needle = needles[i];
            int y = -1;
            int stringlength = needle.length();
            for (int j = 0; j <= (haystack.length() - stringlength); j++){
                String hstacksubstring = haystack.substring(j, j+stringlength);
                //System.out.println("needle: " +needle);
                //System.out.println("hstacksubstring: "+hstacksubstring); //this is for debugging - delete later
                if (hstacksubstring.equals(needle)){
                    //System.out.println("equality of results has been recognized");
                    y = j;
                    break;
                }
            }
            //System.out.println("y is " +y);
            if (y != -1){
                finalmap.put(needle, y);
            }
        }
        
        return finalmap;
    }
}
