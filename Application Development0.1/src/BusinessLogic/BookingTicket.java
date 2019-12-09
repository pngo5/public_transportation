package BusinessLogic;

import Objects.BusSchedule;
import javafx.collections.ObservableList;
/**
 * this interface will inforce the booking of tickets
 * @author abdulhakimdarman
 *
 */

public interface BookingTicket {
	
	public void getTicketInfo();
	
	public ObservableList<BusSchedule> addBusTicket() throws Exception;
	
	public ObservableList<BusSchedule> deleteBusTicket() throws Exception;

}
