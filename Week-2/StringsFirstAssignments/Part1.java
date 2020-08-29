
/**
 * Finding a Gene - Using the Simplified Algorithm
 * 
 * @author Arav Tewari 
 * @version June 1, 2020
 */
public class Part1 
{
    public static String findSimpleGene(String dna)
    {
        String result = "";
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
        {
            return "start colon not found";
        }
        
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if(endIndex == -1)
        {
            return "end colon not found";
        }
        
        if ((endIndex - startIndex) % 3 == 0)
        {
            return dna.substring(startIndex, endIndex + 3);
        }
        else
        {
            return "gene is not proper";
        }
    }
    
    public static void testSimpleGene()
    {
        String dna = "GATTATGTTGATACTATAAGGCT"; //good DNA
        System.out.println("DNA #1 is: " + dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "TACGATAGCCATGGCACTAA"; //gene is not proper
        System.out.println("\nDNA #2 is: " + dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "CATCGGACCAGATAA"; //no start colon
        System.out.println("\nDNA #3 is: " + dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGGACCAGATAG"; //no end colon
        System.out.println("\nDNA #4 is: " + dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGTAA"; //simple gene
        System.out.println("\nDNA #5 is: " + dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "";
        System.out.println("\nDNA #6 is: " + dna);
        System.out.println(findSimpleGene(dna));
    }
}
