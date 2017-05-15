package models.user.criteria;

import java.util.Collections;

import models.base.criteria.AbstractEntityCriteria;
import models.user.User;
import models.user.types.UserType;

public class UserCriteria extends AbstractEntityCriteria<UserCriteria, User>{

	//
	public UserCriteria() {
		super(User.class,Collections.EMPTY_LIST,100);
	}

	//名前に"inputName"を含むもの
	public UserCriteria filterByName(String inputName) {
		setLike("name", inputName);
		return this;
	}

	//ユーザー種別でフィルタ
	public UserCriteria filterByUserType(UserType userType) {
		setEqual("userType", userType.intValue);
		return this;
	}

}
