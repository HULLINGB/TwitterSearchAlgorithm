import java.util.*;
import java.sql.*;

public class Search{
	
	ResultSet result;
	//We assume 100 for now because we cannot return 2 billion accounts
	int num = 150;
	String[] topResult = new String[num];
	
	public static void main(String[] args)
	{
		//Using Scanner just to simulate what i would write in
		//simple java program
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		//show the top results return from our query statement in our application.
		//result by account number, assuming the unique number of the list of all
		//accounts starts at account 0, going to 1, 2, 3, 4......
		for(int i = 0; i < topResult.length; i++)
		{
 			System.out.println(topResult[i]);
		}
	}

	public void search(String input)
	{
		//This will be our input entered converted to a char array
		char[] array1 = input.toCharArray();
		//Access MySQL database at a URL
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Twitter","root","root");    
		//This will be the number of existing accounts.
		//The SQL COUNT() function returns the number of rows in a table satisfying the criteria specified 
		//in the WHERE clause. It sets the number of rows or non NULL column values. 
		//COUNT() returns 0 if there were no matching rows.
		//long datatype goes up to 9,223,372,036,854,775,807
		long length = SQLCOUNT();
		Statement database = connection.createStatement();
		//ArrayList will go up to 2,147,483,647 positions so we will use array list, and it can be sorted easily.
		ArrayList<String> array2 = new ArrayList<String>();
		for(long b = 0; b < length; b++)
		{
			//here we assume that each entry has a unique account number, starting at 0 or 1, going to infinity.
			array2.add(database.executeQuery("SELECT Username FROM Twitter WHERE AccountNum = " + b));
		}
		int charsInARow = 0;
		ArrayList<Integer> array3 = new ArrayList<Integer>();
		for(long i = 0; i < length; i++)
		{
			char[] array2Token = array2.get(i).toCharArray();
			for(int z = 0; z < array2Token.length; z++)
			{
				if(array1[z] == array2Token[z])
				{
					charsInARow = charsInARow + 1;
				}
			}
			array3.add(charsInARow);
			charsInARow = 0;
		}
		int[] array4 = new int[num];
		//Sort array3 in descending order
		//We need to do a custom algorithm to put in the account number.
		//Then we assign the results to topResult[] and output
		long z = 1;
		//10 top entries
		int length2 = 10;
		for(long x = 0; x < num; x++)
        {
			for(long y = 0; y < length; y++)
			{
				if(array3.get(y) < array3.get(z))
				{
						//track where the highest value for charsInARow
						//and add it to array4
						array4[x] = z;
				}
				z++;
			}	
			z = 0;
		}		
		for(int b = length2 - 1; b < num; b++)
		{	
			result = database.executeQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + String.valueOf(array4[b]));
			while(result.next())
			{
				topResult[b] = result.getString("Username");
			}				
		}	
	}
}