package com.shangde.edu.res.domain;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class VedioBookCountStatus  extends PageQuery{
	
	
	private Date data;

	private int kjzc;
	private int kjz;
	private int jjs;
	private int xl;
	private int jz2;


	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getKjzc() {
		return kjzc;
	}

	public void setKjzc(int kjzc) {
		this.kjzc = kjzc;
	}

	public int getKjz() {
		return kjz;
	}


	public void setKjz(int kjz) {
		this.kjz = kjz;
	}

	public int getJjs() {
		return jjs;
	}

	public void setJjs(int jjs) {
		this.jjs = jjs;
	}

	public int getXl() {
		return xl;
	}

	public void setXl(int xl) {
		this.xl = xl;
	}

	public int getJz2() {
		return jz2;
	}

	public void setJz2(int jz2) {
		this.jz2 = jz2;
	}



	
}
