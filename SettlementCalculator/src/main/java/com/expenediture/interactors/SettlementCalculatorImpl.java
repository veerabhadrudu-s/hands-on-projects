package com.expenediture.interactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expenediture.boundary.SettlementCalculator;
import com.expenediture.entities.Receipt;
import com.expenediture.entities.Settlement;
import com.expenediture.entities.SettlementImpl;

public class SettlementCalculatorImpl implements SettlementCalculator {

	private final ArrayList<Receipt> allReceipts = new ArrayList<>();
	private final Map<String, SettlementImpl> personToSettlement = new HashMap<>();

	@Override
	public ArrayList<Receipt> getAllReceipts() {
		return allReceipts;
	}

	@Override
	public void addReceipt(final Receipt receipt) {
		allReceipts.add(receipt);
	}

	@Override
	public List<? extends Settlement> getRelativeSettlementInfo() {
		return new ArrayList<>(personToSettlement.values());
	}

	@Override
	public void calculateSettlement() {
		for (final Receipt receipt : allReceipts) {
			if (receipt.getPartners().isEmpty())
				continue;
			final double eachPartnerPayment = receipt.getExpenditure() / (receipt.getPartners().size() + 1);
			final SettlementImpl settlementStatement = findSpenderSettlementStatement(receipt);
			addEachPartnersPaymentToStatement(settlementStatement, receipt.getPartners(), eachPartnerPayment);

		}
	}

	private void addEachPartnersPaymentToStatement(final SettlementImpl settlementStatement,
			final List<String> partners, final double eachPartnerPayment) {
		for (final String partnerName : partners) {
			settlementStatement.addPartnerPayment(partnerName, eachPartnerPayment);
		}

	}

	private SettlementImpl findSpenderSettlementStatement(final Receipt receipt) {
		SettlementImpl settlementImpl = personToSettlement.get(receipt.getContributor());
		if (settlementImpl == null) {
			settlementImpl = new SettlementImpl(receipt.getContributor());
			personToSettlement.put(receipt.getContributor(), settlementImpl);
		}
		return settlementImpl;
	}

}
