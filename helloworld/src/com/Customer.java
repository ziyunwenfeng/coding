package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
@Service
@Scope("prototype")
public class Customer {
	@Autowired
	private Person person;
	public Customer(){}
	
	public Customer(Person person){
		this.person = person;
	}
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	public void output(){
		person.out();
	}
}
