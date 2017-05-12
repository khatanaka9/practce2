package models.user.collection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import models.base.AbstractCollection2;
import models.user.User;
import models.user.types.UserType;

public  class UsersCollection extends AbstractCollection2<UsersCollection, User>{

	public UsersCollection(List<User> list) {
		super(list);
	}


	//名前でフィルタ
	public UsersCollection filterByName(String name){
		return new UsersCollection(list.stream().filter(user -> user.name.equals(name)).collect(Collectors.toList()));
	}

	public List<String> mapToUserName(){
		return (list.stream().map(user -> user.name).collect(Collectors.toList()));
	}

	public boolean isAnyMatchByUserName(){
		return (list.stream().anyMatch(user -> user.name.equals("name")));
	}

	public boolean isAllMatchByUserName(){
		return (list.stream().anyMatch(user -> user.name.equals("name")));
	}

	public UsersCollection sortedByUserNameAsc(){
		List<User> sortedList = list.stream().sorted(Comparator.comparing(user -> ((User)user).name)).collect(Collectors.toList());
		return new UsersCollection(sortedList);
	}

	public UsersCollection sortedByUserNameDesc(){
		List<User> sortedList = list.stream().sorted(Comparator.comparing(user -> ((User)user).name).reversed()).collect(Collectors.toList());
		return new UsersCollection(sortedList);
	}


	public UsersCollection filterByUserType(UserType userType) {
		return new UsersCollection(list.stream().filter(user -> user.userType().equals(userType)).collect(Collectors.toList()));
	}


}
