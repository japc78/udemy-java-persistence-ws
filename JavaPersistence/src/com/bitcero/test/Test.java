package com.bitcero.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.bitcero.domain.Transaction;
import com.bitcero.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Date date = new Date();

		// Crea una instancia de la transaccion
		// Transaction transaction = new Transaction("Credit", new
		// Timestamp(date.getTime()));
		// Transaction transaction = new Transaction("Inversion", new
		// Timestamp(date.getTime()));

		@SuppressWarnings("unchecked")
		Query<Transaction> query = session.createQuery("from Transaction");
		List<Transaction> transactions = query.getResultList();

		System.out.println(transactions.toString());

		// Salvar la transaccion
		// session.save(transaction);

		session.getTransaction().commit();
		session.close();
	}
}