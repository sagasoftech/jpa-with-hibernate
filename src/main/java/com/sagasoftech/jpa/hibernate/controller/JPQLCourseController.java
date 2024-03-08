package com.sagasoftech.jpa.hibernate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagasoftech.jpa.hibernate.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@RestController
@RequestMapping("/jpql")
public class JPQLCourseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	@GetMapping("/jpql_basic")
	public List jpql_basic() {
		Query query = em.createQuery("Select  c  From Course c");
		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}", resultList);
		return resultList;
	}

	@GetMapping("/jpql_typed")
	public List<Course> jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select  c  From Course c", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select  c  From Course c -> {}", resultList);
		return resultList;
	}

	@GetMapping("/jpql_where")
	public List<Course> jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select  c  From Course c where name like '%100 Steps'",
				Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
		return resultList;
	}
}