package com.bitcero.test;

import org.hibernate.Session;

import com.bitcero.domain.Report;
import com.bitcero.domain.Transaction;
import com.bitcero.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// Transaction transaction = new Transaction("Credit denied", new Timestamp(new Date().getTime()));
			
			// Transaction transaction = new Transaction("Credit in progress", new Timestamp(new Date().getTime()));
			
			
			// session.saveOrUpdate(transaction);
			
			Transaction transaction = session.load(Transaction.class, 6);
			Report report = new Report("PC componentes, online");
			report.setTransaction(transaction);
			
			session.saveOrUpdate(report);
			
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}