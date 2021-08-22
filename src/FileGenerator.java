import java.io.*;
import java.io.IOException;


/**
 * Generates files of tuples to be converted into buildings.
 *
 * @author Bryony Church
 */
public class FileGenerator {
    private String type;
    File file;

    public FileGenerator(String type){
        this.type = type;
        file = generateFile();
    }

    public File generateFile(){
        String data = "";
        try {
            if (type.equals("one building")) {
                data = "4 8 3";
                file = new File("onebuilding.txt");
            }
            else if (type.equals("twenty")){
                data = createTwenty();
                file = new File("twenty.txt");
            }
            else if (type.equals("thousand")){
                data = createThousand();
                file = new File("thousand.txt");
            }
            else if (type.equals("one side")){
                data = createOneSide();
                file = new File("oneside.txt");
            }
            else if (type.equals("wide")){
                data = createWide();
                file = new File("wide.txt");
            }
            else if (type.equals("twenty thousand")){
                data = createTwentyThousand();
                file = new File ("twentythousand.txt");
            }
            else if (type.equals("sparse")){
                data = createSparse();
                file = new File("sparse.txt");
            }
            else if (type.equals("one thousand in order")){
                data = createOneThousandInOrder();
                file = new File("onethousandinorder.txt");
            }
            else if (type.equals("comparison")){
                data = createComparison();
                file = new File("comparison.txt");
            }
            Writer bw = new FileWriter(file);
            bw.write(data);
            bw.close();
            return file;
        } catch (IOException e) { System.out.println("File not created");}
        return null;
    }

    /**
     * Creates data for 20 random buildings.
     * @return A string to be read into a file.
     */
    public String createTwenty(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++){
            int left = (int) (Math.random() *100);
            int right = left + ((int) (Math.random()*5));
            int height = (int) (Math.random() * 50);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 1000 random buildings.
     * @return A string to be read into a file.
     */
    public String createThousand(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++){
            int left = (int) (Math.random() *1200);
            int right = left + ((int) (Math.random() * 10));
            int height = (int) (Math.random() * 100);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 3001 buildings, with 3000 on one side of the x axis and
     * 1 and the other side.
     * @return A string to be read into a file.
     */
    public String createOneSide(){
        String leftSide = createThousand();
        leftSide += "2200 2210 15 ";
        leftSide += createThousand();
        leftSide += createThousand();
        return leftSide;
    }

    /**
     * Creates data for 20 wide buildings.
     * @return A string to be read into a file.
     */
    public String createWide(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++){
            int left = (int) (Math.random() *100);
            int right = left + ((int) (Math.random()*100));
            int height = (int) (Math.random() * 50);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 20,000 random buildings.
     * @return A string to be read into a file.
     */
    public String createTwentyThousand(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++){
            int left = (int) (Math.random() *12000);
            int right = left + ((int) (Math.random()*100));
            int height = (int) (Math.random() * 1000);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 20 buildings spread sparsely on the x axis.
     * @return A string to be read into a file.
     */
    public String createSparse(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++){
            int left = (int) (Math.random() *(i*50));
            int right = left + ((int) (Math.random()*40));
            int height = (int) (Math.random() * 100);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 1000 buildings in order on the x axis.
     * @return A string to be read into a file.
     */
    public String createOneThousandInOrder(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++){
            int left = (int) (Math.random() *(i*12));
            int right = left + ((int) (Math.random()*100));
            int height = (int) (Math.random() * 1000);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    /**
     * Creates data for 8 randomly spaced buildings. To be used as comparison for best
     * and worst case data.
     * @return A string to be read into a file.
     */
    public String createComparison(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            int left = (int) (Math.random() *100);
            int right = left + ((int) (Math.random()*100));
            int height = (int) (Math.random() * 100);
            String building = (left + " " + right + " " + height + " ");
            sb.append(building);
        }
        return sb.toString();
    }

    public File getFile(){
        return file;
    }


}
