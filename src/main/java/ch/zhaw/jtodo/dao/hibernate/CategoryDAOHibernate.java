package ch.zhaw.jtodo.dao.hibernate;

import java.util.List;

import ch.zhaw.jtodo.dao.ICategoryDAO;
import ch.zhaw.jtodo.domain.Category;

public class CategoryDAOHibernate extends GenericHibernateDAO<Category, Integer> implements  ICategoryDAO { 
	
	public CategoryDAOHibernate() {
		super(Category.class);
	}
}
