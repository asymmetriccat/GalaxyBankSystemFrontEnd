package com.guilong.service.userServiceImpl;

import static org.mockito.Matchers.anyCollection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilong.dao.AppointmentDao;
import com.guilong.domain.Appointment;
import com.guilong.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	

	@Override
	public Appointment createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return appointmentDao.save(appointment);
	}

	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub
		return appointmentDao.findAll();
	}

	@Override
	public Appointment findAppointment(Long id) {
		// TODO Auto-generated method stub
		return appointmentDao.findOne(id);
	}

	@Override
	public void confirmAppointment(Long id) {
		// TODO Auto-generated method stub
		Appointment appointment=findAppointment(id);
		appointment.setConfirmed(true);
		appointmentDao.save(appointment);
	}
  
}
