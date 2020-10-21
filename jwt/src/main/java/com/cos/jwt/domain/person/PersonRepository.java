package com.cos.jwt.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Integer>{

	Person findByUsernameAndPassword(String username, String password);
	
	@Query(value = "select * from person where username=:username and password=:password", nativeQuery = true)
	Person check(@Param("username")String name,@Param("password")String pass);
}
