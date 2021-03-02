package com.binginx.myboot.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class CampDtlReadModel {
    @ExcelProperty(index = 0)
    private String custNbr; 
    
    @ExcelProperty(index = 1)
    private String flag;
    
	public String getCustNbr() {
		return custNbr;
	}

	public void setCustNbr(String custNbr) {
		this.custNbr = custNbr;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "CampDtlReadModel [custNbr=" + custNbr + ", flag=" + flag + "]";
	}
}
