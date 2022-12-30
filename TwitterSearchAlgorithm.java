import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class Search{
	//Max number of results we would allow.
	int num = 150;
	String[] topResult = new String[num];
	int count = 0;
	public static void main(String[] args)
	{
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		for(int i = 0; i < count; i++)
		{
			System.out.println(topResult[i]);
		}
	}

	public void search(String input)
	{
		char[] array1 = input.toCharArray();
		ArrayList<String> array2 = new ArrayList<String>();
		try{
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Twitter","root","root");    
		long length = SQLCOUNT();
		Statement database = connection.createStatement();
		}catch(SQLException e)
		{
		}
		for(long b = 0; b < length; b++)
		{
			try{
				array2.add(database.executeQuery("SELECT Username FROM Twitter WHERE AccountNum = " + String.valueOf(b)));
			}catch(SQLException e)
			{
			}
		}
		int charsInARow = 0;
		ArrayList<Integer> array3 = new ArrayList<Integer>();
		for(long i = 0; i < length; i++)
		{
			char[] array2Token = array2.get(i).toCharArray();
			for(int z = 0; z < array2Token.length; z++)
			{
				if(z == array1.length)
				{
					break;
				}
				
				if(array1[z] == array2Token[z])
				{
					charsInARow = charsInARow + 1;
				}else{
					break;
				}
			}
			if(charsInARow == array2token.length)
			{
				charsInARow = charsInARow + 2;
			}
			//1 or 2 charsInARow and above is suitable for a small sample size.
			//if we have millions or billions of account names, we could require
			//3, 4, or 5 charsInARow to count the account name in our list of results
			//because repeat values for charsInARow in our HashMap will automatically default
			//to the last assigned input to the hashmap, and will possibly print those values 
			//multiple times in a row.
			if(charsInARow > 3)
			{
				array3.add(charsInARow);
			}else
            {
                array3.add(0);
            }
			charsInARow = 0;
		}
        HashMap<Integer, String> map = new HashMap<>();
		for(long b = 0; b < length; b++)
		{
			map.put(array3.get(b), String.valueOf(b));
		}
		Collections.sort(array3, Collections.reverseOrder()); 
		for(int b = a; b < num; b++)
		{	
			if(array3.get(i) > 0)
			{
			topResult[count] = database.executeQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + map.get(array3.get(b)));
			count++;
			}
		}
	}
}