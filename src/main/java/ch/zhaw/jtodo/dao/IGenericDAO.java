package ch.zhaw.jtodo.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable> {
	 
    T findById(ID id);
 
    List<T> findAll();
 
    T write(T businessObject) throws Exception;
 
    void delete(T entity) throws Exception;
}