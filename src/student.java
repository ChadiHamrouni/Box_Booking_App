import java.io.*;
import java.text.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class student{
	
	public static boolean checkLoginUser (int id,String email) throws InvalidEmailException, InvalidIdException, IOException,
			NumberFormatException{
		
		if (id/(int)Math.pow(10,6) == 0) throw new InvalidIdException("This ID is invalid!");
		/* Checking if the email is a valid SMU email */
		String[] keywords = {"a","@medtech.tn","@msb.tn"};
		boolean found = false;
		for (String s: keywords)
			if (email.contains(s)){
				found = true;
				break;
			}
		if (!found) throw new InvalidEmailException("This email is invalid!");
		return true;
	}
	
		public static void checkDateFormat(String d) throws DateException{
			DateException e = new DateException("Invalid date!");
			if ("12:34 pm".length() != d.length()) throw e;
			if (d.indexOf(":") != 2) throw e;
			char[] h = {d.charAt(0),d.charAt(1)};
			if((parseInt(String.copyValueOf(h))>12) || (parseInt(String.copyValueOf(h))<1)) throw e;
			char[] m = {d.charAt(3),d.charAt(4)};
			if((parseInt(String.copyValueOf(m))>59) || (parseInt(String.copyValueOf(m))<0)) throw e;
			char[] pm = {d.charAt(5),d.charAt(6),d.charAt(7)};
			if(!(String.copyValueOf(pm).equals(" am")) && (String.copyValueOf(pm).equals(" pm"))) throw e;
		}
		public static void checkDate(Date start, Date end) throws IOException, ParseException, TimeException {
			DateFormat sdf = new SimpleDateFormat("hh:mm a");
			if (start.before(sdf.parse("8:00 am")) || start.after(sdf.parse("07:00 pm")) ||
					(end.before(start)) || end.after(sdf.parse("07:00 pm"))) throw
					new TimeException("Boxes are only available from 8am to 7pm!");
		}
		
	public static void main(String[] args) throws ParseException{
			try {
				Scanner input = new Scanner(System.in);
				//File creation
				File bookingLogs = new File("C:\\Users\\shady\\IdeaProjects\\Booking App\\updated_project\\files\\Logs.txt");
				if (bookingLogs.createNewFile()) {
					System.out.println("Files created: " + bookingLogs.getName() + bookingLogs.getName());
				} else {
					System.out.println("Files already exist.");
				}

				//Login check
				System.out.println("Enter your ID: ");
				String str = input.nextLine();
				str = str.replaceAll(" ", "");
				System.out.println("Enter your E-mail: ");
				String email = input.nextLine();
				email = email.replaceAll(" ", "");
				
				//Admin
				if (str.compareTo("admin") == 0 && (email.compareTo("admin@smu.tn") == 0)) {
					admin admin = new admin();
					admin.exec();
				}
				
				else {
					//User
					int id = parseInt(str);
					if (checkLoginUser(id, email)) {
						user newuser = new user(id, email);
						System.out.println("Welcome student!");
					}

					//Time handling
					DateFormat sdf = new SimpleDateFormat("hh:mm a");
					System.out.println("Enter the starting time (hh:mm AM/PM): ");
					String startTime = input.nextLine();
					//checkDateFormat(startTime);
					Date start = sdf.parse(startTime);
					System.out.println("Enter the ending time (hh:mm AM/PM): ");
					String endTime = input.nextLine();
					//checkDateFormat(endTime);
					Date end = sdf.parse(endTime);
					checkDate(start, end);

				      	// startboxTime and endboxTime are variables that hold the text line start time and end time!
						Date startboxTime = null;
						Date endboxTime = null;
						String data = "";
						String strrSTART= "";
						String strrEND = "";
						Scanner fScn = new Scanner(new File("C:\\Users\\shady\\IdeaProjects\\Booking App\\updated_project\\files\\Logs.txt"));
						FileWriter writer = new FileWriter("C:\\Users\\shady\\IdeaProjects\\Booking App\\updated_project\\files\\Logs.txt", true);
				      
					while(fScn.hasNextLine()){		
						data = fScn.nextLine();
									
									if(data.contains("|"))   
									
									{	strrSTART =data.substring(0,data.indexOf('|'));
										startboxTime = sdf.parse(strrSTART); 
										strrEND = data.substring(data.indexOf('|')+1,data.indexOf('.'));
										endboxTime = sdf.parse(strrEND);					
									}
									if ((start.equals(startboxTime)&& (end.equals(endboxTime)))) { System.out.println("f"); break; }

						// 	if (fScn.nextLine().isBlank())
						if ((start.before(startboxTime) && end.before(startboxTime)) || (start.after(endboxTime) && (end.after(endboxTime))))
                          //  System.out.println("im here");
						//	if (fScn.nextLine().equals(""))
                            if (fScn.hasNextLine()) {
                                fScn.nextLine();
                                writer.write(startTime + "|" + endTime + "." + System.lineSeparator());

                            }

							}
					  writer.close();
				}
				
				}catch(ParseException | InvalidEmailException | InvalidIdException |
						NumberFormatException | TimeException  e){
					System.err.println(e.getMessage());
				}
				catch(IOException e){
					System.err.println("Access error: " + e.getMessage());
				}

		 Date dNow = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzzz");
		 System.out.println("Current Date: " + ft.format(dNow)); 

	}
}