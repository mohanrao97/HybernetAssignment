package com.ty.assaignment;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hybernetproject.Employee;
import com.hybernetproject.Student;

public class DriverStudent {
	public static void main(String[] args) {
		DriverStudent driverStudent = new DriverStudent();
		Student student = new Student();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		do {
			System.out.println(
					" Press 1 to see all data\n Press 2 to see any particular data\n Press 3 to update any particular data\n "
							+ "Press 4 to delete data\n press 5 exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				driverStudent.allData();
				break;
			case 2:
				driverStudent.particularData();
				break;
			case 3:
				driverStudent.updateName();
				break;
			case 4:
				driverStudent.removeData();
				break;
			case 5: {
				System.out.println("Enter Y to exit :");
				char quit = scanner.next().charAt(0);
				if (quit == 'Y' || quit == 'y') {
					exit = true;
				}
			}

				break;
			default:
				System.out.println("Invalid choie enter As Mentioned :");
				break;
			}
		} while (!exit);

	}

	// for case operations
	// for all student data
	public void allData() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = factory.createEntityManager();
		String findAll = "from Student";
		Query query = manager.createQuery(findAll);
		List<Student> list = query.getResultList();
		System.out.println("=============================");
		for (Student student : list) {
			System.out.println(student);
		}
	}

	// to get student id's
	public static boolean studentIds(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
		EntityManager manager = factory.createEntityManager();
		Student student = null;
		student = manager.find(Student.class, id);
		if (student == null) {
			return false;
		}
		return true;

	}

	// for specified data
	public void particularData() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter student id :");
		int sid = scanner.nextInt();
		boolean validate = studentIds(sid);
		if (validate == true) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
			EntityManager manager = factory.createEntityManager();
			String findid = "from Student where sid = :sid";
			Query query = manager.createQuery(findid);
			query.setParameter("sid", sid);
			Student student = (Student) query.getSingleResult();
			System.out.println(student);

		} else {
			try {
				throw new InvalidSid();
			} catch (InvalidSid e) {
				e.printStackTrace();
			}
		}
	}

	// Update by Student id
	public void updateName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enetr Student id to update :");
		int sid = scanner.nextInt();

		boolean validate = studentIds(sid);
		if (validate == true) {
			boolean exit = false;
			do {
				System.out.println(
						" press 1 to update name\n press 2 for update department\n press 3 for update loctaion\n press 4 for stop update :");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: {
					System.out.println("Enter student Name to be update :");
					String name = scanner.next();
					// name = scanner.nextLine();
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
					EntityManager manager = factory.createEntityManager();
					EntityTransaction transaction = manager.getTransaction();
					transaction.begin();
					String update = "update Student set sname = :sname where sid = :sid";
					Query query = manager.createQuery(update);
					query.setParameter("sname", name);
					query.setParameter("sid", sid);
					query.executeUpdate();
					transaction.commit();
				}
					break;
				case 2: {
					System.out.println("Enter student department to be update :");
					String dept = scanner.next();
					//dept = scanner.nextLine();
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
					EntityManager manager = factory.createEntityManager();
					EntityTransaction transaction = manager.getTransaction();
					transaction.begin();
					String update = "update Student set sdept = :dept where id = :sid";
					Query query = manager.createQuery(update);
					query.setParameter("dept", dept);
					query.setParameter("sid", sid);
					query.executeUpdate();
					transaction.commit();
				}
					break;
				case 3: {
					System.out.println("Enter student location to be update :");
					String loc = scanner.next();
					//loc = scanner.nextLine();
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
					EntityManager manager = factory.createEntityManager();
					EntityTransaction transaction = manager.getTransaction();
					transaction.begin();
					String update = "update Student set sloc = :loc where id = :sid";
					Query query = manager.createQuery(update);
					query.setParameter("loc", loc);
					query.setParameter("sid", sid);
					query.executeUpdate();
					transaction.commit();
				}
				case 4: {
					System.out.println("Enter Y to exit :");
					char quit = scanner.next().charAt(0);
					if (quit == 'Y' || quit == 'y') {
						exit = true;
					}
				}
				default:
					break;
				}

			} while (!exit);
		} else {
			try {
				throw new InvalidSid();
			} catch (InvalidSid e) {
				e.printStackTrace();
			}
		}
	}

	// Delete row
	public void removeData() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter student id :");
		int sid = scanner.nextInt();
		boolean validate = studentIds(sid);
		if (validate == true) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
			EntityManager manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			String dynamic = "delete from Student where sid = :sid";
			Query query = manager.createQuery(dynamic);
			query.setParameter("sid", sid);
			int result = query.executeUpdate();
			transaction.commit();

		} else {
			try {
				throw new InvalidSid();
			} catch (InvalidSid e) {
				e.printStackTrace();
			}
		}

	}
}
