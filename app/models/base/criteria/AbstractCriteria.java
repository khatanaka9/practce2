package models.base.criteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.joda.time.DateTime;

import play.db.jpa.JPA;

public class AbstractCriteria<T> {

    protected final Integer maxResult;
    protected final DetachedCriteria criteria;

    //joinTable先のDetachedCriteriaを保持しておく
    protected final Map<String, DetachedCriteria> joinTableMap = new HashMap<String, DetachedCriteria>();

    /**
     * コンストラクタ
     * @param clazz 検索対象エンティティクラス
     * @param orderColumns ソート対象カラム "columnName,asc"
     * @param maxResult 最大返却数
     */
    public AbstractCriteria(final Class clazz,
            final List<String> orderColumns,
            final Integer maxResult) {
        this.criteria = DetachedCriteria.forClass(clazz);

        //ソートオーダー設定
        if (orderColumns != null) {
            for (final String columnName : orderColumns) {
                final String[] column = columnName.split(",");
                if (column[1].toLowerCase().equals("asc")) {
                    criteria.addOrder(Order.asc(column[0]));
                }
                else {
                    criteria.addOrder(Order.desc(column[0]));
                }
            }
        }
        this.maxResult = maxResult != null
                ? maxResult
                : 100;
    }

    /** 検索実行 */
    public List<T> exec() {
        //条件が未設定であれば空リスト返却
        if (hasFilter() == false) {
            return Collections.<T> emptyList();
        }

        //検索結果返却
        return getExecutableCriteria().setMaxResults(maxResult).list();
    }

    /** 検索条件の登録有無 */
    protected boolean hasFilter() {
        final Iterator entries = ((CriteriaImpl) getExecutableCriteria()).iterateExpressionEntries();
        return entries.hasNext();
    }

    /** 実行可能クライテリア取得 */
    protected Criteria getExecutableCriteria() {
        return criteria.getExecutableCriteria((Session) JPA.em().getDelegate());
    }

    /** like フィルタ処理 */
    protected void setLike(final String column, final String value) {
        if (StringUtils.isNotEmpty(value)) {
            criteria.add(Restrictions.ilike(column, "%" + value + "%"));
        }
    }

