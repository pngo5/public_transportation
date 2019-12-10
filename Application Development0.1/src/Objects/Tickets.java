package Objects;

public class Tickets {
	
	private String tUserName;
	private int tBusID;
	private String tDepartCity;
	private String tArrivalCity;
	private String tDepartTime;
	private String tArrivalTime;
	private int passengerCount;
	
	

	
	public Tickets(int tBusID, String tDepartCity, String tArrivalCity, String tDepartTime,
			String tArrivalTime, int passengerCount) {
		super();
		
		this.tBusID = tBusID;
		this.tDepartCity = tDepartCity;
		this.tArrivalCity = tArrivalCity;
		this.tDepartTime = tDepartTime;
		this.tArrivalTime = tArrivalTime;
		this.passengerCount = passengerCount;
	}
	
	public String gettUserName() {
		return tUserName;
	}
	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
	}
	public int gettBusID() {
		return tBusID;
	}
	public void settBusID(int tBusID) {
		this.tBusID = tBusID;
	}
	public String gettDepartCity() {
		return tDepartCity;
	}
	public void settDepartCity(String tDepartCity) {
		this.tDepartCity = tDepartCity;
	}
	public String gettArrivalCity() {
		return tArrivalCity;
	}
	public void settArrivalCity(String tArrivalCity) {
		this.tArrivalCity = tArrivalCity;
	}
	public String gettDepartTime() {
		return tDepartTime;
	}
	public void settDepartTime(String tDepartTime) {
		this.tDepartTime = tDepartTime;
	}
	public String gettArrivalTime() {
		return tArrivalTime;
	}
	public void settArrivalTime(String tArrivalTime) {
		this.tArrivalTime = tArrivalTime;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	
	
	
	
	

}
