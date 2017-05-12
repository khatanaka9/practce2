package models.base.criteria;

import java.util.List;

import play.db.jpa.Model;
/**
 * クライテリア検索抽象基底クラス
 */
public abstract class AbstractEntityCriteria<T extends AbstractEntityCriteria, S extends Model> extends AbstractCriteria<S> {


    public AbstractEntityCriteria(final Class clazz,
            final List<String> orderColumns,
            final Integer maxResult) {
        super(clazz, orderColumns, maxResult);
    }


    public boolean hasFilter() {
        return super.hasFilter();
    }

}