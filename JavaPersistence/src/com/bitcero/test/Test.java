package com.bitcero.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.bitcero.domain.Transaction;
import com.bitcero.domain.Transaction_;
import com.bitcero.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tx = null;

		try {
			tx = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);

			// Se definde el tipo de entidad que retorna la consulta
			Root<Transaction> root = criteria.from(Transaction.class);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parseDate = dateFormat.parse("2022-05-30 17:24:11");

			// Construyendo la consulta
			criteria.select(root).where(builder.and(
					builder.like(root.get(Transaction_.type), "%Inversion%"),
					builder.lessThan(root.get(Transaction_.date), new Timestamp(parseDate.getTime()))
					));

			List<Transaction> transactions = session.createQuery(criteria).getResultList();

			System.out.println(transactions);

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