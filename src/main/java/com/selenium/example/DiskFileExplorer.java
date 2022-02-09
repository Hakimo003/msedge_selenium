package com.selenium.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiskFileExplorer {

    private String initialpath = "";
    private Boolean recursivePath = false;
    public int filecount = 0;
    public int dircount = 0;
    private String ligne;
    private InputStreamReader fichier;
    private BufferedReader tampon;
    boolean t;
    
    public DiskFileExplorer(String path, Boolean subFolder) {
        super();
        this.initialpath = path;
        this.recursivePath = subFolder;
    }
 
    public void list() {
        this.listDirectory(this.initialpath);
    }
    
    public void deleteFile(String path) {
        File f= new File(path);           //file to be delete  
        if(f.delete())                      //returns Boolean value  
        {  
        	System.out.println(f.getName() + " deleted");   //getting and printing the file name  
        }  
        else  
        {  
        	System.out.println("failed");  
        }  
    }
    
    public void copyFile(String source, String destination) {
        FileSystem system = FileSystems.getDefault();
        Path original = system.getPath(source);
        Path target = system.getPath(destination);
        
        // Copy original to target location.
        try {
			Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File copied!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void createTempFile(File file) throws IOException {
    	fichier = new InputStreamReader(new FileInputStream(file.getAbsolutePath()));
    	tampon  = new BufferedReader(fichier);
        FileWriter myWriter = new FileWriter(file.getParentFile()+"\\temp.java");
        while((ligne = tampon.readLine()) != null) {
            if (ligne.contains("login(\"") && !ligne.contains("packArr[1]") ){
            	myWriter.write("\t\t\tString packName = new Object(){}.getClass().getPackage().getName();");   
            	myWriter.write("\n");
            	myWriter.write("\t\t\tString[] packArr = packName.split(\".\");");
            	myWriter.write("\n");
            	String user="";
                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(ligne);
                while(m.find()) {
                	user=m.group(1);    
                }
                if (user.contains(","))
                	myWriter.write("\t\t\tlogin("+user.split(",")[0]+", packArr[1]);");
                else
                	myWriter.write("\t\t\tlogin("+user+", packArr[1]);");
            }else {
            	myWriter.write(ligne);
            }
            myWriter.write("\n");
            
        }
        
        myWriter.close();
        tampon.close();
        System.out.println("Successfully wrote to the file.");
    }
	private void listDirectory(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == true) {
                    System.out.println("Dossier: " + files[i].getAbsolutePath());
                    this.dircount++;
                } else {
                    System.out.println("  Fichier: " + files[i].getName());
                    try {
						fichier = new InputStreamReader(new FileInputStream(files[i].getAbsolutePath()));
	                    tampon  = new BufferedReader(fichier);
	                    
                        t = false;
	                    while((ligne = tampon.readLine()) != null) {	                    	
	                        if (ligne.contains("login(\"") ){
	                        	t = true ;
	                        	break;
	                        }
	                    }
	                    tampon.close();
	                    if ( t == true && !files[i].getAbsolutePath().contains("temp.java")) {
	                    	
	                    	createTempFile(files[i]);

		                	copyFile(files[i].getParentFile()+"\\temp.java", files[i].getAbsolutePath());

		                    deleteFile(files[i].getParentFile()+"\\temp.java");
	                    }
	                    
	                    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
					} 

                    this.filecount++;
                }
                if (files[i].isDirectory() == true && this.recursivePath == true) {
                    this.listDirectory(files[i].getAbsolutePath());
                }
            }
        }
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  /*String pathToExplore = "src\\test\\java";
		  DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
		  Long start = System.currentTimeMillis();
		  diskFileExplorer.list();
		  System.out.println("----------");
		  System.out.println("Analyse de " + pathToExplore + " en " + (System.currentTimeMillis() - start) + " mses");
		  System.out.println(diskFileExplorer.dircount + " dossiers");
		  System.out.println(diskFileExplorer.filecount + " fichiers");*/
		String packName;
		String[] packArr;
		packName = new Object(){}.getClass().getPackage().getName();
		System.out.println(packName);
		packArr = packName.split("\\.");
		System.out.println(packArr);
		System.out.println(packArr[1]);
	}

}
