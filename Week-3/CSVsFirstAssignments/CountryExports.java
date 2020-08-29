
/**
 * Parsing Export Data
 * 
 * @author Arav Tewari 
 * @version June 4, 2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports 
{
    private String countryInfo(CSVParser parser, String country)
    {
        String info = "";
        
        for (CSVRecord record : parser)
        {
            if (record.get("Country").equals(country))
            {
                info = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                break;
            }
        }
        return info;
    }
    
    private void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    private int numberOfExporters(CSVParser parser, String exportItem)
    {
        int counter = 0;
        
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem))
            {
                counter ++;
            }
        }
        return counter;
    }
    
    private void bigExporters(CSVParser parser, String amount)
    {
        int minLength = amount.length();
        
        for (CSVRecord record: parser)
        {
            String currAmount = record.get("Value (dollars)");
            int length = currAmount.length();
            
            if (length > minLength)
            {
                System.out.println(record.get("Country") + " " + currAmount);
            }
        }
    }
    
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "cocoa");
        //System.out.println(numberOfExporters(parser, "sugar"));
        bigExporters(parser, "$999,999,999,999");
    }
}
