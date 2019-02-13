package commandline;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test_log{
	//String for store the log
    static String log_string = "";
	// Write a file and read a file Specify file name and path Prepare the path and file name
	public static final String FILE_NAME = "Test_log.txt";
	// The file is placed in the root directory of the project.
	public static final String FILE_PATH = "";
	// The test_log
	public File test_logFile;
	public Test_log() {
		
		test_logFile = creatFile(FILE_PATH, FILE_NAME);
		// if exists overwrite 
		test_logFile.delete();
	}
	// write data into the file
	
		protected String writeFile(String content) {
			
			log_string += content;
			
			return log_string;
			
		}
	
		protected void writeFileToTestLog() {

			try {
				/* write in TXT
				 * in the new FileWriter(file, true)
				 * if true, then bytes will be written to the end of the file rather than the beginning
				 * it will not cover the previously written content 
				 *  */
				FileWriter fWriter = new FileWriter(test_logFile, true);
				BufferedWriter out = new BufferedWriter(fWriter);
				out.write(log_string);
				/* flush(): Flushes the stream.  
				 * If the stream has saved any characters from the various write() methods in a buffer,
				 *  write them immediately to their intended destination.*/
				out.flush();
				out.close();
			} catch (Exception e) {
				System.err.println("write err!");
				e.printStackTrace();
			}finally {
				if (test_logFile == null) {
					System.err.println("pleace create a file first");
				}
			}
		}
		
		// Create file method
		protected static File creatFile(String filePath, String fileName) {
			File folder = new File(filePath);
			// File path does not exist
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}

			// Create if the file does not exist
			File file = new File(filePath + fileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return file;
		}

}
