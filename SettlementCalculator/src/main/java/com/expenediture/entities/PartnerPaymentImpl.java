package com.expenediture.entities;

public class PartnerPaymentImpl implements PartnerPayment {

	private final String partnerName;
	private double settlementAmount;

	public PartnerPaymentImpl(final String partnerName) {
		super();
		this.partnerName = partnerName;
	}

	@Override
	public String getPartnerName() {
		return partnerName;
	}

	@Override
	public double getSettlementAmmount() {
		return settlementAmount;
	}

	public void addToSettlement(final double shareFromReceipt) {
		settlementAmount += shareFromReceipt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partnerName == null) ? 0 : partnerName.hashCode());
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
		final PartnerPaymentImpl other = (PartnerPaymentImpl) obj;
		if (partnerName == null) {
			if (other.partnerName != null)
				return false;
		} else if (!partnerName.equals(other.partnerName))
			return false;
		return true;
	}

}
