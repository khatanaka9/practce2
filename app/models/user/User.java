package models.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import models.user.types.UserType;
import play.db.jpa.Model;

@Entity(name="Users")
@Getter
@Accessors(fluent = true)
public class User extends Model{

    //カラムの作成
	//ユーザー名
    @Column(name = "name")
    public String name;
  //public UserName name;

    //パスワード
    @Column(name = "pass")
    public String pass;

    //ユーザー種別
    @Column(name="userType")
    public Integer userType;

    public User(String name,String pass){
    	this.name = name;
    	this.pass = pass;
    	this.userType = UserType.利用者.intValue;
    }

    public User(String name,String pass,UserType userType){
    	this.name = name;
    	this.pass = pass;
    	this.userType = userType.intValue;
    }

    public UserType userType(){
    	return UserType.valueOf(this.userType);
    }
}