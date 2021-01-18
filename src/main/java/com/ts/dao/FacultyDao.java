package com.ts.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ts.dto.Senior;
import com.ts.dto.Junior;
import com.ts.dto.Faculty;
import com.ts.AESEncryption;
import com.ts.db.HibernateTemplate;

public class FacultyDao {
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	public int register(Faculty faculty) {
		System.out.println(faculty); 
		return HibernateTemplate.addObject(faculty);
	}
public static Object getFacultyByUserPass(String loginId,String password) {
	String queryString = "from Faculty where userName = :loginId";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  Object queryResult = query.uniqueResult();
	  Faculty faculty = (Faculty)queryResult;
	  //String dbPassword = doctor.getPassword();
	  String decPassword;
	  if(faculty != null) {
	  
		try{
			AESEncryption aesEncryption = new AESEncryption(faculty.getPassword());
			decPassword=aesEncryption.dec();
			System.out.println("Inside getFaculty By User Pass:"+decPassword+" "+password);
			if(decPassword.equals(password))
				return null;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	  }
	  return faculty; 
		}
public static Object getFacultyByUserName(String userName) {
	
	String queryString = "from Faculty where userName = :userName";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("userName", userName);
	  Object queryResult = query.uniqueResult();
	  Faculty faculty = (Faculty)queryResult;
	  return faculty; 
	}
public static int updateFaculty(Faculty faculty)
{
	int result=0;
	
	Transaction tx=null;
	
	try {
		
		Session session=sessionFactory.openSession();
		tx=session.beginTransaction();
		System.out.println("Inside Update Faculty"+faculty);
		
		session.saveOrUpdate(faculty);
		
		tx.commit();
		
		result=1;
		
	} catch (Exception e) {
	
		tx.rollback();
		
		e.printStackTrace();
	}
	
	return result;
}
}