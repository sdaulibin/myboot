package com.binginx.myboot.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class SmsInfoReadModel {
    @ExcelProperty(index = 0)
    private String custNbr;

    @ExcelProperty(index = 1)
    private String replaceOne;

    @ExcelProperty(index = 2)
    private String replaceTwo;

    @ExcelProperty(index = 3)
    private String replaceThree;

    @ExcelProperty(index = 4)
    private String replaceFour;

    @ExcelProperty(index = 5)
    private String replaceFive;

    @ExcelProperty(index = 6)
    private String flag;

    public String getCustNbr() {
        return custNbr;
    }

    public void setCustNbr(String custNbr) {
        this.custNbr = custNbr;
    }

    public String getReplaceOne() {
        return replaceOne;
    }

    public void setReplaceOne(String replaceOne) {
        this.replaceOne = replaceOne;
    }

    public String getReplaceTwo() {
        return replaceTwo;
    }

    public void setReplaceTwo(String replaceTwo) {
        this.replaceTwo = replaceTwo;
    }

    public String getReplaceThree() {
        return replaceThree;
    }

    public void setReplaceThree(String replaceThree) {
        this.replaceThree = replaceThree;
    }

    public String getReplaceFour() {
        return replaceFour;
    }

    public void setReplaceFour(String replaceFour) {
        this.replaceFour = replaceFour;
    }

    public String getReplaceFive() {
        return replaceFive;
    }

    public void setReplaceFive(String replaceFive) {
        this.replaceFive = replaceFive;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SmsInfoReadModel [custNbr=" + custNbr + ", replaceOne="
                + replaceOne + ", replaceTwo=" + replaceTwo + ", replaceThree="
                + replaceThree + ", replaceFour=" + replaceFour
                + ", replaceFive=" + replaceFive + ", flag=" + flag + "]";
    }
}
