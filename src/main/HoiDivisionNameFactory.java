package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class HoiDivisionNameFactory
{
	
	public static final String LEGIONS_NAME_FILE_PATH = "./names.txt";

	public static void main(String[] args)
	{
		HoiDivisionNameFactory factory = new HoiDivisionNameFactory( );
		//for( String name: factory.createLegionNamesToGivenNumber( 100 ) )
		for(String name: factory.createLegionNamesToCapacityFromFile())
			System.out.println( name );
	}
	
	private ArrayList<String> filePathToContainedNamesList(String pathToFile) throws IOException
	{
		String fileContent = Files.readString(Paths.get(pathToFile));
		return new ArrayList<String> (
			Arrays
				.asList( fileContent.split( "\n" ) )
				.stream( )
				.map( ( s ) -> { return s.trim( ); } )
				.toList( )
		);
	}
	
	public ArrayList<String> createLegionNamesToGivenNumber(int number)
	{
		ArrayList<String> finalNames = new ArrayList<>();
		for(int i = 0; i < number; i++)
		{
			finalNames.add(String.format("Legio %s", arabicToRomanNumerals( i + 1 )));
		}
		
		return finalNames;
	}
	
	public ArrayList<String> createLegionNamesToCapacityFromFile()
	{
		try
		{
			ArrayList<String> names = filePathToContainedNamesList(LEGIONS_NAME_FILE_PATH);
			
			ArrayList<String> finalNames = new ArrayList<>();
			
			for(int i = 0; i < names.size(); i++)
			{
				finalNames.add(String.format("Legio %s \"%s\"", arabicToRomanNumerals( i + 1 ), names.get( i ) ));
			}
			
			return finalNames;
		} 
		catch (IOException e)
		{
			throw new FactoryException(e);
		}
	}

	public String arabicToRomanNumerals(int arabic)
	{
		return "I".repeat(arabic)
	            .replace("IIIII", "V")
	            .replace("IIII", "IV")
	            .replace("VV", "X")
	            .replace("VIV", "IX")
	            .replace("XXXXX", "L")
	            .replace("XXXX", "XL")
	            .replace("LL", "C")
	            .replace("LXL", "XC");
	}
	
}
