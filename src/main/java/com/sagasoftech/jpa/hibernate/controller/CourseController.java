package com.sagasoftech.jpa.hibernate.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sagasoftech.jpa.hibernate.entity.Course;
import com.sagasoftech.jpa.hibernate.repository.CourseRepository;

@RestController
public class CourseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;
	
	@GetMapping("/")
    public String welcome(){
        return "Welcome to JPA with Hibernate Course!";
    }
	
	@GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId){
		Course course = repository.findById(courseId);
		logger.info("Course {} -> {}", courseId, course);
		
		if(Objects.isNull(course)) {
            return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(course);
    }
}
