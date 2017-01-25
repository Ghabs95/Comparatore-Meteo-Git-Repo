package core.internet;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import static java.util.stream.Collectors.*;

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
			System.out.println("+++++WARNING+++++\nCaricamento file di esempio per il link: "+ url);
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
	
	private String loadStringExample(String url) {
		String site = "";
		String path = "";
		if (url.contains("lamma")) {
			path = getPath("lamma.xml");
		} else if (url.contains("meteoam")) {
			path = getPath("meteoAM.html");
		} else if (url.contains("3bmeteo")) {
			path = getPath("3bMeteo.html");
		} else {
			return "Sito non disponibile!";
		}
		site = loadFromFile(path);
		return site;
	}
	
	private String getPath(String meteo){
		URL urlP = getClass().getResource(meteo);
		String path = "";
		try {
			path = URLDecoder.decode(urlP.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Example file NOT FOUND!!");
		}
		return path;
	}
	
	private String loadFromFile(String path){
		String site = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			site = br.lines().collect(joining());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			System.out.println("WARNING! Caricamento immagine di esempio da File!");
			img = getSampleImage();
		}
		return img;
	}
	
	private BufferedImage getSampleImage(){
		File sample = new File(getPath("meteoSat.png"));
		BufferedImage img = null;
		try {
			img = ImageIO.read(sample);
		} catch (IOException e) {
			System.out.println("Immagine Esempio Non Trovata!");
			e.printStackTrace();
		}
		return img;
	}

}
