package ch.zhaw.jtodo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author yannik
 *
 * @param <T>
 * @param <ID>
 */
public interface IGenericDAO<T, ID extends Serializable> {

	/**
	 * 
	 * @param id
	 * @return
	 */
	T findById(ID id);

	/**
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 
	 * @param businessObject
	 * @return
	 * @throws Exception
	 */
	T write(T businessObject) throws Exception;
	
	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	void delete(T businessObject) throws Exception;
	
	void update(T businessObject) throws Exception;
}