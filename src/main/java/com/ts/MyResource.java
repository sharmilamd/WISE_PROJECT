package com.ts;
import com.ts.db.HibernateTemplate;

import javax.crypto.SecretKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ts.dao.SeniorDao;
import com.ts.dao.JuniorDao;
import com.ts.dao.FacultyDao;
import com.ts.dto.Faculty;
import com.ts.dto.Senior;
import com.ts.dto.Junior;
import com.ts.dto.Company;
import com.ts.dto.Review;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("getFacultyByUserPass/{firstName}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getFacultyByUserPass(@PathParam("firstName") String firstName, @PathParam("password") String password) {
		System.out.println("Recieved path params: " + firstName + " " + password);
		FacultyDao facultyDao = new FacultyDao();
		Faculty faculty = (Faculty) facultyDao.getFacultyByUserPass(firstName, password);
		return faculty;
	}

	@Path("registerFaculty")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void registerFaculty(Faculty faculty) {
		System.out.println("Faculty Registered!" + faculty);
		AESEncryption aesEncryption = new AESEncryption(faculty.getPassword());
		try{
			faculty.setPassword(aesEncryption.enc());
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		FacultyDao facultyDao = new FacultyDao();
		facultyDao.register(faculty);
	}
	@Path("getFacultyByUserName/{firstName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getFacultyByUserName(@PathParam("firstName") String firstName) {
		System.out.println("Faculty UserName!" + firstName);
		
		FacultyDao facultyDao = new FacultyDao();
		Faculty faculty = (Faculty)facultyDao.getFacultyByUserName(firstName);
		return faculty;
	}
	@Path("updateFaculty")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFaculty(Faculty faculty) {
		System.out.println("Inside Update" + faculty);
		FacultyDao facultyDao = new FacultyDao();
		facultyDao.updateFaculty(faculty);
		//return x;
		
	}

	/*@Path("registerDoctor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void registerDoctor(Doctor doctor) {
		AESEncryption aesEncryption = new AESEncryption(doctor.getPassword());
		try{
			doctor.setPassword(aesEncryption.enc());
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		DoctorDao doctorDao = new DoctorDao();
		doctorDao.register(doctor);
	}*/

	@Path("registerSenior")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void registerSenior(Senior senior) {
		AESEncryption aesEncryption = new AESEncryption(senior.getPassword());
		try{
			senior.setPassword(aesEncryption.enc());
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		SeniorDao seniorDao = new SeniorDao();
		seniorDao.register(senior);
	}

	@Path("registerJunior")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void registerJunior(Junior junior) {
		AESEncryption aesEncryption = new AESEncryption(junior.getPassword());
		try{
			junior.setPassword(aesEncryption.enc());
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		
		JuniorDao juniorDao = new JuniorDao();
			juniorDao.register(junior);
	}

	/*@Path("getDoctorByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getDoctorByUserPass(@PathParam("loginId") String loginId, @PathParam("password") String password) {
		System.out.println("Recieved path params: " + loginId + " " + password);
		DoctorDao doctorDao = new DoctorDao();
		Doctor doctor = (Doctor) doctorDao.getDoctorByUserPass(loginId, password);
		return doctor;
	}*/

	@Path("getJuniorByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getJuniorByUserPass(@PathParam("loginId") String loginId,
			@PathParam("password") String password) {
		System.out.println("Recieved path params: " + loginId + " " + password);
		JuniorDao juniorDao = new JuniorDao();
		Junior junior = (Junior) juniorDao.getJuniorByUserPass(loginId, password);
		return junior;
	}

	@Path("getSeniorByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getSeniorByUserPass(@PathParam("loginId") String loginId, @PathParam("password") String password) {
		System.out.println("Recieved path params: " + loginId + " " + password);
		SeniorDao SeniorDao = new SeniorDao();
		Senior senior = (Senior) SeniorDao.getSeniorByUserPass(loginId, password);
		return senior;
	}
	@Path("getSeniorByUserName/{firstName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getSeniorByUserName(@PathParam("firstName") String firstName) {
		System.out.println("Senior firstName!" + firstName);
		SeniorDao seniorDao = new SeniorDao();
		Senior senior = (Senior)seniorDao.getSeniorByUserName(firstName);
		return senior;
	}
	@Path("updateSenior")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateSenior(Senior senior) {
		System.out.println("Inside Update" + senior);
		SeniorDao seniorDao = new SeniorDao();
		seniorDao.updateSenior(senior);
		//return x;
		
	}
	/*@Path("getDoctorByUserName/{userName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getDoctorByUserName(@PathParam("userName") String userName) {
		System.out.println("Doctor UserName!" + userName);
		DoctorDao doctorDao = new DoctorDao();
		Doctor doctor = (Doctor)DoctorDao.getDoctorByUserName(userName);
		return doctor;
	}
	@Path("updateDoctor")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDoctor(Doctor doctor) {
		System.out.println("Inside Update" + doctor);
		DoctorDao doctorDao = new DoctorDao();
		doctorDao.updateDoctor(doctor);
		//return x;
		
	}*/
	@Path("getJuniorByUserName/{userName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getJuniorByUserName(@PathParam("firstName") String firstName) {
		System.out.println("Junior FirstName!" + firstName);
		JuniorDao juniorDao = new JuniorDao();
		Junior junior = (Junior)juniorDao.getJuniorByUserName(firstName);
		return junior;
	}
	@Path("updateJunior")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateJunior(Junior junior) {
		System.out.println("Inside Update" + junior);
		JuniorDao juniorDao = new JuniorDao();
		JuniorDao.updateJunior(junior);
		//return x;
		
	}
}