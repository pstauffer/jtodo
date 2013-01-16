package ch.zhaw.jtodo.dao.hibernate;

import java.util.List;

import ch.zhaw.jtodo.dao.ICategoryDAO;
import ch.zhaw.jtodo.domain.Category;

/**
 * Konkrete Implementation f�r Hibernate des CategoryDAO
 * @author yannik
 *
 */
public class CategoryHibernateDAO extends GenericHibernateDAO<Category, Integer> implements  ICategoryDAO { 
	
	/**
	 * Default Konstruktor, ruft super Klasse mit BO Klassentyp auf
	 */
	public CategoryHibernateDAO() {
		super(Category.class);
	}
}
