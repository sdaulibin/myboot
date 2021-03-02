package com.binginx.myboot.examination;

public class BankInfo {
    private String bankNo;
    private String bankLevel;
    private String parentBankNo;
    private String bankName;
    private String status;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankLevel() {
        return bankLevel;
    }

    public void setBankLevel(String bankLevel) {
        this.bankLevel = bankLevel;
    }

    public String getParentBankNo() {
        return parentBankNo;
    }

    public void setParentBankNo(String parentBankNo) {
        this.parentBankNo = parentBankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return bankNo + " " + bankLevel + " " + parentBankNo + " " + bankName + " " + status;
    }
}
