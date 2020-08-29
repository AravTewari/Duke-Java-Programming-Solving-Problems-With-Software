
/**
 * Problem Solving with Strings
 * 
 * @author Arav Tewari 
 * @version June 1, 2020
 */

public class Part3 
{
    public static boolean twoOccurences(String stringA, String stringB)
    {
        boolean twoTimes = false;
        if(stringB.contains(stringA))
        {
            int val = stringB.indexOf(stringA);
            String newStringB = stringB.substring(val + stringA.length(), stringB.length());
            
            if(newStringB.contains(stringA))
            {
                twoTimes = true;
            }
        }
        return twoTimes;
    }
    
    public static String lastPart(String stringA, String stringB)
    {
        String result = "";
        
        if(stringB.contains(stringA))
        {
            int val = stringB.indexOf(stringA);
            result = stringB.substring(val + stringA.length());
        }
        else
        {
            result = stringB;
        }
        return result;
    }
    
    public static void test()
    {
        /* Checks twoOccurences method */
        System.out.println("Checking twoOccurencesMethod");
        
        String stringA = "by"; //true
        String stringB = "A story by Abby Long";
        System.out.println("\nTest String #1: " + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(twoOccurences(stringA, stringB));
        
        stringA = "a"; //true
        stringB = "banana";
        System.out.println("\nTest String #2: " + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(twoOccurences(stringA, stringB));
        
        stringA = "atg"; //false
        stringB = "ctgtatgta";
        System.out.println("\nTest String #3: "  + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(twoOccurences(stringA, stringB));
        
        /* Checks lastPart method */
        System.out.println("\nChecking lastPart method");
        
        stringA = "an"; //should return "ana"
        stringB = "banana";
        System.out.println("\nTest String #1: " + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(lastPart(stringA, stringB));
        
        stringA = "zoo"; //should return "forest"
        stringB = "forest";
        System.out.println("\nTest String #2: " + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(lastPart(stringA, stringB));
        
        stringA = "w"; //should return "atcher"
        stringB = "watcher";
        System.out.println("\nTest String #3: " + stringA);
        System.out.println("Checking against: " + stringB);
        System.out.println(lastPart(stringA, stringB));
    }
}
