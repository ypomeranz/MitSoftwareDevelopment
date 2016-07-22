package piwords;

import java.util.HashMap;
import java.util.Map;

public class AlphabetGenerator {
    /**
     * Given a numeric base, return a char[] that maps every digit that is
     * representable in that base to a lower-case char.
     * 
     * This method will try to weight each character of the alphabet
     * proportional to their occurrence in words in a training set.
     * 
     * This method should do the following to generate an alphabet:
     *   1. Count the occurrence of each character a-z in trainingData.
     *   2. Compute the probability of each character a-z by taking
     *      (occurrence / total_num_characters).
     *   3. The output generated in step (2) is a PDF of the characters in the
     *      training set. Convert this PDF into a CDF for each character.
     *   4. Multiply the CDF value of each character by the base we are
     *      converting into.
     *   5. For each index 0 <= i < base,
     *      output[i] = (the first character whose CDF * base is > i)
     * 
     * A concrete example:
     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
     *               "ccccc..." (198 "c"s)}, base = 93
     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 193
     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
     *      Pr(c) = 198 / 1000 = .198
     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
     *   5. Output = {"a", "a", ... (28 As, indexes 0-27),
     *                "b", "b", ... (47 Bs, indexes 28-74),
     *                "c", "c", ... (18 Cs, indexes 75-92)}
     * 
     * The letters should occur in lexicographically ascending order in the
     * returned array.
     *   - {"a", "b", "c", "c", "d"} is a valid output.
     *   - {"b", "c", "c", "d", "a"} is not.
     *   
     * If base >= 0, the returned array should have length equal to the size of
     * the base.
     * 
     * If base < 0, return null.
     * 
     * If a String of trainingData has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * @param base A numeric base to get an alphabet for.
     * @param trainingData The training data from which to generate frequency
     *                     counts. This array is not mutated.
     * @return A char[] that maps every digit of the base to a char that the
     *         digit should be translated into.
     */
    public static char[] generateFrequencyAlphabet(int base,
                                                   String[] trainingData) {
        HashMap<Character, Integer> frequencyList = new HashMap<Character, Integer>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabeta = alphabet.toCharArray();
        // Initialize all letters in HashMap to 0
        for (int i = 0; i < 26; i++){
            char l = alphabet.charAt(i);
            frequencyList.put(l, 0);
        }
        //Now go through the training data, word by word, character by character. 
        // Count total number of characters and update the hashamp
        int totalchars = 0;
        for (int i = 0; i < trainingData.length; i++){
            String word = trainingData[i];
            for (int j = 0; j < word.length(); j++){
                String letter = word.substring(j,j+1);
                //Making sure this is one letter, not none with a print statement
                //System.out.println(letter);
                if (alphabet.contains(letter)){
                    frequencyList.put(letter.charAt(0), frequencyList.get(letter.charAt(0))+1);
                    totalchars = totalchars + 1;
                }
            }
        }
        
        //Now - new HashMap for probabilities
        HashMap<Character, Double> probabilityDistributionFunction = new HashMap<Character, Double>();
        for (char key : frequencyList.keySet()){
            double prob = (double) frequencyList.get(key) / totalchars;
            probabilityDistributionFunction.put(key, prob);
        }
        
        //Yet another hashmap - this time cdf - and order matters- going to be ugly and switch to
        // a char array of alphabet to iterate over
        HashMap<Character, Double> cumulativeDistributionFunction = new HashMap<Character, Double>();
        double cumval = 0.0;
        for (char key : alphabeta){
            cumval = cumval + probabilityDistributionFunction.get(key);
            cumulativeDistributionFunction.put(key, cumval);
        }
        
        //Working with this same HashMap this time
        //Multiply each value by the base we are converting into
        for (char key : cumulativeDistributionFunction.keySet()){
            cumulativeDistributionFunction.put(key, cumulativeDistributionFunction.get(key)*base);
        }
        
        //Now create the final array and fill with characters propotional to freq
        char[] finalArray = new char[base];
        int i = 0;
        for (char letter : alphabeta){
            while (cumulativeDistributionFunction.get(letter) > i){
                finalArray[i] = letter;
                i++;
                if (i >= base){
                break;
                }
            }
            
            
        }
        //System.out.println(java.util.Arrays.toString(finalArray));
        return finalArray;
        
        
        
        
    }
}
