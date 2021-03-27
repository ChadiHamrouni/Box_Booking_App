import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class box {
	public String name;
	public Date startTime;
	public Date endTime;
	public boolean states;
	public String filename;

	public box (String name){
		this.name = name;
		this.filename = this.name + ".txt";
		try{
			File boxFile = new File("C:\\Users\\shady\\IdeaProjects\\Booking App\\updated_project\\files" + filename);
			if (boxFile.createNewFile()) {
				System.out.println("File created: " + boxFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");}
	}
	/*public String checkAvailable() {
		return " ";
	}*/

	public void bookBox(Date startDate, Date endDate,user u){
		this.states = true;
		this.startTime = startDate;
		this.endTime = endDate;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String time = dateFormat.format(dateFormat);
		String msg = startTime +":"+ endTime +":"+ u.email+":"+u.id+System.lineSeparator();
		try {
			FileWriter myWriter = new FileWriter(this.filename, true);
			myWriter.write(msg);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");}
	}
}
