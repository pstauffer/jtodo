package ch.zhaw.jtodo.dao.hibernate;

import java.util.List;

import ch.zhaw.jtodo.dao.ICategoryDAO;
import ch.zhaw.jtodo.domain.Category;

public class CategoryHibernateDAO extends GenericHibernateDAO<Category, Integer> implements  ICategoryDAO { 
	
	public CategoryHibernateDAO() {
		super(Category.class);
	}
}
