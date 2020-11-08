package cisc3130Lab5;
//Jason Li 
//CISC3130 TY9 Katherine Chuang
//Due November 8th 2020
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab5 {
	public static Song insert(Song root, Song qtr) { // insert method for the node
		if(root == null) {
			root = qtr;
			return root;
		}
		if(qtr.getSongTitle().compareTo(root.getSongTitle())<=0 )
			root.setLeft(insert(root.getLeft(),qtr));
		else if (qtr.getSongTitle().compareTo(root.getSongTitle())>0)
			root.setRight(insert(root.getRight(),qtr));
		
		return root;
				
	}
	
	public static void subSet(String start, String end, Song root,PrintWriter outFile){ //Subset method, uses comparison of ASCII values to organize alphabetically
		 if (root == null) 
	            return; 
	        subSet(start, end, root.getLeft(),outFile); //Checks the left
	        if(start.compareTo(root.getSongTitle())<=0 && end.compareTo(root.getSongTitle())>=0) {
	        	outFile.println(root.getSongTitle() + " ,Stream Count: " +root.getStreamCount()); 
	        }
	        subSet(start, end, root.getRight(),outFile);//Checks the right
	    }
	
	public static void printTree(Song root,PrintWriter outFile) { 
        if (root == null) {
            return; 
        }
        	printTree(root.getLeft(), outFile);   //Prints left nodes until there's no more
        	outFile.println(root.getSongTitle()+ " ,Stream Count: " +root.getStreamCount());
        	printTree(root.getRight(), outFile); //Prints right nodes until there's no more
   	} 
	
	public static void main(String[]args) throws IOException {
		FileReader fr = new FileReader("regional-global-daily-latest.csv"); //Have to use BufferedReader because it stopped reading when I used Scanner
        	BufferedReader br = new BufferedReader(fr);
        	File topSongs= new File(("TopStreamingArtists.txt")); //Creating top song list and the subset list
        	File subsets= new File(("subsets.txt")); 
        	PrintWriter topSongList = new PrintWriter(topSongs);
        	PrintWriter subSetList = new PrintWriter(subsets);
        	br.readLine();
        	br.readLine();
        	Song root=null;
        
        for (String line = br.readLine(); line != null; line = br.readLine()) {
        	String temp = line.split(",")[1];// Song name is the first split on comma
        	String songName = temp.replaceAll("^\"+|\"+$", ""); 
        	String temp2=line.split(",")[2]; //Artist name is suppose to be the second split
        	for(int i=0; i<temp2.length();i++) { // Loop to check for parenthesis so artist name is next
        		char c=')';
        		if(temp2.charAt(i)==c) {
        			String tempArtist=line.split(",")[3];
        			tempArtist = tempArtist.replaceAll("^\"+|\"+$", "");
        			tempArtist =tempArtist.replaceAll("\\s+","");
        			temp2 = tempArtist; 
        			break;
        		}
        	}
        	
        	String artist = temp2.replaceAll("^\"+|\"+$", "");//Sets artist name
        	String temp3=line.split(",")[3];
        	int length = temp3.length();
        	for (int i = 0; i < length; i++) { // loops to check if letters are in the split if not it becomes next part for parsing
                if ((Character.isLetter(temp3.charAt(i)) == true)) {
                   temp3=line.split(",")[4];
                   if ((Character.isLetter(temp3.charAt(i)) == true)) {
                       temp3=line.split(",")[5];
                       break;
                    }
                   break;
                }
            }
        	int streamCount= Integer.parseInt(temp3); //Number of streams played
        	Song song=new Song(songName,artist,streamCount);
        	if(root == null) { // My insert method didn't work because of pass by value so I placed it in the main method
    			root = song;		
    		}
        	else if(song.getSongTitle().compareTo(root.getSongTitle())<=0 )
    			root.setLeft(insert(root.getLeft(),song));
    		else if (song.getSongTitle().compareTo(root.getSongTitle())>0)
    			root.setRight(insert(root.getRight(),song));
        	
        }
        //Uppercase values are considered alphabetically before lowercase value 
        printTree(root,topSongList);
        subSet("Party Girl","Riptide",root,subSetList);
        br.close();
        subSetList.close();
        topSongList.close();
	}
	
}
