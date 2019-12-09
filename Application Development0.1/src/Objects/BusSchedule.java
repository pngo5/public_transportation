package Objects;

public class BusSchedule {
	
	private long busID;
	private String departCity;
	private String arrivalCity;
	private String departTime;
	private String arrivalTime;
	private long passengerCount;
	
	
	public BusSchedule(int busID, String departCity, String arrivalCity, String departTime, String arrivalTime,
			long passengerCount) {
		super();
		this.busID = busID;
		this.departCity = departCity;
		this.arrivalCity = arrivalCity;
		this.departTime = departTime;
		this.arrivalTime = arrivalTime;
		this.passengerCount = passengerCount;
	}
	
	public long getBusID() {
		return busID;
	}
	public void setBusID(long busID) {
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
	public  long getPassengerCount(long passengerCount) {
		return passengerCount;
	}
	public void setPassengerCount(long passengerCount) {
		this.passengerCount = passengerCount;
	}
	
	

}
