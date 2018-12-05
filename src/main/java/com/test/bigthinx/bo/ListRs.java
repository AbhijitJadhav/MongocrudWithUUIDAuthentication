package com.test.bigthinx.bo;

import java.util.List;

public class ListRs<T> extends BaseResponse {
	
	public List<T> data;
	public Integer count;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	

}
