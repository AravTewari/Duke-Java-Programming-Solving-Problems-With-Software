
/**
 * Using the Simplified Algortithm Reorganized
 * 
 * @author Arav Tewari 
 * @version July 1, 2020
 */

public class Part2 
{
    public static String findSimpleGene(String dna, String startCodon, String stopCodon)
    {
        String result = "";
        
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1)
        {
            return "start colon not found";
        }
        
        int endIndex = dna.indexOf(stopCodon, startIndex + 3);
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
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String dna = "GATTATGTTGATACTATAAGGCT"; //good DNA
        System.out.println("DNA #1 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon));
        
        dna = "TACGATAGCCATGGCACTAA"; //gene is not proper
        System.out.println("\nDNA #2 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon));
        
        dna = "CATCGGACCAGATAA"; //no start colon
        System.out.println("\nDNA #3 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon));
        
        dna = "ATGGACCAGATAG"; //no end colon
        System.out.println("\nDNA #4 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon));
        
        dna = "ATGTAA"; //simple gene
        System.out.println("\nDNA #5 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon)); 
        
        dna = "ATGGGTTAAGTC"; //All uppercase letters
        System.out.println("\nDNA #6 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon, stopCodon));
        
        dna = "gatgctataat";
        System.out.println("\nDNA #7 is: " + dna);
        System.out.println(findSimpleGene(dna, startCodon.toLowerCase(), stopCodon.toLowerCase()));
    }
}
