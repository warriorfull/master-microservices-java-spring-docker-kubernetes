package com.eaztbytes.loans.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loans {

	@Column(name = "loan_number")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanNumber;
	@Column(name = "customer_id")
	private int customerId;
	@Column(name = "start_dt")
	private Date startDt;
	@Column(name = "loan_type")
	private String loanType;
	@Column(name = "total_loan")
	private int totalLoan;
	@Column(name = "amount_paid")
	private int amountPaid;
	@Column(name = "outstanding_amount")
	private int outstandingAmount;
	@Column(name = "create_dt")
	private String createDt;
	
	public Loans() {
	}
	
	public Loans(int loanNumber, int customerId, Date startDt, String loanType, int totalLoan, int amountPaid,
			int outstandingAmount, String createDt) {
		super();
		this.loanNumber = loanNumber;
		this.customerId = customerId;
		this.startDt = startDt;
		this.loanType = loanType;
		this.totalLoan = totalLoan;
		this.amountPaid = amountPaid;
		this.outstandingAmount = outstandingAmount;
		this.createDt = createDt;
	}
	public int getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getTotalLoan() {
		return totalLoan;
	}
	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public int getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	@Override
	public String toString() {
		return "Loans [loanNumber=" + loanNumber + ", customerId=" + customerId + ", startDt=" + startDt + ", loanType="
				+ loanType + ", totalLoan=" + totalLoan + ", amountPaid=" + amountPaid + ", outstandingAmount="
				+ outstandingAmount + ", createDt=" + createDt + "]";
	}
	
	
}
