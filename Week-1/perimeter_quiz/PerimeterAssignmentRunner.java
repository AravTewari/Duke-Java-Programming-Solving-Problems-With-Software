import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner 
{
    public double getPerimeter (Shape s) 
    {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) 
        {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) 
    {
        int numPoints = 0;
        
        for(Point p : s.getPoints())
        {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) 
    {
        double totalLength = getPerimeter(s);
        int numSides = getNumPoints(s);
        
        return totalLength/numSides;
    }
    
    public double getLargestSide(Shape s) 
    {
        double largestSide = 0;
        Point prevPoint = s.getLastPoint();
        
        for(Point currPoint : s.getPoints())
        {
            double currDist = prevPoint.distance(currPoint);
            if (currDist > largestSide)
            {
                largestSide = currDist;
            }
            prevPoint = currPoint;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) 
    {
        double largestX = Integer.MIN_VALUE;
        
        for(Point currPoint : s.getPoints())
        {
            double currX = currPoint.getX();
            if (currX > largestX)
            {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() 
    {
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0;
        
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            
            if (currPeri > largestPeri)
            {
                largestPeri = currPeri;
            }
           
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() 
    {
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0;
        String fileWithLargestPeri = "";
        
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            
            if(currPeri > largestPeri)
            {
                fileWithLargestPeri = f.getName();
            }
        }
        return fileWithLargestPeri;
    }

    public void testPerimeter () 
    {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average length = " + averageLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() 
    {
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPeri);
    }

    public void testFileWithLargestPerimeter() 
    {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle()
    {
        Shape triangle = new Shape();
        
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        
        for (Point p : triangle.getPoints())
        {
            System.out.println(p);
        }
        
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() 
    {
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        {
            System.out.println(f);
        }
    }

    public static void main (String[] args) 
    {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
