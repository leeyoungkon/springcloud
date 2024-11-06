package pl.piomin.services.order.model;

public class Customer {
	private String name;
	private int age;
	
	public Customer() {}
	
	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	} 
	
	

}
