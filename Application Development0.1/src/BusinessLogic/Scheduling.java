package BusinessLogic;

import Database.Mysql;
import Objects.BusSchedule;

public interface Scheduling {
	public abstract void addUserToDataBase(BusSchedule BusSchedule) throws Exception;
	
}

	
