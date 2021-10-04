package com.ty.assaignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hybernetproject.Student;

public class InsertIntoStudent {
	public static void main(String[] args) {
		Student student = new Student();
		student.setSid(1);
		student.setSname("surya");
		student.setSdept("civil");
		student.setSloc("chennai");
		EntityTransaction transaction = null;	
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(student);
			
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();;
			}
		}
		finally {
			if (manager != null) {
				manager.close();
			}
			if (factory != null) {
				factory.close();
			}
			
		}
		
	}

}
