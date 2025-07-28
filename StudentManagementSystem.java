import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentManagement {
	
	private static final String url="jdbc:mysql://Localhost:3306/jdbc";
	
	private static final String username="root";
	
	private static final String password="Sejal@123";
	
	private static Connection connection;
	
	private static Statement statement;
	private static PreparedStatement preparedStatement;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	  connection	=	DriverManager.getConnection(url, username, password);
	  statement = connection.createStatement();
	  
	  Scanner scanner = new Scanner(System.in);
	  while(true) {
		  System.out.println("******* WELCOME TO STUDENT MANAGEMENT SYSTEM ********");
		  System.out.println("Press 1 to add the student");
		  System.out.println("Press 2 to update the student");
		  System.out.println("Press 3 to delete the student");
		  System.out.println("Press 4 to get all the student");
		  System.out.println("Press 5 to exit");
		  
		  String choice = scanner.nextLine();
		  if(choice.equalsIgnoreCase("1")){
			  // add the student details 
			  System.out.println("Enter Student Name : ");
			  String name = scanner.nextLine();
			  System.out.println("Enter Student City : ");
			  String city = scanner.nextLine();
			  System.out.println("Enter Student gender :");
			  String gender = scanner.nextLine();
			  System.out.println("Enter Student Course : ");
			  String course = scanner.nextLine();
			  System.out.println("Enter Student Email ID :");
			  String email = scanner.nextLine();
			  
			  boolean res = statement.execute("SELECT * from student where email = '" + email + "'");
		
		if(res) {
			ResultSet resultset = statement.getResultSet();
	
		if(resultset.next()) {
			System.out.println("Student already exits");
			
		}
		else {
	preparedStatement =		connection.prepareStatement("insert into student(name,city,gender,course,email) values(?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, city);
			preparedStatement.setString(3, gender);
			preparedStatement.setString(4, course);
			preparedStatement.setString(5, email);
		int i =	preparedStatement.executeUpdate();
		if(i>0) {
			System.out.println("records inserted");
		}
		else {
			System.out.println("records not inserted");
		}
		}
		  }
		}
		  else if(choice.equalsIgnoreCase("2")) {
				// update the record
				System.out.println("Enter the email to be updated :");
				String usertoupdate = scanner.nextLine();

				boolean res = statement.execute("SELECT * from student where email = '" + usertoupdate + "'");
				if(res) {
					ResultSet resultset = statement.getResultSet();
					if(resultset.next()) {
						// Perform update
						System.out.println("Enter updated Student Name : ");
						String name = scanner.nextLine();
						System.out.println("Enter updated Student City : ");
						String city = scanner.nextLine();
						System.out.println("Enter updated Student gender :");
						String gender = scanner.nextLine();
						System.out.println("Enter updated Student Course : ");
						String course = scanner.nextLine();
						System.out.println("Enter updated Student Email ID :");
						String email = scanner.nextLine();

						preparedStatement = connection.prepareStatement(
							"UPDATE student SET name = ?, city = ?, gender = ?, course = ?, email = ? WHERE email = ?");
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, city);
						preparedStatement.setString(3, gender);
						preparedStatement.setString(4, course);
						preparedStatement.setString(5, email);
						preparedStatement.setString(6, usertoupdate);

						int i = preparedStatement.executeUpdate();
						if(i > 0) {
							System.out.println("Record updated.");
						} else {
							System.out.println("Record not updated.");
						}
					} else {
						System.out.println("student not found.");
					}
				}
			}

		  
        
		 
        	  
			
				
			 if(choice.equalsIgnoreCase("3")) {
				 System.out.println("Enter the email to be deleted :");
					String usertodelete = scanner.nextLine();

					boolean res = statement.execute("SELECT * from student where email = '" + usertodelete + "'");
					if(res) {
						ResultSet resultset = statement.getResultSet();
						if(resultset.next()) {
							preparedStatement = connection.prepareStatement(
									"delete from student  WHERE email = ?");
								preparedStatement.setString(1, usertodelete);
								
								int i = preparedStatement.executeUpdate();
								if(i > 0) {
									System.out.println("Record deleted.");
								} else {
									System.out.println("Record not deleted.");
								}
							} else {
								System.out.println("student not found.");
							}
						}
					

			  
		  }
		  else if(choice.equalsIgnoreCase("4")) {
			  preparedStatement = connection.prepareStatement("SELECT * FROM student");
			  ResultSet res = preparedStatement.executeQuery();
			  if(res.next()){
                  System.out.println("ID : "+res.getInt("id"));
                  System.out.println("NAME : "+res.getString("name"));
                  System.out.println("CITY : "+res.getString("city"));
                  System.out.println("GENDER : "+res.getString("gender"));
                  System.out.println("COURSE: "+res.getString("Course"));
                  System.out.println("EMAIL: "+res.getString("email"));
                  
                  System.out.println("-------------");
              }
              else{
                  System.out.println("no records found");
              }
			  
		  }
		  else if(choice.equalsIgnoreCase("5")) {
			  System.out.println("Thanks for visiting . . .");
			  break;
		  }
		  else {
			  System.out.println("Choose correct option");
		  }
		  
	
	  }
		  
	 
		}
		
	   catch(Exception e) {
		   e.printStackTrace();
	   }

	}
}


	




