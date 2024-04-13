package com.tugalsan.api.sql.sum.server;

import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.where.server.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TS_SQLSum {

    public TS_SQLSum(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.executor = new TS_SQLSumExecutor(anchor, tableName, columnName);
    }
    private final TS_SQLSumExecutor executor;

    public TGS_UnionExcuse<Long> whereGroupAnd(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<Long> whereGroupOr(TGS_RunnableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public TGS_UnionExcuse<Long> whereConditionAnd(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public TGS_UnionExcuse<Long> whereConditionOr(TGS_RunnableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public TGS_UnionExcuse<Long> whereConditionNone() {
        return executor.run();
    }
}
