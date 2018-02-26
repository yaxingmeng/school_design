package com.suiyi.jpa.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.suiyi.jpa.Utils.EnumName;


@Entity
@Table(name = "admin_user")
public class AdminUser extends BasicBean {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin_no")
    private String adminNo;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "rights")
    private Integer rights;

    @Column(name = "type")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getRights() {
		return rights;
	}

	public void setRights(Integer rights) {
		this.rights = rights;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAdminType() {
        return EnumName.AdminType.getCaptionByValue(type);
    }
    
    public String getRightName(){
    	if(type==1){
    		return "人员管理，商品管理，订单管理";
    	}
    	if(rights==2){
    		return "商品管理，订单管理";
    	}
    	return EnumName.AdminRight.getCaptionByValue(rights);
    }
}
