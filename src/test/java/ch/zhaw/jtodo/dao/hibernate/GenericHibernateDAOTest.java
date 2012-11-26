package ch.zhaw.jtodo.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenericHibernateDAOTest {

	@Test
	public void testPersist() throws Exception {
		SimpleBo bo = new SimpleBo();
		SimpleHibernateDAO simpleHibernateDAO = new SimpleHibernateDAO(SimpleBo.class);
		simpleHibernateDAO.write(bo);
	}
	
	

}


class SimpleBo {
	int id;
	
}

class SimpleHibernateDAO extends GenericHibernateDAO<SimpleBo, Integer> {

	public SimpleHibernateDAO(Class<SimpleBo> type) {
		super(type);
	}
	
}
