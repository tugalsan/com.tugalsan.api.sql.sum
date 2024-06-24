package com.tugalsan.api.sql.sum.server;


import com.tugalsan.api.callable.client.TGS_CallableType1Void;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.where.server.*;

public class TS_SQLSum {

    public TS_SQLSum(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.executor = new TS_SQLSumExecutor(anchor, tableName, columnName);
    }
    private final TS_SQLSumExecutor executor;

    public long whereGroupAnd(TGS_CallableType1Void<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.run();
    }

    public long whereGroupOr(TGS_CallableType1Void<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.run();
    }

    public long whereConditionAnd(TGS_CallableType1Void<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public long whereConditionOr(TGS_CallableType1Void<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public long whereConditionNone() {
        return executor.run();
    }
}
