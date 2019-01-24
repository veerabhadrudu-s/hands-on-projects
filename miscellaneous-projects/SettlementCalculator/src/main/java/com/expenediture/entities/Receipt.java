package com.expenediture.entities;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

	private int expenditure;
	private String contributor;
	private List<String> partners = new ArrayList<>();

	public Receipt() {
		super();
	}

	public Receipt(final int expenditure, final String contributor) {
		super();
		this.expenditure = expenditure;
		this.contributor = contributor;
	}

	public Receipt(final int expenditure, final String contributor, final List<String> partners) {
		this(expenditure, contributor);
		this.partners = partners;
	}

	public int getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(final int expenditure) {
		this.expenditure = expenditure;
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(final String contributor) {
		this.contributor = contributor;
	}

	public List<String> getPartners() {
		return partners;
	}

	public void setPartners(final List<String> partners) {
		this.partners = partners;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contributor == null) ? 0 : contributor.hashCode());
		result = prime * result + expenditure;
		result = prime * result + ((partners == null) ? 0 : partners.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Receipt other = (Receipt) obj;
		if (contributor == null) {
			if (other.contributor != null)
				return false;
		} else if (!contributor.equals(other.contributor))
			return false;
		if (expenditure != other.expenditure)
			return false;
		if (partners == null) {
			if (other.partners != null)
				return false;
		} else if (!partners.equals(other.partners))
			return false;
		return true;
	}



}
