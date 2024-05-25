package com.infy.validator;

import java.util.List;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {

	public void validate(ServiceRequest service) throws MobileServiceException {
		// your code goes here
		if (!isValidBrand(service.getBrand())) {
			throw new MobileServiceException("Sorry! we do not provide service for this brand");
		}
		if (!isValidIssues(service.getIssues())) {
			throw new MobileServiceException("Please provide the device only if there are issues.");
		}
		if (!isValidIMEINumber(service.getiMEINumber())) {
			throw new MobileServiceException("Sorry! we’re not able to detect the IMEI number for this device");
		}
		if (!isValidContactNumber(service.getContactNumber())) {
			throw new MobileServiceException("Please provide a valid contact number");
		}
		if (!isValidCustomerName(service.getCustomerName())) {
			throw new MobileServiceException("Please provide a valid customer name");
		}
	}

	// validates the brand
	// brand should always start with a upper case alphabet
	// and can be followed by one or more alphabets (lower case or upper case)
	public Boolean isValidBrand(String brand) {
		String brandPattern = "^[A-Z][a-zA-Z]*$";
		if (brand.matches(brandPattern)) {
			return true;
		}
		return false;
	}

	// validates the list of issues
	// checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		for (String i : issues) {
			if (i == null || i.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	// validates the IMEINumber
	// it should be a 16 digit number
	public Boolean isValidIMEINumber(Long iMEINumber) {
		String imeiPattern = "^\\d{16}$";
		String Lnumber = Long.toString(iMEINumber);
		if (Lnumber.matches(imeiPattern)) {
			return true;
		}
		return false;
	}

	// validates the contact number
	// should contain 10 numeric characters and should not contain 10 repetitive
	// characters
	public Boolean isValidContactNumber(Long contactNumber) {
		String regexContactNumber = "^(?!.*(\\d)\\1{9})\\d{10}$";
		String stringContactNumber = Long.toString(contactNumber);
		if (stringContactNumber.matches(regexContactNumber)) {
			return true;
		}
		return false;
	}

	// validates the customer name
	// should contain at least one word and each word separated by a single space
	// should contain at least one letter.
	// the first letter of every word should be an upper case character
	public Boolean isValidCustomerName(String customerName) {
		String regexCustomerName = "\\b[A-Z][a-zA-Z]*\\b";
		if (customerName.matches(regexCustomerName)) {
			return true;
		}
		return false;
	}
}
