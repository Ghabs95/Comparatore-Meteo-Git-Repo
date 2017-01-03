package core.internet;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class WebHandler {
	private static WebHandler instance;
	
	private WebHandler() {}
	
	public static WebHandler getInstance(){
		return instance==null ? new WebHandler() : instance;
	}
	
	public String getSite(String url){
		String site = "";
		try {
			URLConnection connection = setUpConnection(new URL(url));
	        connection.connect();
			site = readSite(connection);				
		} catch (Exception e) {
			site = loadStringExample(url);
		}	
		return site;
	}
	
	private URLConnection setUpConnection(URL myUrl) throws IOException{
        System.setProperty("http.agent", "");
        URLConnection myURLConnection = myUrl.openConnection();
        myURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        return myURLConnection;
	}
	
	private String readSite(URLConnection connection) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line, siteStr = "";
		while ((line = in.readLine()) != null){
			siteStr += line;
		}
		in.close();	
		return siteStr;
	}
	
	private String loadStringExample(String url){
		String site = "";
		String path = "src\\core\\internet\\";
		if(url.contains("lamma")){
			path += "lamma.xml";
		}else if(url.contains("meteoam")){
			path += "meteoAM.html"; 
		}else if(url.contains("3bmeteo")){
			path += "3bMeteo.html";
		}else{
			return "Sito non disponibile!";
		}
		site = loadFromFile(path);
		return site;
	}
	
	private String loadFromFile(String path){
		BufferedReader br = null;
		FileReader fr = null;
		String site = "";
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine())!= null){
				site += line;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null){	br.close(); }
				if(fr != null){ fr.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return site;		
	}
	
	/* Restituisce una BufferedImage, trasferibile tramite la classe ImageIO */
	public BufferedImage getPhoto(String url){
		BufferedImage img = null;
		try{
			URL myUrl = new URL(url);
			img = ImageIO.read(myUrl);
		} catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}

}
