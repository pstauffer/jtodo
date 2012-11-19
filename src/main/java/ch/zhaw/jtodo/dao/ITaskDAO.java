package ch.zhaw.jtodo.dao;

import ch.zhaw.jtodo.domain.Task;

/**
 * This interace extends the basic functionality of the gerneric Interface.
 * If there are special methods needed by the TaskDAO, this interface
 * would provide them.
 * @author yannik
 */
public interface ITaskDAO extends IGenericDAO<Task,Integer>{

}
