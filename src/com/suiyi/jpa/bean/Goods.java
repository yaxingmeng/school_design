package com.suiyi.jpa.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.suiyi.jpa.Utils.EnumName;

@Entity
@Table(name = "goods")
public class Goods extends BasicBean {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "good_no")
	private String goodNo;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private Integer type;

	@Column(name = "price")
	private Double price;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "state")
	private Integer state;
	
	@Column(name="unit")
	private String unit;

	@OneToOne
	@JoinColumn(name = "type", insertable = false, updatable = false)
	private GoodType goodType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public GoodType getGoodType() {
		return goodType;
	}

	public void setGoodType(GoodType goodType) {
		this.goodType = goodType;
	}

	public String getStateName() {
		return EnumName.GoodState.getCaptionByValue(state);
	}

	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Transient
	public String getTypeName() {
		return goodType.getName();
	}

}
