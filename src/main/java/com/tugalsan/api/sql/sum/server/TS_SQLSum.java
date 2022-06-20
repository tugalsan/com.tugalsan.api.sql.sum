package com.tugalsan.api.sql.sum.server;

import com.tugalsan.api.executable.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.where.server.*;

public class TS_SQLSum {

    public TS_SQLSum(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.executor = new TS_SQLSumExecutor(anchor, tableName, columnName);
    }
    private TS_SQLSumExecutor executor;

    public long whereGroupAnd(TGS_ExecutableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(groups);
        return executor.execute();
    }

    public long whereGroupOr(TGS_ExecutableType1<TS_SQLWhereGroups> groups) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(groups);
        return executor.execute();
    }

    public long whereConditionAnd(TGS_ExecutableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupAnd(where -> where.conditionsAnd(conditions));
    }

    public long whereConditionOr(TGS_ExecutableType1<TS_SQLWhereConditions> conditions) {
        return whereGroupOr(where -> where.conditionsOr(conditions));
    }

    public long whereConditionNone() {
        return executor.execute();
    }
}
