import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;


//Bard wrote the SQL statement SELECT AccountName FROM Twitter WHERE AccountName LIKE CONCAT('%', " + input + ", '%') ORDER BY COUNT(AccountName) DESC;
//That basically took all of my mechanics of the character matching, the size of the loops, and the HashMap reference that pulls the AccountNum
//All i did was plug it into a java program

/**
 It was created by saying to Bard: 
 "can you write a character-matching 
 MySQL database search algorithm where
 the top matching account names with the 
 highest amount of matching characters in a
 row to a user input are filtered to the top
 out the end output? I want the columns of the
 database to be AccountNumber, AccountName, DateRegistered,
 MaleOrFemale. This database would be like that you would find
 in a social media app"
 **/
public class TwitterSearchAlgorithmBard{

	public static String[] array = new String[75];
	
	
	public static void main(String[] args)
	{
		TwitterSearchAlgorithmBard search = new TwitterSearchAlgorithmBard();
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search.search(input);
		System.out.println(Arrays.toString(search.getResults());
		
	}

	public static void search(String input)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Twitter","root","root");    
		Statement database = connection.createStatement();
		
		int m = 1;
		int n = 0;
		String[] array7 = new String[num];
		ResultSet topResult;
		
		topResult = database.executeQuery("SELECT AccountName FROM Twitter WHERE AccountName LIKE CONCAT('%', " + input + ", '%') ORDER BY COUNT(AccountName) DESC");
		while(topResult.next())
		{
		array7[n] = topResult.getString(m);
		n++;
		m++;
		}
		if(array7[0].equals(null))
		{
			System.out.println("There are no matches");
		}
		setResults(array7);
		}catch(SQLException e)
		{
		}
	}
	
	public static void setResults(String[] array7)
	{
		array = array7;
	}
	
	public static String[] getResults()
	{
		return array;
	}

}