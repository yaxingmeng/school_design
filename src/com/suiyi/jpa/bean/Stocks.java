package com.suiyi.jpa.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Stocks {
private Integer id;
private String goods;
private String provide;
private Integer inamount;
private Date intime;
private String sale;
private Integer outamount;
private Date outtime;
@Id
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getGoods() {
	return goods;
}
public void setGoods(String goods) {
	this.goods = goods;
}
public String getProvide() {
	return provide;
}
public void setProvide(String provide) {
	this.provide = provide;
}
public Integer getInamount() {
	return inamount;
}
public void setInamount(Integer inamount) {
	this.inamount = inamount;
}
public Date getIntime() {
	return intime;
}
public void setIntime(Date intime) {
	this.intime = intime;
}
public String getSale() {
	return sale;
}
public void setSale(String sale) {
	this.sale = sale;
}
public Integer getOutamount() {
	return outamount;
}
public void setOutamount(Integer outamount) {
	this.outamount = outamount;
}
public Date getOuttime() {
	return outtime;
}
public void setOuttime(Date outtime) {
	this.outtime = outtime;
}

}
