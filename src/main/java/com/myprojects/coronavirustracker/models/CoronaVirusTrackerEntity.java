package com.myprojects.coronavirustracker.models;

public class CoronaVirusTrackerEntity {

	private String state;
	private String country;
	private int latestCasesConfirmed;
	private int latestDeaths;
	private int latestRecoveredCases;

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public int getLatestCasesConfirmed() {
		return latestCasesConfirmed;
	}

	public int getLatestDeaths() {
		return latestDeaths;
	}

	public int getLatestRecoveredCases() {
		return latestRecoveredCases;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLatestCasesConfirmed(int latestCasesConfirmed) {
		this.latestCasesConfirmed = latestCasesConfirmed;
	}

	public void setLatestDeaths(int latestDeaths) {
		this.latestDeaths = latestDeaths;
	}

	public void setLatestRecoveredCases(int latestRecoveredCases) {
		this.latestRecoveredCases = latestRecoveredCases;
	}
	
	@Override
	public String toString() {
		return "CoronaVirusTrackerEntity [state=" + state + ", country=" + country + ", latestCasesConfirmed="
				+ latestCasesConfirmed + ", latestDeaths=" + latestDeaths + ", latestRecoveredCases="
				+ latestRecoveredCases + "]";
	}
}
