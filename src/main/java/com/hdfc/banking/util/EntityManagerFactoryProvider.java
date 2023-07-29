package com.hdfc.banking.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {
	private static EntityManagerFactory factory = null;
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if (factory==null)	factory = Persistence.createEntityManagerFactory("JPA-PU");
		return factory;
	}
}
