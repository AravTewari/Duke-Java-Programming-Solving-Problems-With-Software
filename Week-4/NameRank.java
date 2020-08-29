
/**
 * Write a description of NameRank here.
 * 
 * @author Arav Tewari
 * @version June 5, 2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NameRank 
{
    public void printNames() 
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) 
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) 
            {
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) +
        			   " Num Born " + rec.get(2));
            }
        }
    }

    private void totalBirths(FileResource fr) 
    {
    	int totalBirths = 0;
    	int totalBoys = 0;
    	int totalGirls = 0;
    	int totalGirlNames = 0;
    	int totalBoyNames = 0;
    	int totalNames = 0;
    	
    	for (CSVRecord rec : fr.getCSVParser(false)) 
    	{
    	    int numBorn = Integer.parseInt(rec.get(2));	
    	    totalBirths += numBorn;
    	    totalNames++;
        		
    	    if (rec.get(1).equals("M")) 
    	    {
    	        totalBoys += numBorn;
    	        totalBoyNames++;
    	    }
    	    else 
    	    {
    	        totalGirls += numBorn;
    	        totalGirlNames++;
    	    }
    	}
    
    	System.out.println("total births = " + totalBirths);
    	System.out.println("female babies = " + totalGirls);
    	System.out.println("male babies = " + totalBoys);
    	System.out.println("\ntotal names = " + totalNames);
    	System.out.println("total female names = " + totalGirlNames);
    	System.out.println("total male names = " + totalBoyNames);
    }

    public void testTotalBirths() 
    {
    	FileResource fr = new FileResource();
    	//FileResource fr = new FileResource("data/yob2014.csv");
    	totalBirths(fr);
    }
    
    private int getRank(int year, String name, String gender)
    {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        
        int index = fr.asString().indexOf(name + "," + gender);
        if (index == -1)
        {
            return index;
        }
        
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        
        for (CSVRecord record : parser)
        {
            String currName = record.get(0);
            String currGender = record.get(1);
            
            if (currGender.equals(gender))
            {
                currRank++;
                if (currName.equals(name))
                {
                    break;
                }
            }
        }
        return currRank;
    }
    
    public void testGetRank()
    {
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    private String getName(int year, int rank, String gender)
    {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        String name = "";
        
        for (CSVRecord record : parser)
        {
            String currGender = record.get(1);
            if (currGender.equals(gender))
            {
                currRank++;
                if (rank == currRank)
                {
                    name = record.get(0);
                }
            }
        }
        
        if (!name.isEmpty())
        {
            return name;
        }
        else
        {
            return "NO NAME";
        }
    }
    
    public void testGetName()
    {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    private void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    private int yearOfHighestRank(String name, String gender)
    {
        int currYear = 1880;
        int maxYear = 2014;
        int highestYear = -1;
        int maxRank = Integer.MAX_VALUE;
        
        for (; currYear <= maxYear; currYear++)
        {
            int currRank = getRank(currYear, name, gender);
            
            if (currRank < maxRank  && currRank != -1)
            {
                highestYear = currYear;
                maxRank = currRank;
            }
        }
        return highestYear;
    }
    
    public void testYearOfHighestRank()
    {
        int maxYear = yearOfHighestRank("Mich", "M");
        System.out.println(maxYear);
    }
    
    private double getAverageRank(String name, String gender)
    {
        int currYear = 1880;
        int maxYear = 2014;
        double sum = 0;
        int counter = 0;
        
        for (; currYear <= maxYear; currYear++)
        {
            int currRank = getRank(currYear, name, gender);
            sum += currRank;
            counter++;
        }
        if (sum > 0)
        {
            return sum / counter;
        }
        else
        {
            return -1.0;
        }
    }
    
    public void testGetAverageRank()
    {
        double avgRank = getAverageRank("Robert", "M");
        System.out.println(avgRank);
    }
    
    private int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int maxRank = getRank(year, name, gender);
        int totalBirths = 0;
        
        for (CSVRecord record : parser)
        {
            String currGender = record.get(1);
            
            if (currGender.equals(gender))
            {
                String currName = record.get(0);
                int currRank = getRank(year, currName, currGender);
                
                if (currRank < maxRank)
                {
                    int currBirths = Integer.parseInt(record.get(2));
                    totalBirths += currBirths;
                }
                else if(currRank == maxRank)
                {
                    break;
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher()
    {
        int totalBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirths);
    }
}
