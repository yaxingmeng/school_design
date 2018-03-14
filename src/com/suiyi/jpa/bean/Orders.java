package com.suiyi.jpa.bean;

import com.suiyi.jpa.Utils.EnumName;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders extends BasicBean {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "order_no")
	private String orderNo;

	@Column(name = "pay_state")
	private Integer payState;

	@Column(name = "tran_state")
	private Integer tranState;

	@Column(name = "location")
	private String location;

	@Column(name = "phone")
	private String phone;

	@Column(name = "contact")
	private String contact;

	@OneToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@OneToMany
	@JoinColumn(name = "order_id")
	private List<OrderItem> orderItem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Integer getTranState() {
		return tranState;
	}

	public void setTranState(Integer tranState) {
		this.tranState = tranState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Double getTotalPrice(){
		Double price=0.0;
		for(OrderItem orderIt:orderItem){
			price=price+orderIt.getTotalPrice();
		}
		return price;
	}

	public String  getPayStateName(){
		return EnumName.PaySate.getCaptionByValue(payState);
	}

	public String getTransStateName(){
		return EnumName.TranState.getCaptionByValue(tranState)
;	}

}
