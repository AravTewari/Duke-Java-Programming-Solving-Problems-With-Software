
/**
 * Parsing weather data
 * 
 * @author Arav Tewari 
 * @version June 4, 2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherData 
{
    private CSVRecord minTemperatureOfTwo(CSVRecord currentRow, CSVRecord minTemp)
    {
        if (minTemp == null) 
            {
                minTemp = currentRow;
            }
            else if (!currentRow.get("TemperatureF").equals("-9999"))
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(minTemp.get("TemperatureF"));
                
                if (currentTemp < smallestTemp) 
                {
                    minTemp = currentRow;
                }
            }
        return minTemp;
    }
    
    private CSVRecord minHumidityOfTwo(CSVRecord currentRow, CSVRecord minTemp)
    {
        if (minTemp == null) 
            {
                minTemp = currentRow;
            }
            else if (!currentRow.get("Humidity").equals("N/A"))
            {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(minTemp.get("Humidity"));
                
                if (currentTemp < smallestTemp) 
                {
                    minTemp = currentRow;
                }
            }
        return minTemp;
    }
    
    private CSVRecord coldestHourInFile(CSVParser parser) 
    {
        CSVRecord minTemp = null;
        
        for (CSVRecord currentRow : parser) 
        {
            minTemp = minTemperatureOfTwo(currentRow, minTemp);
        }
        return minTemp;
    }

    public void testColdestHourInFile () 
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
            
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    private String fileWithColdestTemperature()
    {
       DirectoryResource dr = new DirectoryResource();
       String fileName = "";
       CSVRecord minTemp = null;
       
       for (File f : dr.selectedFiles())
       {
           FileResource fr = new FileResource(f);
           CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
           
           if (minTemp == null)
           {
               minTemp = currentRow;
           }
           else if(!currentRow.get("TemperatureF").equals("-9999"))
           {
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double smallestTemp = Double.parseDouble(minTemp.get("TemperatureF"));
                
                if (currentTemp < smallestTemp) 
                {
                    minTemp = currentRow;
                    fileName = f.getPath();
                }
           }
       }
       return fileName;
    }
    
    public void testFileWithColdestTemperatures()
    {
        File f = new File(fileWithColdestTemperature());
        FileResource fr = new FileResource(f);
        
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        
        System.out.println("Coldest day was in file " + f.getName());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        
        for (CSVRecord record : parser)
        {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }
    
    private CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord minHum = null;
        
        for (CSVRecord currentRow : parser) 
        {
            minHum = minHumidityOfTwo(currentRow, minHum);
        }
        return minHum;
    }
    
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
            
        System.out.println("Lowest humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
    }
    
    private CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord minHum = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            
            minHum = minHumidityOfTwo(currentRow, minHum);
        }
        return minHum;
    }
    
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("lowest humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
    }

    private CSVRecord coldestInManyFiles() 
    {
        CSVRecord minTemp = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            
            minTemp = minTemperatureOfTwo(currentRow, minTemp);
        }
        return minTemp;
    }
    
    public void testColdestInManyFiles() 
    {
        CSVRecord smallest = coldestInManyFiles();
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    } 
    
    private double avgTemperatureInFile(CSVParser parser)
    {
        double sum = 0;
        double counter = 0;
        
        for (CSVRecord record : parser)
        {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            sum += currTemp;
            counter++;
        }
        
        return sum / counter;
    }
    
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        double avgTemperature = avgTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemperature);
    }
    
    private double avgTemperatureWithHighHumidityInFile(CSVParser parser, int val)
    {
        double sum = 0;
        double counter = 0;
        
        for (CSVRecord record : parser)
        {
            double currHum = Double.parseDouble(record.get("Humidity"));
            
            if (currHum >= val)
            {
                sum += currHum;
                counter++;
            }
        }
        if (counter == 0.0)
            return 0.0;
        else 
            return sum / counter;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        double avgTemperature = avgTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        
        if (avgTemperature == 0.0)
            System.out.println("No temperatures with that humidity.");
        else
            System.out.println("Average temperature with high humidity is " + avgTemperature);
    }
}
