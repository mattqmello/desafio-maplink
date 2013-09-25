package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * FileManager manipulates java I/O implementations to
 * write and read files with specified parameters in its methods.
 * @author Matheus Q. de Mello
 * @since Terça 24/9/2013
 */
public class FileManager {
	
	/**
	 * Read a file and put its lines into a StringBuffer object.
	 * @param file_location The file's path for the read process.
	 * @return A StringBuffer containing the file's content.
	 */
	public static StringBuffer readFile(String file_location) {
		try {
			FileReader fileReader = new FileReader(file_location);
			
			System.out.printf("\nLendo \"%s\"... ", file_location);
			
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			StringBuffer strBuffer = new StringBuffer();
			String line = new String();
			
			while((line = buffReader.readLine()) != null){
				strBuffer.append(line);
			}
			
			fileReader.close();
			buffReader.close();
			
			System.out.println("Sucesso!");
			
			return strBuffer;
		} catch (FileNotFoundException e) {
			System.err.println("Erro: arquivo nao encontrado!\n");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("Erro ao tentar ler arquivo:\n");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Write into a file the String passed as parameter.
	 * @param content String that will be the file content.
	 */
	public static void writeFile(String content) {
		try {
			String fileName = "src/JsonArray_saida_"+Calendar.getInstance().getTime().toString()+".json";
			PrintWriter printWriter = new PrintWriter(fileName, "UTF-8");
			
			System.out.printf("\nGravando \"%s\"... ",fileName);
			
			printWriter.println(content);
			printWriter.close();
			
			System.out.println("Sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao gravar arquivo:\n");
			e.printStackTrace();
		}
	}
}