    /** join先 like フィルタ処理 */
    protected void setLike(final String column, final String value, final List<String> joinTables) {
        if (StringUtils.isNotEmpty(value)) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.ilike(column, "%" + value + "%"));
        }
    }

    /** equal フィルタ処理 */
    protected void setEqual(final String column, final Object value) {
        if (value != null) {
            criteria.add(Restrictions.eq(column, value));
        }
    }

    /** join先 equal フィルタ処理 */
    protected void setEqual(final String column, final Object value, final List<String> joinTables) {
        if (value != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.eq(column, value));
        }
    }

    /** not equal フィルタ処理 */
    protected void setNotEqual(final String column, final Object value) {
        if (value != null) {
            criteria.add(Restrictions.ne(column, value));
        }
    }

    /** join先 not equal フィルタ処理 */
    protected void setNotEqual(final String column,
                               final Object value,
                               final List<String> joinTables) {
        if (value != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.ne(column, value));
        }
    }

    /** in フィルタ処理 */
    protected void setIn(final String column, final List list) {
        if (list.size() != 0) {
            criteria.add(Restrictions.in(column, list));
        }
    }

    /** join先 in フィルタ処理 */
    protected void setIn(final String column, final List list, final List<String> joinTables) {
        if (list.size() != 0) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.in(column, list));
        }
    }

    /** graterThan フィルタ処理（適用開始日） */
    protected void setGt(final String column, final DateTime date) {
        if (date != null) {
            criteria.add(Restrictions.gt(column, date));
        }
    }

    /** join先 graterThan フィルタ処理（適用開始日） */
    protected void setGt(final String column, final DateTime date, final List<String> joinTables) {
        if (date != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.gt(column, date));
        }
    }

    /** graterThan or equal フィルタ処理（適用開始日） */
    protected void setGe(final String column, final DateTime date) {
        if (date != null) {
            criteria.add(Restrictions.ge(column, date));
        }
    }

    /** join先 graterThan or equal フィルタ処理（適用開始日） */
    @Deprecated
    protected void setge(final String column, final DateTime date, final List<String> joinTables) {
        if (date != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.ge(column, date));
        }
    }

    /** graterThan フィルタ処理（数値下限） */
    protected void setGt(final String column, final Integer value) {
        if (value != null) {
            criteria.add(Restrictions.gt(column, value));
        }
    }

    /** graterThan or equal フィルタ処理（数値下限） */
    protected void setGe(final String column, final Integer value) {
        if (value != null) {
            criteria.add(Restrictions.ge(column, value));
        }
    }

    /** lessThan フィルタ処理（数値上限） */
    protected void setLt(final String column, final Integer value) {
        if (value != null) {
            criteria.add(Restrictions.lt(column, value));
        }
    }

    /** graterThan or equal フィルタ処理（数値上限） */
    protected void setLe(final String column, final Integer value) {
        if (value != null) {
            criteria.add(Restrictions.le(column, value));
        }
    }

    /** join先 graterThan or equal フィルタ処理（適用開始日） */
    protected void setGe(final String column, final DateTime date, final List<String> joinTables) {
        if (date != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.ge(column, date));
        }
    }

    /** lessThan フィルタ処理（適用終了日） */
    protected void setLt(final String column, final DateTime date) {
        if (date != null) {
            criteria.add(Restrictions.lt(column, date));
        }
    }

    /** join先 lessThan フィルタ処理（適用終了日） */
    protected void setLt(final String column, final DateTime date, final List<String> joinTables) {
        if (date != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.lt(column, date));
        }
    }

    /** lessThan or equal フィルタ処理（適用終了日） */
    protected void setLe(final String column, final DateTime date) {
        if (date != null) {
            criteria.add(Restrictions.le(column, date));
        }
    }

    /** join先 lessThan or equal フィルタ処理（適用終了日） */
    protected void setLe(final String column, final DateTime date, final List<String> joinTables) {
        if (date != null) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.le(column, date));
        }
    }

    /** isNull フィルタ処理 */
    protected void setIsNull(final String column) {
        if (StringUtils.isNotEmpty(column)) {
            criteria.add(Restrictions.isNull(column));
        }
    }

    /** join先 isNull フィルタ処理 */
    protected void setIsNull(final String column, final List<String> joinTables) {
        if (StringUtils.isNotEmpty(column)) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.isNull(column));
        }
    }

    /** isNotNull フィルタ処理 */
    protected void setIsNotNull(final String column) {
        if (StringUtils.isNotEmpty(column)) {
            criteria.add(Restrictions.isNotNull(column));
        }
    }

    /** join先 isNotNull フィルタ処理 */
    protected void setIsNotNull(final String column, final List<String> joinTables) {
        if (StringUtils.isNotEmpty(column)) {
            final DetachedCriteria joinCriteria = getJoinCriteria(joinTables);
            joinCriteria.add(Restrictions.isNotNull(column));
        }
    }

    /** joinTable用criteriaインスタンス取得 */
    protected DetachedCriteria getJoinCriteria(final List<String> joinTables) {
        if (joinTables == null || joinTables.isEmpty()) {
            return this.criteria;
        }

        DetachedCriteria joinCriteria = joinTableMap.get(joinTables.toString());

        if (joinCriteria == null) {

            final DetachedCriteria baseCriteria = joinTables.size() == 1
                    ? this.criteria
                    : getJoinCriteria(joinTables.subList(0, joinTables.size() - 1));
            joinCriteria = baseCriteria.createCriteria(joinTables.get(joinTables.size() - 1),
                                                       CriteriaSpecification.LEFT_JOIN);
            joinTableMap.put(joinTables.toString(), joinCriteria);
        }

        return joinCriteria;
    }

}
