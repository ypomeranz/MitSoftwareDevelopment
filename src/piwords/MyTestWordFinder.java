package piwords;

import java.util.HashMap;
import java.util.Map;


public class MyTestWordFinder{
    public static void main(String[] args){
        basicGetSubstringsTest();
    }
    
    public static void basicGetSubstringsTest() {
        //Test 1
        System.out.println("Test 1");
        String haystack = "abcde";
        String[] needles = {"ab", "abc", "de", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
        expectedOutput.put("ab", 0);
        expectedOutput.put("abc", 0);
        expectedOutput.put("de", 3);
        
        Map<String, Integer> actualOutput = WordFinder.getSubstrings(haystack, needles);
        
       if (expectedOutput.equals(actualOutput)){
           System.out.println("success");
       } else {
           System.out.println("test fails");
           System.out.println("expected output:");
           System.out.println(expectedOutput.entrySet());
           System.out.println("actual output:");
           System.out.println(actualOutput.entrySet());
       }
    }

}