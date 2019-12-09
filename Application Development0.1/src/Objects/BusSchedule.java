package Objects;

public class BusSchedule {
	
	private int busID;
	private String departCity;
	private String arrivalCity;
	private String departTime;
	private String arrivalTime;
	private int passengerCount;
	
	
	public BusSchedule(int busID, String departCity, String arrivalCity, String arrivalTime,String departTime, int passengerCount) {
		super();
		this.busID = busID;
		this.departCity = departCity;
		this.arrivalCity = arrivalCity;
		this.departTime = departTime;
		this.arrivalTime = arrivalTime;
		this.passengerCount = passengerCount;
	}
	
	public int getBusID() {
		return busID;
	}
	public void setBusID(int busID) {
		this.busID = busID;
	}
	public String getDepartCity() {
		return departCity;
	}
	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public  int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	
	

}
