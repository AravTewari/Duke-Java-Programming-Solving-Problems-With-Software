
/**
 * Counts how many genes in a given strand of DNA.
 * 
 * @author Arav Tewari 
 * @version June 2, 2020
 */
public class Part3 
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
        
        if (minIndex < dna.length())
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
                break;
            }
            else
            {
                System.out.println(currGene);
                currIndex += currGene.length();
            }
        }
    }
    
    private static int countGenes(String dna)
    {
        int currIndex = 0;
        int counter = 0;
        while (true)
        {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty())
            {
                break;
            }
            else
            {
                counter ++;
                currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
            }
        }
        return counter;
    }
    
    public static void testCountGenes()
    {
        System.out.println("Testing \"countGenes\" method\n");
        
        String dna = "ATGxxxxxxTAAxxxATGxxxxxxTAGxxxATGxxxxxxTGAxxx";
        int genes = countGenes(dna);
        if (genes != 3)
        {
            System.out.println("Wrong answer in test #1: " + genes);
        }
        
        dna = "AGTxxxxxxTAA";
        genes = countGenes(dna);
        if (genes != 1)
        {
            System.out.println("Wrong answer in test #2: " + genes);
        }
        
        dna = "AGTxxTAAxxxxTAG";
        genes = countGenes(dna);
        if (genes != 1)
        {
            System.out.println("Wrong answer in test #3: " + genes);
        }
        
        dna = "AGTTAGAGTTGAAGTTAA";
        genes = countGenes(dna);
        if (genes != 3)
        {
            System.out.println("Wrong answer in test #4: " + genes);
        }
        
        System.out.println("\nTesting finished");
    }
}
