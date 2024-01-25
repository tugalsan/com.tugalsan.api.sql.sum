package com.tugalsan.api.sql.sum.server;

import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;

public class TS_SQLSumUtils {

    public static TS_SQLSum sum(TS_SQLConnAnchor anchor, CharSequence tableName, CharSequence columnName) {
        return new TS_SQLSum(anchor, tableName, columnName);
    }

//    public static void test() {
//        var sum = TS_SQLSumUtils
//                .sum(null, "tn", "cn")
//                .whereConditionAnd(conditions -> conditions.lngEq("", 0));
//        out.println(sum);
//    }
}
