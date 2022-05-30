package com.bitcero.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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
			CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);

			// Se definde el tipo de entidad que retorna la consulta
			Root<Transaction> root = criteria.from(Transaction.class);
			
			Path<Integer> pathId = root.get(Transaction_.id);
			Path<Timestamp> pathDate = root.get(Transaction_.date);
			

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parseDate = dateFormat.parse("2022-05-31 00:00:00");

			// Construyendo la consulta
			criteria.multiselect(pathId, pathDate)
				.where(builder.and(
					builder.like(root.get(Transaction_.type), "%Inversion%"),
					builder.lessThan(root.get(Transaction_.date), new Timestamp(parseDate.getTime()))
					));

			List<Tuple> tuples = session.createQuery(criteria).getResultList();
			
			for (Tuple tuple : tuples) {
				System.out.println("Tupla: " +  tuple.get(pathId) + "," + tuple.get(pathDate));
			}

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