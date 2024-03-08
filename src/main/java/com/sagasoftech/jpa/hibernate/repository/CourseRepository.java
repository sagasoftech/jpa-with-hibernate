package com.sagasoftech.jpa.hibernate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sagasoftech.jpa.hibernate.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class CourseRepository {	
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id){
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id){
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId()==null) {
			//Insert
			em.persist(course);
		}else {
			//Update
			em.merge(course);
		}
		return course;
	}
	
	public Course playEntityManager(Course course) {
		if(course.getId()==null) {
			//Insert
			em.persist(course);
		}else {
			//Update
			em.merge(course);
		}
		course.setName("Rest Services in 1000 steps");
		
		return course;
	}

	//flush() used to send data to database immediately before executing next code
	public Course playEntityManager2(Course course) {
		em.persist(course);
		em.flush();
		
		course.setName("Angular JS after flush");
		em.flush();
		
		return course;
	}
	
	//detach() used to detach entity object from entity manager. 
	//With this next operation related to that entity will not be sent to DB
	//Changes to other entity in the same block will be sent to DB
	public Course playEntityManager3(Course course1, Course course2) {
		em.persist(course1);
		em.persist(course2);
		
		em.detach(course1);
		
		//course 1 details will not be saved since it has been detached from em
		course1.setName("Angular JS 1 after detach");
		em.flush();
		course2.setName("Angular JS 2 after detach");
		em.flush();
		
		return course1;
	}
	
	//clear() used to detach all entity objects from entity manager. 
	//With this next operation related to any entity will not be sent to DB
	//No DB update after clear
	public Course playEntityManager4(Course course1, Course course2) {
		em.persist(course1);
		em.persist(course2);
		em.flush();
		
		em.clear();
		
		//course 1 details will not be saved since it has been detached from em
		course1.setName("Vue JS 1 after clear");
		em.flush();
		course2.setName("Vue JS 2 after clear");
		em.flush();
		
		return course1;
	}
	
	//refresh() used to refresh the entity objects from DB. 
	//All the changes which are not flushed will be reloaded from the DB
	public Course playEntityManager5(Course course1, Course course2) {
		em.persist(course1);
		em.persist(course2);
		em.flush();
		
		
		course1.setName("React JS 1 - Updated");
		course2.setName("React JS 2 - Updated");
		
		//course 1 details will be loaded from DB using select statement
		em.refresh(course1);
		
		//Only course 2 changes will go to DB 
		//Course 1 changes will not go to DB Since course 1 changes are loaded from DB and no further changes.
		em.flush();
		
		return course1;
	}
}
