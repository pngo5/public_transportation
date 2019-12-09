package BusinessLogic;

import Objects.BusSchedule;
import Database.Mysql;

public class Schedules implements Scheduling {
	@Override
	public void addUserToDataBase(BusSchedule BusSchedule) throws Exception {
		Mysql.post(BusSchedule);
		
	}

}
