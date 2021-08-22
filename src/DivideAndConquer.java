import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Represents a Divide and Conquer object.
 *
 * @author Bryony Church
 */
public class DivideAndConquer {
    private String data;
    private List<Point2D> coOrds = new ArrayList<Point2D>();
    private int mergeCounter = 0;
    private int numBuildings = 0;
    private int sortCounter = 0;

    /**
     * Creates a Divide and Conquer object.
     * @param
     */
    public DivideAndConquer(String fileName){
        this.data = fileName;
    }

    /**
     * Recursively divides the building list in half.
     * @param buildings List of buildings in the skyline.
     * @return  A list of points that define the skyline.
     */
    public List<Point2D> processData(List<Building> buildings){

        int size = buildings.size();
        List<Point2D> coOrds = new ArrayList<Point2D>();
        //If not buildings, return an empty list
        if (size == 0)  return coOrds;
        //If one building, return the coordinates of the one building
        if (size == 1) {
            coOrds.add(new Point2D.Double(buildings.get(0).getLeft(), buildings.get(0).getHeight()));
            coOrds.add(new Point2D.Double(buildings.get(0).getRight(), 0));
            return coOrds;
        }

        //Otherwise, recursively divide the list into two sub problems
        int half = buildings.size()/2;
        List<Point2D> leftHalf = processData(new ArrayList<Building>(buildings.subList(0, half)));
        List<Point2D> rightHalf = processData(new ArrayList<Building>(buildings.subList(half, size)));
        mergeCounter++;
        //Merge the sub problems together
        return mergeHalves(leftHalf, rightHalf);
    }

    /**
     * Combines the divided data.
     * @param leftHalf  First half of coordinates to merge.
     * @param rightHalf Second half of coordinates to merge.
     * @return The merged list of coordinates.
     */
    public List<Point2D> mergeHalves(List<Point2D> leftHalf, List<Point2D> rightHalf){
        List<Point2D> sky = new ArrayList<Point2D>();
        int leftCounter = 0;
        int rightCounter = 0;
        double xPos = 0.0;
        double height = 0.0;
        double leftHeight = 0.0;
        double rightHeight = 0.0;
        double currentHeight = 0.0;
        //Compare the coordinates in each list, storing the information of the one furthest to the left
        while((leftCounter < leftHalf.size()) && (rightCounter < rightHalf.size())) {
            sortCounter++;
            if (leftHalf.get(leftCounter).getX() < rightHalf.get(rightCounter).getX()) {
                xPos = leftHalf.get(leftCounter).getX();
                leftHeight = leftHalf.get(leftCounter).getY();
                leftCounter++;
            } else {
                xPos = rightHalf.get(rightCounter).getX();
                rightHeight = rightHalf.get(rightCounter).getY();
                rightCounter++;
            }
            //Store the larger height to the right
            height = Math.max(leftHeight, rightHeight);
            //If the height has changed, add the coordinate to the list and update the current height
            if (currentHeight != height){
                updateCoordinates(sky, xPos, height);
                currentHeight = height;
            }
        }
        //If either list has leftover coordinates, add them in if they represent a change in height
        List<Point2D> extras = new ArrayList<Point2D>();
        if (leftCounter < leftHalf.size())  extras = leftHalf.subList(leftCounter, leftHalf.size());
        else                                extras = rightHalf.subList(rightCounter, rightHalf.size());
        for (Point2D p : extras) {
            if (currentHeight != p.getY()) {
                updateCoordinates(sky, p.getX(), p.getY());
                currentHeight = p.getY();
            }
        }
        return sky;
    }

    /**
     * Adds coordinates to the list or updates their height values.
     * @param coOrds    List of coordinates that make up the skyline.
     * @param xPos      X position of the current coordinate.
     * @param height    Y position of the current coordinate.
     */
    public void updateCoordinates(List<Point2D> coOrds, double xPos, double height){
        //If it's a vertical move, remove the last co-ordinate so that it can be replaced
        //by one at the new height
        if (!coOrds.isEmpty() && coOrds.get(coOrds.size()-1).getX() == xPos){
            coOrds.remove(coOrds.size()-1);
        }
        coOrds.add(new Point2D.Double(xPos, height));
    }

    /**
     * Read the building data from a file and create building tuples to add to the building list.
     */
    public void readData(String fileName){
        List<Building> buildings = new ArrayList<Building>();
        InputStream is = this.getClass().getResourceAsStream(fileName);
        Scanner sc = new Scanner(is);
        while (sc.hasNext()){
            Building current = new Building(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
            buildings.add(current);
        }
        numBuildings = buildings.size();
        coOrds = processData(buildings);
    }

    /**
     * Displays the first ten co-ordinates in the skyline for testing and the data from the counters that count
     * the steps.
     */
    public void displayStats(){
        System.out.println("File name = " + data);
        System.out.println("First ten co-ordinates of the skyline:");
        int display = coOrds.size() > 10 ? 10 : coOrds.size();
        for (int i = 0; i < display ; i++){
            System.out.print("( " + coOrds.get(i).getX() + ", " + coOrds.get(i).getY() + " ) ");
     }
        System.out.println("");
        System.out.println("Number of buildings = " + numBuildings);
        System.out.println("Number of co-ordinates = " + coOrds.size());
        System.out.println("Number of sort steps counted = " + sortCounter);
        System.out.println("Number of merge steps counted = " + mergeCounter);
        System.out.println("____________________________________________________________________________________");
    }


    public static void main(String[] args) {
        //Commented code creates the files. Not needed once the files are created.
//        FileGenerator f = new FileGenerator("twenty thousand");
//        File file = f.getFile();
//        DivideAndConquer d = new DivideAndConquer(file);

        String[] files = {"testdata.txt", "onebuilding.txt", "twenty.txt", "thousand.txt",
                "oneside.txt", "wide.txt", "twentythousand.txt", "sparse.txt",
                "onethousandinorder.txt", "worstcase.txt", "bestcase.txt", "comparison.txt"};
        for (String s : files){
            DivideAndConquer d = new DivideAndConquer(s);
            d.readData(s);
            d.displayStats();
        }

    }
}
