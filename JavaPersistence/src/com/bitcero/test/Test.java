package com.bitcero.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.bitcero.domain.Transaction;
import com.bitcero.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);

		// Se definde el tipo de entidad que retorna la consulta
		Root<Transaction> root = criteria.from(Transaction.class);

		// Construyendo la consulta
		criteria.select(root);
		List<Transaction> transactions = session.createQuery(criteria).getResultList();

		System.out.println(transactions);

		session.getTransaction().commit();
		session.close();
	}
}