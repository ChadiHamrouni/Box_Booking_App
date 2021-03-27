import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class admin {
    public int numberOfBoxes;
    public box[] boxes = new box[50];
    public int setNbrOfBoxes(){
        System.out.println("How many boxes are there in your institution?");
        Scanner input = new Scanner (System.in);
        String str  = input.nextLine();
        return parseInt(str);
    }
    public void createBoxes(int n){
        System.out.println("Please enter the names of the boxes.");
        Scanner input = new Scanner (System.in);
        for(int i=0;i<n;i++){
            boxes[i]=new box(input.nextLine());
        }
    }
    public void exec(){
        createBoxes(setNbrOfBoxes());
    }
}