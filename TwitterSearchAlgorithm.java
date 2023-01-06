import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class Search{
	//Max number of results we would allow.
	int num = 150;
	ResultSet result;
	Integer length = new Integer(11);
	int length2 = 0
	ResultSet topResult;
	int count = 0;
	int len = 25;
	String[] array = new String[num];
	
	public static void main(String[] args)
	{
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		for(int a = 0; a < len; a++)
		{
			System.out.println(array[a]);
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
		Statement database = connection.createStatement();
		result = database.execute("SELECT COUNT(*) FROM Twitter");
		}catch(SQLException e)
		{
		}
		try{
			while(result.next())
			{
			length = result.getInt(1);
			}
		}catch(Exception e)
		{
		}
		length2 = length;
		for(long b = 0; b < length2; b++)
		{
			try{
				array2.add(database.executeQuery("SELECT Username FROM Twitter WHERE AccountNum = " + String.valueOf(b)));
			}catch(SQLException e)
			{
			}
		}
		int charsInARow = 0;
		ArrayList<Integer> array3 = new ArrayList<Integer>();
		int x = 0;
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
				charsInARow = charsInARow + 3;
			}
			//Give the entries with the same charsInARow higher values for HashMap key reference
			while(x < array4.size())
            {
                if(array4.get(x).equals(charsInARow))
                {
                    charsInARow++;
                }
                x++;
            }
            x = 0;
			//1 or 2 charsInARow and above is suitable for a small sample size. If we have
			//millions or billions of account names, we could require 3, 4, or 5 charsInARow
			//to count the account name in our list of results because repeat values
			//for charsInARow in our HashMap will automatically default to the last 
			//assigned input to the hashmap, and will possibly print those values multiple times 
			if(charsInARow > 2)
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
		int m = 0;
		for(int i = 0; i < num; i++)
		{	
			if(array3.get(i) > 0)
			{
				topResult = database.executeQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + map.get(array3.get(i)));
				while(topResult.next())
				{
					array[count] = String.valueOf(topResult.absolute(count));
					count++;
				}
				while(topResult.next())
				{
					topResult.absolute(m);
					topResult.deleteRow();
					m++;
				}
				m = 0;
			}
		}
	}
}