package piwords;

public class BTTest{
    public static void main(String[] args){
        testBaseTranslator();
    }
    
    public static void testBaseTranslator(){
        System.out.println("Test 1: Convert 1,4,1,5,9,2,6,5 from base ten to base 16 up to 7 digits");
        int[] test = {1,4,1,5,9,2,6,5};
        int[] result = BaseTranslator.convertBase(test,10,16,7);
        System.out.println("Expected result: [2,4,3,15,6,10,7]");
        System.out.println("Actual result:");
        System.out.println(java.util.Arrays.toString(result));
        
        System.out.println("Test2: Convert 3 from base 10 to base 3 up to six digits");
        int[] test1 = {3};
        int[] result1 = BaseTranslator.convertBase(test1, 10, 3, 6);
        System.out.println("Expected result: 022002");
        System.out.println("Actual result:");
        System.out.println(java.util.Arrays.toString(result1));
        
        System.out.println("Test 3a,b,and c - expecting null");
        System.out.println("Base A < 2");
        int[] zeroes={0,0,0,0,0};
        if (BaseTranslator.convertBase(zeroes,1,3,6) == null){
            System.out.println("Success");
        } else {
            System.out.println("Test fails");
        }
        System.out.println("digit >= base A:");
        int[] test2 = {1,2,3,12};
        if (BaseTranslator.convertBase(test2, 12,16,4) == null){
            System.out.println("Success");
        } else {
            System.out.println("Test fails");
        }
        
        System.out.println("Test 4: converting from base 3 to base 16");
        System.out.println("Result is unknown so using my base 10 entry and converting to base 16");
        int[] Test4a = {3};
        int[] Test4b = {0,2,2,0,0,2,2,0,0,2,2,0,0,2,2,0,0,2,2,0};
        System.out.println("Next two results should be equal");
        int[] result4a = BaseTranslator.convertBase(Test4a, 10, 16, 6);
        int[] result4b = BaseTranslator.convertBase(Test4b, 3, 16, 6);
        System.out.println(java.util.Arrays.toString(result4a));
        System.out.println(java.util.Arrays.toString(result4b));
        
    }
}