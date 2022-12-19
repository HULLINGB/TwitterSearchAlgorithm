import java.util.*;
import java.sql.*;


public class Search{
	
	int num = 150;
	String[] topResult = new String[num];
	
	public static void main(String[] args)
	{
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search(input);
		for(int i = 0; i < topResult.length; i++)
		{
			System.out.println(topResult[i]);
		}
	}

	public void search(String input)
	{
		char[] array1 = input.toCharArray();
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Twitter","root","root");    
		long length = SQLCOUNT();
		Statement database = connection.createStatement();
		ArrayList<String> array2 = new ArrayList<String>();
		for(long b = 0; b < length; b++)
		{
			array2.add(database.executeQuery("SELECT Username FROM Twitter WHERE AccountNum = " + b));
		}
		int p = 0;
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
				}
			}
			array3.add(charsInARow);
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
				topResult[b] = database.executeQuery("SELECT Username FROM Twitter WHERE AccountNumber = " + map.get(array3.get(b)));
		}
	}
}