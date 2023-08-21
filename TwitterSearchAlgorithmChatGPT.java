import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//ChatGPT wrote the SQL statement and assembled it into a java program upon request when Bard
//Didn't want to

/**
 It was created by saying to ChatGPT: 
 "can you write a character-matching 
 MySQL database search algorithm where
 the top matching account names with the 
 highest amount of matching characters in a
 row to a user input are filtered to the top
 out the end output? I want the columns of the
 database to be AccountNumber, AccountName, DateRegistered,
 MaleOrFemale. This database would be like that you would find
 in a social media app"

 "Ok good now plug this into a java program"

 This was the result
 **/
public class CharacterMatchingSearch {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "your_username";
        String password = "your_password";

        try {

        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT AccountName "
                         + "CASE "
                         + "    WHEN AccountName LIKE ? THEN 1 "
                         + "    WHEN AccountName LIKE CONCAT('%', ?) THEN 2 "
                         + "    WHEN AccountName LIKE CONCAT('%', ?, '%') THEN 3 "
                         + "    ELSE 0 "
                         + "END AS MatchLevel "
                         + "FROM Twitter "
                         + "WHERE AccountName LIKE CONCAT('%', ?, '%') "
                         + "ORDER BY MatchLevel DESC, CHAR_LENGTH(AccountName) "
                         + "LIMIT 10";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + input + "%");
            preparedStatement.setString(2, "u%" + input + "%");
            preparedStatement.setString(3, "us%" + input + "%");
            preparedStatement.setString(4, input);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String accountName = resultSet.getString("AccountName");
                int matchLevel = resultSet.getInt("MatchLevel");

                System.out.println("AccountNumber: " + accountNumber);
                System.out.println("AccountName: " + accountName);
                System.out.println("DateRegistered: " + dateRegistered);
                System.out.println("MaleOrFemale: " + maleOrFemale);
                System.out.println("MatchLevel: " + matchLevel);
                System.out.println("--------------");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
