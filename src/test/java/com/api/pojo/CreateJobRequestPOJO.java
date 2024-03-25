package com.api.pojo;

import java.util.Arrays;

public class CreateJobRequestPOJO {
	private int mst_service_location_id;
	private int mst_platform_id;
	private int mst_warrenty_status_id;
	private int mst_oem_id;
	private Customer customer;
	private Customer_address custadd;
	private Customer_product custprod;
	private Problems[] problems;
	public CreateJobRequestPOJO(int mst_service_location_id, int mst_platform_id, int mst_warrenty_status_id,
			int mst_oem_id, Customer customer, Customer_address custadd, Customer_product custprod,
			Problems[] problems) {
		super();
		this.mst_service_location_id = mst_service_location_id;
		this.mst_platform_id = mst_platform_id;
		this.mst_warrenty_status_id = mst_warrenty_status_id;
		this.mst_oem_id = mst_oem_id;
		this.customer = customer;
		this.custadd = custadd;
		this.custprod = custprod;
		this.problems = problems;
	}
	public int getMst_service_location_id() {
		return mst_service_location_id;
	}
	public void setMst_service_location_id(int mst_service_location_id) {
		this.mst_service_location_id = mst_service_location_id;
	}
	public int getMst_platform_id() {
		return mst_platform_id;
	}
	public void setMst_platform_id(int mst_platform_id) {
		this.mst_platform_id = mst_platform_id;
	}
	public int getMst_warrenty_status_id() {
		return mst_warrenty_status_id;
	}
	public void setMst_warrenty_status_id(int mst_warrenty_status_id) {
		this.mst_warrenty_status_id = mst_warrenty_status_id;
	}
	public int getMst_oem_id() {
		return mst_oem_id;
	}
	public void setMst_oem_id(int mst_oem_id) {
		this.mst_oem_id = mst_oem_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer_address getCustadd() {
		return custadd;
	}
	public void setCustadd(Customer_address custadd) {
		this.custadd = custadd;
	}
	public Customer_product getCustprod() {
		return custprod;
	}
	public void setCustprod(Customer_product custprod) {
		this.custprod = custprod;
	}
	public Problems[] getProblems() {
		return problems;
	}
	public void setProblems(Problems[] problems) {
		this.problems = problems;
	}
	@Override
	public String toString() {
		return "CreateJobRequestPOJO [mst_service_location_id=" + mst_service_location_id + ", mst_platform_id="
				+ mst_platform_id + ", mst_warrenty_status_id=" + mst_warrenty_status_id + ", mst_oem_id=" + mst_oem_id
				+ ", customer=" + customer + ", custadd=" + custadd + ", custprod=" + custprod + ", problems="
				+ Arrays.toString(problems) + "]";
	}
	
	

}
