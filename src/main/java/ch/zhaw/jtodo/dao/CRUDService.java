package ch.zhaw.jtodo.dao;

import java.util.List;

import ch.zhaw.jtodo.dao.hibernate.HibernateUtil;
import ch.zhaw.jtodo.domain.Category;

public class CRUDService {

	public CRUDService() {
	}

	/**
	 * Obsolete class, just for inital tests
	 */
	public void testCrudService() {
		Category cat = new Category();

		cat.setName("test1");
		DAOFactory factory2 = new DAOFactory();

		ICategoryDAO catDAO = factory2.getCategoryDAO();

		try {
			Category cat3 = catDAO.write(cat);
			Category cat2 = catDAO.findById(1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Category> catList = catDAO.findAll();

		HibernateUtil.closeSession();

	}

}
