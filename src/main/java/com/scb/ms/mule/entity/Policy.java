package com.scb.ms.mule.entity;

public class Policy {

	private String policy;
	private String type;
	private String active_from;
	private String premium;
	private String currency;
	
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActive_from() {
		return active_from;
	}
	public void setActive_from(String active_from) {
		this.active_from = active_from;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Policy(String policy, String type, String active_from, String premium, String currency) {
		super();
		this.policy = policy;
		this.type = type;
		this.active_from = active_from;
		this.premium = premium;
		this.currency = currency;
	}
	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((policy == null) ? 0 : policy.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policy other = (Policy) obj;
		if (policy == null) {
			if (other.policy != null)
				return false;
		} else if (!policy.equals(other.policy))
			return false;
		return true;
	}
	
}
