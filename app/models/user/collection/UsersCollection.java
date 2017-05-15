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

	//名前で更新
	public List<String> mapToUserName(String name){
		return (list.stream().map(user -> name).collect(Collectors.toList()));
	}

	//名前が"Hatanaka"か判定
	public boolean isAnyMatchByUserName(){
		return(list.stream().anyMatch(user -> user.name.equals("Hatanaka")));
	}

	//名前の文字数が8文字か判定
	public boolean isAllMatchByUserName(){
		return (list.stream().allMatch(user -> user.name.length() == 8));
	}

	//名前えを昇順にソート
	public UsersCollection sortedByUserNameAsc(){
		List<User> sortedList = list.stream().sorted(Comparator.comparing(user -> ((User)user).name)).collect(Collectors.toList());
		return new UsersCollection(sortedList);
	}

	//名前を降順にソート
	public UsersCollection sortedByUserNameDesc(){
		List<User> sortedList = list.stream().sorted(Comparator.comparing(user -> ((User)user).name).reversed()).collect(Collectors.toList());
		return new UsersCollection(sortedList);
	}


	//ユーザー種別でフィルタ
	public UsersCollection filterByUserType(UserType userType) {
		return new UsersCollection(list.stream().filter(user -> user.userType().equals(userType)).collect(Collectors.toList()));
	}


}
