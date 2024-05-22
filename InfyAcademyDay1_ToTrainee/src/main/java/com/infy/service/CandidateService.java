package com.infy.service;

import com.infy.dao.CandidateDAO;
import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public class CandidateService {

	// can have result as 'P' only if all 3 marks are 50 and above
	public String addCandidate(Candidate candidate) {
		if (candidate.getMark1() < 50 || candidate.getMark2() < 50 || candidate.getMark3() < 50
				|| candidate.getResult().equals("F")) {
			return "Result should be 'F' (Fail) if student scores less than 50 in any one subject";
		} else {
			CandidateDAO candidate1 = new CandidateDAO();
			return candidate1.addCandidate(candidate);
		}
	}

	// calculating grade for candidate based on his marks and result
	public String calculateGrade(CandidateReport candidateReportTO) {
		if (candidateReportTO.getResult() == 'F') {
			return "NA";
		} else {
			Integer average = 0;
			average = (candidateReportTO.getMark1() + candidateReportTO.getMark2() + candidateReportTO.getMark3()) / 3;
			if (average >= 85) {
				return "A";
			} else if (average < 85 && average >= 75) {
				return "B";
			} else {
				return "C";
			}
		}
	}

	// populating String[] by calling calculateGrade(candidateReportTO) and
	// returning the same.
	public String[] getGradesForAllCandidates() {
		CandidateDAO candidate1 = new CandidateDAO();
		CandidateReport[] candidates = candidate1.getAllCandidates();
		String[] idGrade = new String[candidates.length];
		int i = 0;
		for (CandidateReport c : candidates) {
			c.setGrade(calculateGrade(c));
			idGrade[i] = c.getCandidateId() + ":" + c.getGrade();
			i++;
		}
		return idGrade;
	}
}
