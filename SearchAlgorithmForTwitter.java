import java.util.*;

public class Search{
	
	String input = "";
	//We assume 100 for now because we cannot return 2 billion accounts
	String[] topResult = new String[100];
	Hashmap
	
	public static void main(String[] args)
	{
		//Using Scanner just to simulate what i would write in
		//simple java program
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		
		for(int y = 0; y < topResult.length; y++)
		{
			
		}
	}

	public void search(String input)
	{
		//This will be our 
		char[] array1 = input.toCharArray();
		int a = 0;
		//this will be the number of existing accounts.
		//The SQL COUNT() function returns the number of rows in a table satisfying the criteria specified 
		//in the WHERE clause. It sets the number of rows or non NULL column values. 
		//COUNT() returns 0 if there were no matching rows.
		//int length = SQLCOUNT();
		SQLDatabase database = new SQLDatabase();
		String[] array2 = new String[length];
		
		for(int b = 0; b < length; b++)
		{
			//here we assume that each entry has a unique account number, starting at 0 or 1, going to infinity.
			array2[b] = database.rawQuery("SELECT Username FROM Twitter WHERE AccountNum = " + b);
		}
		int charsInARow = 0;
		int[] array3 = new array3[length];
		for(int i = 0; i < length; i++)
		{
			char[] array2Token = array2[i].toCharArray();
			for(int z = 0; z < array2Token[i].length; z++)
			{
				if(array1[z] == array2Token[z])
				{
					charsInARow = charsInARow + 1;
				}
				if(array1[z] != array2Token[z])
				{
					array3[a] = charsInARow;
					charsInARow = 0;
				}
			}
			a++;
		}
		Map<int, int> map = new HashMap<int, int>();	
		
		for(int w = 0; w < array3.length; w++)
		{
			map.put(array3[w], w);
		}
		for(int x = 0; x < array3.length; x++)
        {
            for(int y = 0; y < array3.length; y++)
            {
				//descending sort
                if(map[x] > map[y])
                {
                    space = array3[x];
                    array3[x] = array3[y];
                    array3[y] = space;
                }
            }
        }
		for(int p = 0; p < 100; p++;)
		{
			topResult[p] = database.rawQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + map.get(array3[p]);
		}
		
	}
	public String returnResult()
	{
		return topResult;
	}
	
}