package ch.zhaw.jtodo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Representiert das generische DAO objekt, dass alle generellen Methoden bereitstellt
 * @author yannik
 *
 * @param <T> BusinessObjekt der spezifischen DAO Klasse
 * @param <ID> PrimaryKey Typ der spezifischen DAO Klasse -> int
 */
public interface IGenericDAO<T, ID extends Serializable> {

	/**
	 * Sucht ein BO anhand seines PrimaryKeys in der DB
	 * @param id
	 * @return spezifisches BO
	 */
	T findById(ID id);

	/**
	 * Sucht alle BO's des gewünschten Typs
	 * @return Liste mit allen BO's
	 */
	List<T> findAll();

	/**
	 * Schreibt ein neues BO in die DB
	 * @param businessObject das neue BO
	 * @return geschriebenes BO
	 * @throws Exception falls schreiben fehlgschlagen ist
	 */
	T write(T businessObject) throws Exception;
	
	/**
	 * Löscht ein bestehendes BO aus der DB
	 * @param bestehendes BO
	 * @throws Exception falls löschen fehlgeschlagen ist
	 */
	void delete(T businessObject) throws Exception;
	
	/**
	 * Updatet ein bestehendes BO mit neuen Daten
	 * @param businessObject aktualisiertes BO
	 * @throws Exception
	 */
	void update(T businessObject) throws Exception;
}