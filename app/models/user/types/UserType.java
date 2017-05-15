package models.user.types;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public enum UserType {

	//ユーザー種別
	管理者(1,true),
	利用者(2,false),
	;
	public Integer intValue;
	public Boolean ediTable;

	private UserType(Integer intValue,Boolean ediTable){
		this.intValue = intValue;
		this.ediTable=ediTable;
	}

	public static UserType valueOf(final Integer value) {
        for (final UserType type : values()) {
            if (type.intValue.equals(value)) {
                return type;
            }
        }

       return null;
    }


}
