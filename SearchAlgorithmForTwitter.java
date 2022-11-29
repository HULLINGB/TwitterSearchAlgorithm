import java.util.*;

public class Search{
	
	String input = "";
	//We assume 100 for now because we cannot return 2 billion accounts
	String[] topResult = new String[100];
	
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
		long a = 0;
		//this will be the number of existing accounts.
		//The SQL COUNT() function returns the number of rows in a table satisfying the criteria specified 
		//in the WHERE clause. It sets the number of rows or non NULL column values. 
		//COUNT() returns 0 if there were no matching rows.
		//long datatype goes up to 9,223,372,036,854,775,807
		long length = SQLCOUNT();
		SQLDatabase database = new SQLDatabase();
		//ArrayList will go up to 2,147,483,647 positions so we will use array list, and it can be sorted easily.
		ArrayList<String> array = new ArrayList<String>(length);
			
		for(long b = 0; b < length; b++)
		{
			//here we assume that each entry has a unique account number, starting at 0 or 1, going to infinity.
			array.add(database.rawQuery("SELECT Username FROM Twitter WHERE AccountNum = " + b));
		}
		int charsInARow = 0;
		String charsString = "";
		int totalInARow = 0;
		ArrayList<String> array2 = new ArrayList<String>(length);
		ArrayList<Integer> array3 = new ArrayList<Integer>(length);
		ArrayList<String> array4 = new ArrayList<String>(length);
		for(long i = 0; i < length; i++)
		{
			char[] array2Token = array2.get(i).toCharArray();
			for(int z = 0; z < array2Token.length; z++)
			{
				if(array1[z] == array2Token[z])
				{
					charsInARow = charsInARow + 1;
				}
				if(array1[z] != array2Token[z])
				{
					//totalInARow = totalInARow + charsInARow;
					array3.add(charsInARow);
					charsInARow = 0;
				}
			}
		}
		Map<int, String> map = new HashMap<int, String>();	
		
		for(long w = 0; w < array3.size(); w++)
		{
			map.put(w, array3.get(w));
		}
		int space = 0;
		//Sort array3 into descending order because its an Integer arraylist.
		Collections.sort(array3, Collections.reverseOrder());
		//Then assing array3 to array4 as String values so we can use the value
		//in our map.get() method to retrieve the integer values from highest number
		//of characters in a row
		for(int i = 0; i < array3.size(); i++)
		{
			//convert array3 to String and copy to array4
			array4.add(String.valueOf(array3.get(i)));
		}
		//Change p to String to get our String values from array 4 to retrieve the int values in
		//our hashmap, which will return integers, or our "account number"
		for(int p = 0; p < 100; p++;)
		{
			topResult[p] = database.rawQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + map.get(array4.get(p));
		}	
	}
}