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

public class JuniorDao {
	private static SessionFactory sessionFactory;
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public int register(Junior junior) {
		System.out.println(junior); 
		return HibernateTemplate.addObject(junior);
	}
	
public static Object getJuniorByUserPass(String loginId,String password) {
	String queryString = "from Junior where userName = :loginId";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("loginId", loginId);
	  Object queryResult = query.uniqueResult();
	  Junior junior = (Junior)queryResult;
	  //String dbPassword = doctor.getPassword();
	  String decPassword;
	  if(junior != null) {
	  AESEncryption aesEncryption = new AESEncryption(junior.getPassword());
		try{
			decPassword=aesEncryption.dec();
			if(decPassword != password)
				return null;
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	  }
	  return junior; 
		}
public static Object getJuniorByUserName(String userName) {	
	String queryString = "from Junior where userName = :userName";
	  Query query = sessionFactory.openSession().createQuery(queryString);
	  query.setString("userName", userName);
	  Object queryResult = query.uniqueResult();
	  Junior junior = (Junior)queryResult;
	  return junior; 
	}
public static int updateJunior(Junior junior)
{
	int result=0;
	
	Transaction tx=null;
	
	try {
		
		Session session=sessionFactory.openSession();
		tx=session.beginTransaction();
		System.out.println("Inside Update Junior"+junior);
		
		session.saveOrUpdate(junior);
		
		tx.commit();
		
		result=1;
		
	} catch (Exception e) {
	
		tx.rollback();
		
		e.printStackTrace();
	}
	
	return result;
}
}