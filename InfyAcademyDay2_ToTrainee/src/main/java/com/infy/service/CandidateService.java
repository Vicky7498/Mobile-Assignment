package com.infy.service;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public interface CandidateService {
	public abstract String addCandidate(Candidate candidate) throws Exception;

	public abstract String calculateGrade(CandidateReport candidateReportTO);

	public abstract String[] getGradesForAllCandidates();
}
