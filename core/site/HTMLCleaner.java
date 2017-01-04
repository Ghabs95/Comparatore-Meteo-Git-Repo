package core.site;

import java.util.ArrayList;

public class HTMLCleaner {

	//Toglie tutto cio' che e' contenuto tra 2 tag (uno di apertura e uno di chiusura)
	public String cutTag(String dirtyStr, String startRegex, String endRegex){
		String[] dirtyList = dirtyStr.split(startRegex);
		
		ArrayList<String> cleanList = new ArrayList<>();
		cleanList.add(dirtyList[0]);			//il primo elemento e' pulito
		
		for(int i=1; i<dirtyList.length; i++){ //salto il primo elemento (perche' non inizia col tag cercato)
			String[] tmp = dirtyList[i].split(endRegex);
			if(tmp.length > 1){
				cleanList.add(tmp[1]);
			}
		}
		
		String clean = listToString(cleanList);
		return clean;
	}
	
	private String listToString(ArrayList<String> a){
		String tmp = "";
		for(String s : a){
			tmp += s;
		}
		return tmp;
	}
	
	public String removeBlocks(String dirtyStr, String block){
		String[] tmp = dirtyStr.split(block);
		String sum ="";
		for(String s : tmp){
			sum += s;
		}
		return sum;
	}

}
