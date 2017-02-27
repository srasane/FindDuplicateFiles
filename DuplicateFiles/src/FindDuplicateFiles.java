import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindDuplicateFiles {

	Map <String, List<String>> fileMap;
	public FindDuplicateFiles(){
		fileMap = new HashMap<String, List<String>>();
	}
	
	public int numOfFilesChecked(){
		int size = 0;
		for(String key : fileMap.keySet()){
			size += fileMap.get(key).size();
		}
		return size;
	}

	
	/**
	 * if no path is found default path is set to root directory
	 * @param path
	 * @return
	 */
	public String fileValidation(String path){
		if((new File(path).exists())){
			return path;
		}
		else if(path == null || path.isEmpty()){
			System.out.println("Setting default path" + File.listRoots()[0].getAbsolutePath());
			return File.listRoots()[0].getAbsolutePath();
		}
		else{
			System.out.println("Enter valid folder path");
			return null;
		}
	}
	
	public List<List<String>> searchForDuplicateFilesInCurrentDir(String directoryStr){
		File directory = new File(directoryStr);
		List<List<String>>dupfiles = new ArrayList<List<String>>();
		groupFiles(directory);
		for(String key : fileMap.keySet()){
			if(fileMap.get(key).size() > 1){
				dupfiles.add(fileMap.get(key));
			}
		}
		return dupfiles;
	}
	
	/***
	 * This method groups files together  in a map based on the hash function calculated by MD5 algorithm.
	 * Duplicate files will has same hash values and will be stored under same key.
	 * @param directory
	 */
	private void groupFiles(File directory){
		if(directory.isDirectory()){
			for(File file : directory.listFiles() ){
				if(!file.isDirectory()){

					String hash = getHash(file);
					List<String> fileSet = fileMap.get(hash);
					if(fileSet == null){
						fileSet = new ArrayList<String>();
						fileMap.put(hash, fileSet);
					}
					fileSet.add(file.getAbsolutePath());
				}  
				//this is a directory search for files inside the directory
				else{
					groupFiles(file);
				}
			}
		}
	}
	
	/**
	 * MD5 hashing 
	 * @param file
	 * @return
	 */
	private String getHash(File file){
		String hash = "";
		FileInputStream ipStream = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e2) {
			System.err.println("Trouble in instantiating MD5!!!" + e2);
		}

		try {
			ipStream = new FileInputStream(file);
			byte data[] = new byte[(int) file.length()];
			// Reads input stream till file size
			ipStream.read(data);
			hash = new BigInteger(1, md.digest(data)).toString(16);
		}
		catch (FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND!!!" + file.getAbsolutePath() + e1); 
			
		} catch (IOException e) {
			System.err.println("FILE NOT FOUND!!!" + e); 
		}finally{
			try {
				ipStream.close();
			} catch (IOException e) {
				System.err.println("Error while closing file!" +file.getAbsolutePath() + e);
			}
		}
		return hash;
	}
	
}
