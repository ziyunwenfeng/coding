package com;

public class Person {
	private int id;
	public Person(){};
	public Person(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void out(){
		System.out.println(getId());
	}
	
}
