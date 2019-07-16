package com.revoult.entity;


/**
 * @author Vivek Grewal
 */
public class Transaction {
    private volatile int idSequence = 0;
    private String id;
    private double amount;
    private Account accountFrom;
    private Account accountTo;
   
    public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	private String reasonForTransfer;
    private Status status;
    private String failureReason;


    public Transaction(Account accountFrom, Account accountTo, double amount, String reasonForTransfer) {
        this.id = String.valueOf(++idSequence);
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.reasonForTransfer = reasonForTransfer;
        this.status = Status.CREATED;
    }

    public Account getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}

	public String getReasonForTransfer() {
		return reasonForTransfer;
	}

	public void setReasonForTransfer(String reasonForTransfer) {
		this.reasonForTransfer = reasonForTransfer;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transaction(Account accountFrom, Account accountTo, double amount) {
        this(accountFrom, accountTo, amount, "");
    }

    public double getAmount() {
        return amount;
    }

  
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enum Status {
        CREATED, IN_PROGRESS, FAILED, SUCCESS;
    }
}
