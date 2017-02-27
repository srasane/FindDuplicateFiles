import java.sql.Timestamp;
import java.util.List;

public class Driver {
	public static void main(String [] args){
		Timestamp start  , end;

		FindDuplicateFiles manageDir = new FindDuplicateFiles();

		for(int i = 0; i < args.length; i++){
			String filePath = args[i];
			if(manageDir.fileValidation(filePath) !=null ){
				System.out.println("\n-----TEST " + (i+1) +  " FOR DUPLICATE FILES------");
				start  = new Timestamp(System.currentTimeMillis());
				List<List<String>> dupList = manageDir.searchForDuplicateFilesInCurrentDir(filePath);
				end = new Timestamp(System.currentTimeMillis());
				if(dupList.isEmpty()){
					System.out.println("Empty Folder. No Duplicates!!!");
				}
				System.out.println("Total time taken: " + (end.getTime() - start.getTime()) + " milliseconds");
				System.out.println("Files Scanned: " + manageDir.numOfFilesChecked());
				for(List<String> dupFiles : dupList){
					System.out.println("--------------------------------------------------------------------------------------------------");
					for(String absPath : dupFiles){
						System.out.println(absPath);
					}
					System.out.println("--------------------------------------------------------------------------------------------------");
				}
			}
		}
	}
}





