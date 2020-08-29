import edu.duke.*;

/**
 * Finding Web Links from the following site: https://www.dukelearntoprogram.com//course2/data/manylinks.html
 * 
 * @author Arav Tewari 
 * @version June 1, 2020
 */

public class Part4 
{
    public static void findYoutubeLinks()
    {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        System.out.println("Begin\n");
        
        for (String word: ur.words())
        {
            word = word.toLowerCase();
            if (word.contains("youtube.com".toLowerCase()))
            {
                int startIndex = word.indexOf("\"");
                int stopIndex = word.indexOf("\"", startIndex+1);
                
                System.out.println(word.substring(startIndex, stopIndex+1));
            }
        }
        System.out.println("\nEnd");
    }
}
