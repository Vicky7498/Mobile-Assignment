package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Request;

import com.infy.validator.Validator;
import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;

public class MobileServiceImpl implements MobileService {

	private MobileServiceDAO dao = new MobileServiceDAOImpl();
	private Validator validator = new Validator();

	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws MobileServiceException {
		validator.validate(service);
		float serviceFee = calculateEstimateCost(service.getIssues());
		if (serviceFee <= 0) {
			throw new MobileServiceException("Sorry, we do not provide that service.");
		}
		service.setServiceFee(serviceFee);
		service.setStatus(Status.ACCEPTED);
		service.setTimeOfRequest(LocalDateTime.now());
		return dao.registerRequest(service);
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		float serviceFess = 0;
		for (String i : issues) {
			if (i.equalsIgnoreCase("BATTERY")) {
				serviceFess += 10;
			} else if (i.equalsIgnoreCase("CAMERA")) {
				serviceFess += 5;
			} else if (i.equalsIgnoreCase("SCREEN")) {
				serviceFess += 15;
			} else {
				serviceFess += 0;
			}
		}
		return serviceFess;
	}

	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		List<ServiceReport> serviceReport = new ArrayList<ServiceReport>();
		List<ServiceRequest> serviceRequest = new ArrayList<ServiceRequest>();
		serviceRequest = dao.getServices();
		for (ServiceRequest request : serviceRequest) {
			if (request.getStatus().equals(status)) {
				ServiceReport report = new ServiceReport(request.getServiceId(), request.getBrand(),
						request.getIssues(), request.getServiceFee());
				serviceReport.add(report);
			}
		}
		if (serviceReport.isEmpty()) {
			throw new MobileServiceException("Sorry, we did not find any records for your query");
		} else {
			return serviceReport;
		}
	}

}
