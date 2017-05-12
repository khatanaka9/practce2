package models.project.types;

import lombok.*;
import lombok.experimental.*;

@Getter
@Accessors(fluent = true)
public enum ProjectType {
    
    不動産案件(1, true),
    銀行システム案件(2, true),
    病院システム案件(3, true), ;
    public Integer intValue;
    public Boolean ediType;
    
    private ProjectType(final Integer intValue, final Boolean ediType) {
        this.intValue = intValue;
        this.ediType = ediType;
    }
    
    public static ProjectType valueOf(final Integer value) {
        for (final ProjectType type : values()) {
            if (type.intValue.equals(value)) {
                return type;
            }
        }
        
        return null;
    }
}
