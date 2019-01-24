package com.expenediture.entities;

import java.util.List;

public interface Settlement {

	public String getSpenderName();

	public List<? extends PartnerPayment> getPartnerPayments();


}
