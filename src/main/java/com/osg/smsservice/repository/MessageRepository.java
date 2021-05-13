package com.osg.smsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osg.smsservice.entity.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	

}
