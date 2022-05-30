package com.bitcero.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 8189200771071907152L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private Timestamp date;
	
	@OneToOne(mappedBy = "transaction")
	private Report report;

	public Transaction() {

	}

	public Transaction(String type, Timestamp date) {
		super();
		this.type = type;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", type=" + type + ", date=" + date + "]";
	}

}
