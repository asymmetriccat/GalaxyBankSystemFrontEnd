package com.guilong.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.guilong.domain.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Long>{
   List<Appointment> findAll();
}
