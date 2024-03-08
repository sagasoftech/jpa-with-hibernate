package com.sagasoftech.jpa.hibernate.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/coursename/{courseId}")
    public ResponseEntity<String> getCourseNameById(@PathVariable Long courseId){
		Course course = repository.findById(courseId);
		logger.info("Course {} -> {}", courseId, course);
		
		if(Objects.isNull(course)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(course.getName());
    }
	
	@GetMapping("/course/delete/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long courseId){
		repository.deleteById(courseId);
		logger.info("Course {} -> deleted", courseId);
		
		return ResponseEntity.ok("Course " +courseId+ " deleted.");
    }
	
	@PostMapping("/course/save")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		repository.save(course);
		logger.info("Course {} -> {}", course.getId(), course);
		
		if(Objects.isNull(course)) {
            return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(course);
    }
	
	@PostMapping("/course/playem")
    public ResponseEntity<Course> playWithEntityManager(@RequestBody Course course){
		repository.playEntityManager(course);
		logger.info("Course {} -> {}", course.getId(), course);
		
		if(Objects.isNull(course)) {
            return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
		}
		
		repository.playEntityManager2(new Course("Angular JS for Flush demo"));
		repository.playEntityManager3(new Course("Angular JS 1 for detach demo"), new Course("Angular JS 2 for detach demo"));
		
		return ResponseEntity.ok(course);
    }
}
