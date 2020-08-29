
/**
 * Write a description of Part1 here.
 * 
 * @author Arav Tewari
 * @version June 2, 2020
 */
public class Part1 
{
   private static void findAbc(String input) 
   {
    int index = input.indexOf("abc");
    while (true) 
    {
        if (index == -1) 
        {
            break;
        }
        if (index > input.length() - 4)
        {
            break;
        }
        
        //System.out.println("index: " + index);
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        //System.out.println("index after updating: " + index);
    }
   }
   
   public static void test() 
   {
    //findAbc("abcd");
    //findAbc("abcdabc");
    //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
    findAbc("abcabcabcabca");
   }
}
