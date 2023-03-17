package models;

public class Segment {
	private int segmentId;
	private int port;
	private int PC_from_ID;
	private int PC_to_ID;
	private String message;
	
	
	public Segment(int segmentId, int PC_from_ID, int PC_to_ID, String message) {
		this.segmentId = segmentId;
		this.port = 993;
		this.PC_from_ID = PC_from_ID;
		this.PC_to_ID = PC_to_ID;
		this.message = message;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getSegmentId() {
		return segmentId;
	}
	
	public int getPC_from_ID() {
		return PC_from_ID;
	}
	
	public int getPC_to_ID() {
		return PC_to_ID;
	}


	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setData(String message) {
		this.message = message;
	}
	
	
	
	
}
