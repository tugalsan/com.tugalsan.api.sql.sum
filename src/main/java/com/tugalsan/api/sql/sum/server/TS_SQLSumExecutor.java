package com.tugalsan.api.sql.sum.server;

import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.tuple.client.*;
import com.tugalsan.api.sql.conn.server.*;
import com.tugalsan.api.sql.sanitize.server.*;
import com.tugalsan.api.sql.select.server.*;
import com.tugalsan.api.sql.where.server.*;

public class TS_SQLSumExecutor {

    final public static TS_Log d = TS_Log.of(TS_SQLSumExecutor.class);

    public TS_SQLSumExecutor(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        this.anchor = anchor;
        this.tableName = tableName;
        this.columnName = columnName;
    }
    final public TS_SQLConnAnchor anchor;
    final public CharSequence tableName;
    final public CharSequence columnName;

    public TS_SQLWhere where = null;

    @Override
    public String toString() {
        TS_SQLSanitizeUtils.sanitize(columnName);
        TS_SQLSanitizeUtils.sanitize(tableName);
        var sb = new StringBuilder().append("SELECT SUM(").append(columnName).append(") ").append(" FROM ").append(tableName);
        if (where != null) {
            sb.append(" ").append(where);
        }
        var stmt = sb.toString();
        d.ci("toString", stmt);
        return stmt;
    }

    public Long run() {
        TGS_Tuple1<Long> pack = new TGS_Tuple1();
        TS_SQLSelectStmtUtils.select(anchor, toString(), fillStmt -> {
            if (where != null) {
                where.fill(fillStmt, 0);
            }
        }, rs -> {
            if (rs.row.isEmpty()) {
                return;
            }
            pack.value0 = rs.lng.get(0, 0);
        });
        return pack.value0;
    }
}
