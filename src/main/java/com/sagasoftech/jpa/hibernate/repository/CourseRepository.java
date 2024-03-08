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

	public Course playEntityManager2(Course course) {
		em.persist(course);
		em.flush();
		
		course.setName("Angular JS in 1000 steps");
		em.flush();
		
		return course;
	}
}
