import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main
{

	public static void main(String[] args)
	{
		List<File> files = loadFiles();
		
		for(File file : files)
		{
			try(BufferedReader csvReader = new BufferedReader(new FileReader(file)))
			{
				Session session = new Session(mapFileNameToDate(file.getName()));
				
				
				String firstline = csvReader.readLine();
				String[] players = firstline.split(",");
				Player player1 = new Player(players[1]);
				Player player2 = new Player(players[2]);
				Player player3 = new Player(players[3]);
				Player player4 = new Player(players[4]);
				
				
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}

		}
		
		

	}
	
	private static List<File> loadFiles()
	{
		final File folder = new File("src/data");
		return Arrays.asList(folder.listFiles()); 
	}
	
	private static LocalDate mapFileNameToDate(String fileName)
	{
		String withoutTail = fileName.substring(0, fileName.length() -4);
		String[] splitted = withoutTail.split("_");
		return LocalDate.of(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
	}

}
