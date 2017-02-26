import java.io.File;
import java.util.List;
import java.util.Map;

public class Driver {
	public static void main(String [] args){
		FindDuplicateFiles manageDir = new FindDuplicateFiles();
		List<List<String>> dupList = manageDir.searchForDuplicateFilesInCurrentDir(new File("C:/projects/FindDuplicateFiles/dir1"));
		for(List<String> dupFiles : dupList){
			System.out.println("--------------------------------------------------------------------------------------------------");
			for(String absPath : dupFiles){
				System.out.println(absPath);
			}
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
	}
}


