package com.expenediture.boundary;

import java.util.List;

import com.expenediture.entities.Receipt;
import com.expenediture.entities.Settlement;

public interface SettlementCalculator {

	List<Receipt> getAllReceipts();

	void addReceipt(Receipt receipt);

	List<? extends Settlement> getRelativeSettlementInfo();

	void calculateSettlement();

}