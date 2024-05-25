package com.infy.dao;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public interface CandidateDAO {
	public abstract String addCandidate(Candidate candidates);
	public abstract CandidateReport[] getAllCandidates();
}
