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
public class Search{

	public static String[] array = new String[75];
	
	
	public static void main(String[] args)
	{
		Search search = new Search();
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
		}catch(SQLException e)
		{
		}
		int m = 1;
		int n = 0;
		String[] array7 = new String[num];
		ResultSet topResult;
		
		try{
		if(array3.get(0) > 0)
		{
		topResult = database.executeQuery("SELECT AccountName FROM Twitter WHERE AccountName LIKE CONCAT('%', " + input + ", '%') ORDER BY COUNT(AccountName) DESC");
		while(topResult.next())
		{
		array7[n] = topResult.getString(m);
		n++;
		m++;
		}
		m = 1;
		
		}
		if(array7[0].equals(null))
		{
			System.out.println("There are no matches");
		}
		setResults(array7);
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