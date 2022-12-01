import java.util.*;


//This version will skip important account matches because map.get() returns the 
//the last value match found. If we have two accounts that have the same number of 
//matching characters, the map.get() will go the last entry input into the Hashmap.
//This will essentially output the same account multiple outputs of the same account,
//While skipping the others with the same number of matching characters. We would have
//to test this to see if this is a problem when we are performing a search. If we
//are lucky, this will yeild good search results. If not, then we will skip too many
//accounts yeild good search results.
public class Search{
	
	String input = "";
	//We assume 100 for now because we cannot return 2 billion accounts
	int num = 100;
	String[] topResult = new String[num];
	
	public static void main(String[] args)
	{
		//Using Scanner just to simulate what i would write in
		//simple java program
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		int lastPosition = -1;
		int thisPosition = 0;
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
		ArrayList<String> array2 = new ArrayList<String>();
		for(long b = 0; b < length; b++)
		{
			//here we assume that each entry has a unique account number, starting at 0 or 1, going to infinity.
			array2.add(database.rawQuery("SELECT Username FROM Twitter WHERE AccountNum = " + b));
		}

		int charsInARow = 0;
		String charsString = "";
		//int totalInARow = 0;
		ArrayList<Integer> array3 = new ArrayList<Integer>();
		//ArrayList<String> array4 = new ArrayList<String>();
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
		//Create our hashmap with an integer and a String
		Map<int, String> map = new HashMap<int, String>();	
		for(long w = 0; w < length; w++)
		{
			map.put(w, String.valueOf(array3.get(w)));
		}

		for(int p = 0; p < num; p++;)
		{
			//This allows for duplicates to be copied to topResult[], which will
			//essentially will skip accounts that have the same numbers of matching
			//characters, which will leave out important matches, so this will not work correctly.
			topResult[p] = database.rawQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + String.valueOf(map.get(String.valueOf(array3.get(p)))));
		}
	}
}