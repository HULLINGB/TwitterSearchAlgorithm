import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;



public class Search3{

	//Num of max results
	int num = 25;
	public static String[] array = new String[75];
	String[] array7 = new String[num];
	Connection connection;
	Statement database;
	PreparedStatement preparedStatement;
	PreparedStatement preparedStatement2;
	ResultSet result;
	ResultSet resultNames;
	ResultSet resultSet;
	ResultSet topResult;
	String names = "";
	public static void main(String[] args)
	{
		Search3 search = new Search3();
		Scanner myObj = new Scanner(System.in);
		String input = myObj.nextLine();
		search.search(input);
		System.out.println(Arrays.toString(search.getResults());
	}

	public static void search(String input)
	{
		char[] array1 = input.toCharArray();
		ArrayList<String> array2 = new ArrayList<String>();
		try{
		Class.forName("com.mysql.jdbc.Driver");  
		connection = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/Twitter","root","root");    
		database = connection.createStatement();
		result = database.executeQuery("SELECT COUNT(*) FROM Twitter");
		}catch(SQLException e)
		{
		}
		Integer length = 0;
		try{
			while(result.next())
			{
			length = result.getLong(1);
			}
		}catch(Exception e)
		{
		}
		Long length2 = new Long(length);
		long c = 0;
		for(long b = 0; b < length2; b++)
		{
			try{
			names = "SELECT Username FROM Twitter WHERE AccountNum = ?";
			preparedStatement = database.prepareStatement(names);
			preparedStatement.setString(1, String.valueOf(b);
			resultNames = preparedStatement.executeQuery();			
			while(resultNames.next())
			{
			array2.add(resultNames.getString(c));
			c++;
			}
			c = 1;
			while(resultNames.next())
			{
			resultNames.absolute(c);
			resultNames.deleteRow();
			c++;
			}
			c = 1;
			}catch(SQLException e)
			{
			}
		}
		int charsInARow = 0;
		ArrayList<Integer> array3 = new ArrayList<Integer>();
		long x = 0;
		int o = 0;
        int g = 0;
        int adder = 100;
        int adder2 = 1000;
        int subtracter = 1;
		for(long i = 0; i < length2; i++)
		{
			char[] array2Token = array2.get(i).toCharArray();
			//No need for this but we can still keep it just
			//to make extra sure nothing skews the results.
            if(array1.length == 1 && array1[0] == array2Token[0])
            {
                charsInARow = charsInARow + adder2;
            }
            for (int i = 0; i < array1.length; i++) {
                for (int v = i; v < array2Token.length; v++) {
                    if (array1[i] == array2Token[v]) {
                        o = v;
                        for (int z = 0; z < array1.length; z++) {
                        	subtracter = subtracter * o;
                            adder = adder - subtracter;
                            try{
                            if (array1[z] == array2Token[o]) {
                                g++;
                                //charsInARow++;
                                if(g >= array1.length)
                                {
                                    charsInARow = charsInARow + adder;
                                }
                            } else {
                                g = 0;
                                break;
                            }
                            o++;
                            }catch(Exception e)
                            {
                            }
                            subtracter = 1;
                            adder = 100;
                        }
                    }
                }
            }
			//Add a checker so we do not have duplicate values so HashMap get()
			//statement works properly.
			if(charsInARow > 0)
			{
				while(x < array3.size())
				{
					if(array3.get(x).equals(charsInARow))
					{
						charsInARow++;
					}
					x++;
				}
				x = 0;	
			}
			//Adding an extra checker solved my problem
            //of "alexandradaddario" appearing above "arianagrande"
            //When i type "ari" for my smaller dataset of 100 celebrity names. 
            //It will slow down the search just a little
            //So we can comment it out for a larger dataset if needed.
            /**
            if(charsInARow > 0)
            {
              while(x < array3.size())
                {
                    if(array3.get(x).equals(charsInARow))
                    {
                        charsInARow++;
                    }
                    x++;
                }
                x = 0;
            }
            **/
			if(array1.length > 1)
			{
			    if(charsInARow > 1)
			    {
			    array3.add(charsInARow);
			    }else{
			    array3.add(0);
			    }   
			}
			if(array1.length < 2)
			{
			    array3.add(charsInARow);
			}
			charsInARow = 0;
		}
        HashMap<Integer, String> map = new HashMap<>();
		for(long b = 0; b < length2; b++)
		{
			map.put(array3.get(b), String.valueOf(b));
		}
		Collections.sort(array3, Collections.reverseOrder()); 
		int m = 1;
		int n = 0;
		for(int i = 0; i < num; i++)
		{	
			try{
				if(array3.get(i) > 0)
				{
				names = "SELECT Username FROM Twitter WHERE AccountNum = ?";
				preparedStatement2 = connection.prepareStatement(names);
				preparedStatement2.setString(1, map.get(array3.get(i)));
				topResult = preparedStatement2.executeQuery();

				while(topResult.next())
					{
						array7[n] = topResult.getString(m);
						n++;
						m++;
					}
					m = 1;
					while(topResult.next())
					{
						topResult.absolute(m);
						topResult.deleteRow();
						m++;
					}
					m = 1;
				}
			}catch(Exception e)
			{

			}
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