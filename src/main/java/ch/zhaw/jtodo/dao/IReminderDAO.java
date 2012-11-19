package ch.zhaw.jtodo.dao;

import ch.zhaw.jtodo.domain.Reminder;

/**
 * This interace extends the basic functionality of the gerneric Interface.
 * If there are special methods needed by the ReminderDAO, this interface
 * would provide them.
 * @author yannik
 */
public interface IReminderDAO extends IGenericDAO<Reminder,Integer>{

}
