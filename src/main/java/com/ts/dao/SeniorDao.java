package com.ts.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ts.AESEncryption;
import com.ts.db.HibernateTemplate;
import com.ts.dto.Senior;
import com.ts.dto.Junior;
import com.ts.dto.Faculty;

public class SeniorDao {
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public int register(Senior senior) {
		System.out.println(senior); 
		return HibernateTemplate.addObject(senior);
	}
	
public static Object getSeniorByUserPass(String loginId,String password) {
	String queryString = "from Senior where userName = :loginId";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  Object queryResult = query.uniqueResult();
	  Senior senior = (Senior)queryResult;
	  //String dbPassword = doctor.getPassword();
	  String decPassword;
	  if(senior != null) {
	  AESEncryption aesEncryption = new AESEncryption(senior.getPassword());
		try{
			decPassword=aesEncryption.dec();
			if(decPassword != password)
				return null;
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	  }
	  return senior; 
		}
public static Object getSeniorByUserName(String userName) {	
	String queryString = "from Senior where userName = :userName";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("userName", userName);
	  Object queryResult = query.uniqueResult();
	  Senior senior = (Senior)queryResult;
	  return senior; 
	}
public static int updateSenior(Senior senior)
{
	int result=0;
	
	Transaction tx=null;
	
	try {
		
		Session session=sessionFactory.openSession();
		tx=session.beginTransaction();
		System.out.println("Inside Update Senior"+senior);
		
		session.saveOrUpdate(senior);
		
		tx.commit();
		
		result=1;
		
	} catch (Exception e) {
	
		tx.rollback();
		
		e.printStackTrace();
	}
	
	return result;
}
}