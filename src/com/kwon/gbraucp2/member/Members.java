package com.kwon.gbraucp2.member;

import java.util.List;

public class Members {
	private List<Member> members;

	public Members() {
		// TODO Auto-generated constructor stub
	}

	public Members(List<Member> members) {
		super();
		this.members = members;
	}


	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
