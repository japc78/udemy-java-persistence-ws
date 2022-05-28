package com.bitcero.test;

import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import com.bitcero.domain.Transaction;
import com.bitcero.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();

		// Crea una instancia de la transaccion
		Transaction transaction = new Transaction("Credit", new Timestamp(date.getTime()));

		// Salvar la transaccion
		session.save(transaction);

		session.getTransaction().commit();
		session.close();
	}
}