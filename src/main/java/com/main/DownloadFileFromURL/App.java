package com.main.DownloadFileFromURL;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String url = "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf";
        String filename = "JavaBook";
        downloadFile(url,filename);
    }
    public static void downloadFile(String fileUrl, String fileName) {
		String dirName = CommonConstant.DIRACTORY_NAME;
		String fname = fileName + CommonConstant.SAVE_WITH_EXTENSION;
		String fileNameInLocalPC = dirName + "\\" + fname;
		
		try {
			saveFileFromUrlWithCommonsIO(fileNameInLocalPC, fileUrl);
			System.out.println("Finished Downloading in LocalMachine");
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void saveFileFromUrlWithCommonsIO(String fileName, String fileUrl)
			throws MalformedURLException, IOException {
			FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
			
			File file = new File(fileName);
			double size = getFileSizeKiloBytes(file);
			System.out.println("File Size :"+size);
	}
    public static double getFileSizeKiloBytes(File file) {
		return (double) (file.length()/1024 );
	}
	

	// Using Java IO
	public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(fileUrl).openStream());
			fout = new FileOutputStream(fileName);
			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null)
				in.close();
			if (fout != null)
				fout.close();
		}
	}
    


}
