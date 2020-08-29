
/**
 * Several methods and tests for finding genes in a given strand of dna.
 * 
 * @author Arav Tewari 
 * @version June 2, 2020
 */

public class Part1 
{
    private static int findStopCodon(String dna, int startIndex, String stopCodon)
    {  
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        
        if(startIndex == -1 || stopIndex == -1)
            return dna.length();
        
        if ((stopIndex - startIndex) % 3 == 0)
            return stopIndex;
        else
            return dna.length();
    }
    
    private static String findGene(String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
            return "";
            
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaIndex, (Math.min(tagIndex, tgaIndex)));
        
        if (minIndex <= dna.length())
            return dna.substring(startIndex, minIndex + 3);
        else
            return "";
    }
    
    private static void printAllGenes(String dna)
    {
        int currIndex = 0;
        while (true)
        {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty())
            {
                System.out.println("Loop broken");
                break;
            }
            else
            {
                System.out.println(currGene);
                currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
            }
        }
    }
    
    public static void testPrintAllGenes()
    {
        String dna = "ATGxxxxxxTAAxxxATGxxxxxxTAGxxxATGxxxxxxTGAxxx";
        printAllGenes(dna);
        dna = "ATGxxxxxxTAGxxxAGTxxxxxxTAAxxx";
        printAllGenes(dna);
        System.out.println("Testing finished");
    }
    
    public static void testFindGene()
    {
        System.out.println("Testing for \"findGene\" method");
        
        String dna = "xxxATGxxxxxxTAAxxx"; //proper gene with "TAA" stop codon
        String gene = findGene(dna, 0);
        if (gene.isEmpty())
            System.out.println("Error in test #1");
        
        dna = "xxxATGxxxxxxTAGxxx"; //proper gene with "TAG" stop codon
        gene = findGene(dna, 0);
        if (gene.isEmpty())
            System.out.println("Error in test #2");
            
        dna = "xxxATGxxxxxxTGAxxx"; //proper gene with "TGA" stop codon
        gene = findGene(dna, 0);
        if (gene.isEmpty())
            System.out.println("Error in test #3");
            
        dna = "xxxxxxATGxxxTAGxxxTAAxxxTGA"; //proper gene with "TAG" stop codon before other ones
        gene = findGene(dna, 0);
        if (!gene.endsWith("TAG") || gene.isEmpty())
            System.out.println("Error in test #4: " + gene);
            
        dna = "xxxATGxxTAGxxxxTAA"; //proper gene with"TAA" stop codon and improper "TAG" stop codon
        gene = findGene(dna, 0);
        if (gene.isEmpty() || !gene.endsWith("TAA")) 
            System.out.println("Error in test #5: " + gene);
            
        dna = "xxxTAAxxx"; //improper gene with no start codon
        gene = findGene(dna, 0);
        if (!gene.isEmpty())
            System.out.println("Error in test #6: " + gene);
            
        dna = "AATGCTAACTAGCTGACTAAT";
        gene = findGene(dna, 0);
        System.out.println("Gene is: " + gene);
            
        System.out.println("\nTesting finished");
    }
    
    public static void testFindStopCodon()
    {
        System.out.println("Testing for \"findStopCodon\" method");
        String stopCodon = "TAA";
        
        String dna = "xxxxxxATGxxxxxxxxxTAAxxx"; //gene is proper
        int startIndex = dna.indexOf("ATG");
        int stopIndex = findStopCodon(dna, startIndex, stopCodon);
        if (stopIndex != 18)
            System.out.println("Error in test #1");
        
        dna = "ATGxxxxxxTAA"; //gene is proper
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        if (stopIndex != 9)
        {
            System.out.println("Error in test #2");
        }
        
        dna = "ATGTAA"; //gene is proper
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        if (stopIndex != 3)
        {
            System.out.println("Error in test #3");
        }
        
        dna = "xxxATGxxxxxTAAxxx"; //faulty gene, number of codons incorrect
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        if (stopIndex != dna.length())
        {
            System.out.println("Error in test #4");
        }
        
        dna = ""; //faulty gene, empty
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        if (stopIndex != dna.length())
        {
            System.out.println("Error in test #5");
        }
        
        System.out.println("Tests finished\n");
    }
}