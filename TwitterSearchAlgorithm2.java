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
		//Sort array3 into descending order because its an Integer arraylist.
		Collections.sort(array3, Collections.reverseOrder());
		//Create our hashmap with Collection as the string value with an integer and a String
		//that will allow for duplicate map values.
		//Normal hashmap does not take duplicate key values in map.get()
		//Collection as value will help us access different positions with duplicate keys
		//because our matching algorithm on lines 53 - 61 can return duplicate values.
		//(https://www.baeldung.com/java-map-duplicate-keys)
		Map<int, List<String>> map = new HashMap<>();
		int numOfDuplicatesWanted = 0;
		long a = 1;
		long c = 0;
		long d = 0;
		for(long b = 0; b < length; b++)
		{
			//here we just call a String.valueOf() to skip assigning int array3 to an String array3
			//multiple positions with the same key get assigned this way?
			//otherwise we will have to just 
			a = b + 1;
			c = b;
			numOfDuplicatesWanted = 1;
			for( ; ; )
			{
				if(array3.get(c) == array3.get(a))
				{
					numOfDuplicatesWanted = numOfDuplicatesWanted + 1;
				}
				if(array3.get(c) != array3.get(a))
				{
					break;
				}
				a++;
			}
			List<String> list = new ArrayList<>();
			map.put(array3.get(b), list);
			for(int i = 0; i < numOfDuplicatesWanted; i++)
			{
				map.get(array3.get(c)).add(String.valueOf(b));
				if(i < numOfDuplicatesWanted - 1)
				{
					b++;
				}
			}
		}
		//Change p to String to get our String values from array 4 to retrieve the int values in
		//our hashmap, which will return integers, or our "account number"
		String sqlString = "";
		for(int b = 0; b < num; b++)
		{	
			//Using assertThat() will allow us to access each
			//next position of the map int value (account number)
			//that we are trying with the same String key
			a = b + 1;
			c = b;
			numOfDuplicatesWanted = 1;
			for( ; ; )
			{
				if(array3.get(c) == array3.get(a))
				{
					numOfDuplicatesWanted = numOfDuplicatesWanted + 1;
				}
				if(array3.get(c) != array3.get(a))
				{
					break;
				}
				a++;
			}
			for(int y = 0; y < numOfDuplicatesWanted; y++)
			{
				sqlString = assertThat(map.get(array3.get(c)).get(y)).isEqualTo(String.valueOf(b));
				result = database.executeQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + sqlString);
				topResult[b] = result.getString("Username");
				if(y < numOfDuplicatesWanted - 1)
				{
					b++;
				}
			}	
		}
	}
}