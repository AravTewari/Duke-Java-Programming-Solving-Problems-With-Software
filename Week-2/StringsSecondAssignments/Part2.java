
/**
 * Finding multiple occurences - how many times does stringA occur in stringB?
 * 
 * @author Arav Tewari 
 * @version June 2, 2020
 */
public class Part2 
{
    private static int howMany(String stringA, String stringB)
    {
        int times = 0;
        int currIndex = 0;
        
        while (currIndex < stringB.length())
        {
            int indexA = stringB.indexOf(stringA, currIndex);
            if (indexA != -1)
            {
                times++;
            }
            currIndex += stringA.length();
        }
        return times;
    }
    
    public static void testHowMany()
    {
        System.out.println("Testing \"howMany\" method\n");
        
        String stringA = "an";
        String stringB = "banana";
        int times = howMany(stringA, stringB);
        if (times != 2)
            System.out.println("Error in test #1: " + times);
            
        stringA = "a";
        stringB = "arav";
        times = howMany(stringA, stringB);
        if (times != 2)
            System.out.println("Error in test #2: " + times);
            
        stringA = "c";
        stringB = "ccccc";
        times = howMany(stringA, stringB);
        if (times != 5)
            System.out.println("Error in test #3: " + times);
        
        stringA = "ATG";
        stringB = "ATGATGATGATGATGATG";
        times = howMany(stringA, stringB);
        if (times != 6)
            System.out.println("Error in test #4: " + times);
            
        stringA = "c";
        stringB = "forest";
        times = howMany(stringA, stringB);
        if (times != 0)
            System.out.println("Error in test #5: " + times);
            
        System.out.println("\nTesting finished");
    }
}
