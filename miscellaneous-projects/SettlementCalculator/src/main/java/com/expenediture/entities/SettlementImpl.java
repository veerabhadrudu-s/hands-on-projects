package com.expenediture.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementImpl implements Settlement {

	private final String spenderName;
	private final Map<String, PartnerPaymentImpl> nameToPaymentInfo = new HashMap<>();

	public SettlementImpl(final String spenderName) {
		super();
		this.spenderName = spenderName;
	}

	@Override
	public String getSpenderName() {
		return spenderName;
	}

	@Override
	public List<? extends PartnerPayment> getPartnerPayments() {
		return new ArrayList<PartnerPaymentImpl>(nameToPaymentInfo.values());
	}

	public void addPartnerPayment(final String partnerName, final double amount) {
		PartnerPaymentImpl partnerPaymentImpl = nameToPaymentInfo.get(partnerName);
		if (partnerPaymentImpl == null) {
			partnerPaymentImpl = new PartnerPaymentImpl(partnerName);
			nameToPaymentInfo.put(partnerName, partnerPaymentImpl);
		}
		partnerPaymentImpl.addToSettlement(amount);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameToPaymentInfo == null) ? 0 : nameToPaymentInfo.hashCode());
		result = prime * result + ((spenderName == null) ? 0 : spenderName.hashCode());
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
		final SettlementImpl other = (SettlementImpl) obj;
		if (nameToPaymentInfo == null) {
			if (other.nameToPaymentInfo != null)
				return false;
		} else if (!nameToPaymentInfo.equals(other.nameToPaymentInfo))
			return false;
		if (spenderName == null) {
			if (other.spenderName != null)
				return false;
		} else if (!spenderName.equals(other.spenderName))
			return false;
		return true;
	}

}
