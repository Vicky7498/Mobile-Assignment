package com.infy.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.infy.model.Candidate;

enum Department {
	ECE, CSE, IT, EEE
}

public class Validator {
	// calls the validation methods for individual inputs
	// if any method returns false, then the String value correspondingly
	public String validate(Candidate candidate) {
		if (!isValidCandidateName(candidate.getCandidateName())) {
			return "The entered candidate name is invalid.";
		}
		if (!isValidCandidateId(candidate.getCandidateId())) {
			return "The entered candidate ID is invalid.";
		}
		if (!isValidDepartment(candidate.getDepartment())) {
			return "The entered Department name is invalid.";
		}
		if (!isValidExamDate(candidate.getExamDate())) {
			return "The entered Exam Date is invalid.";
		}
		if (!isValidExamMarks(candidate)) {
			return "The entered exam marks are invalid.";
		}
		if (!isValidResult(candidate.getResult())) {
			return " The entered result is invalid.";
		}
		return null;
	}

	// The entered candidate name should contain only alphabets. Cannot have
	// special characters and only spaces
	public Boolean isValidCandidateName(String candidateName) {
		String regex = "^[a-zA-Z]*$";
		if (candidateName.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	// The entered candidate ID should be of size 5
	public Boolean isValidCandidateId(Integer candidateId) {
		String regex = "^\\d{5}$";
		if (candidateId > 99999) {
			return false;
		}
		return true;
	}

	// The entered Department name should be one among the given departments
	// (ECE, CSE, IT, EEE)
	public Boolean isValidDepartment(String department) {
		if (Department.valueOf(department) != null) {
			return true;
		}
		return false;
	}

	// exam date cannot be today or after todays date
	public Boolean isValidExamDate(LocalDate examDate) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter currentDateFormate = DateTimeFormatter.ofPattern("yyyy, MM, dd");
		String examDateCompare = examDate.format(currentDateFormate);
		if (examDate.compareTo(currentDate) < 0) {
			return true;
		}
		return false;
	}

	// Checking if marks are not equal to "0"
	public Boolean isValidExamMarks(Candidate candidateTO) {
		if (candidateTO.getMark1() < 0 || candidateTO.getMark2() < 0 || candidateTO.getMark3() < 0) {
			return false;
		}
		return true;
	}

	// Checking if result set is either 'P' or 'F' only
	public Boolean isValidResult(Character result) {
		if (result == 'P' || result == 'F') {
			return true;
		}
		return false;
	}

}
