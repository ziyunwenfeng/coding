package entity;

public class Order {
	private int orderId;
	private int user;
	private String detail;
	public Order() {}
	public Order(int orderId,int user,String detail) {
		this.orderId = orderId;
		this.user = user;
		this.detail = detail;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
