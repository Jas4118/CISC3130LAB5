package cisc3130Lab5;

public class Song {
	private Song right;
	private Song left;
	private String songTitle;
	private int streamCount;
	private String artistName;

	public Song(String songTitle, String artistName, int streamCount) {
		this.songTitle = songTitle;
		this.artistName = artistName;
		this.streamCount = streamCount;
	}

	public Song() {
		
	}

	public Song getRight() {
		return right;
	}

	public void setRight(Song right) {
		this.right = right;
	}

	public Song getLeft() {
		return left;
	}

	public void setLeft(Song left) {
		this.left = left;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public int getStreamCount() {
		return streamCount;
	}

	public void setStreamCount(int streamCount) {
		this.streamCount = streamCount;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}
