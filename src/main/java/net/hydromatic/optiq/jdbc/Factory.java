/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package net.hydromatic.optiq.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Factory for JDBC objects.
 *
 * <p>There is an implementation for each supported JDBC version.</p>
 */
interface Factory {
    int getJdbcMajorVersion();

    int getJdbcMinorVersion();

    OptiqConnectionImpl newConnection(
        UnregisteredDriver driver,
        Factory factory,
        String url,
        Properties info);

    OptiqStatement newStatement(
        OptiqConnectionImpl connection,
        int resultSetType,
        int resultSetConcurrency,
        int resultSetHoldability);

    OptiqPreparedStatement newPreparedStatement(
        OptiqConnectionImpl connection,
        String sql,
        int resultSetType,
        int resultSetConcurrency,
        int resultSetHoldability) throws SQLException;

    /**
     * Creates a result set. You will then need to call
     * {@link net.hydromatic.optiq.jdbc.OptiqResultSet#execute()} on it.
     *
     * @param statement Statement
     * @param prepareResult Result
     * @return Result set
     */
    OptiqResultSet newResultSet(
        OptiqStatement statement,
        OptiqPrepare.PrepareResult prepareResult);

    OptiqDatabaseMetaData newDatabaseMetaData(
        OptiqConnectionImpl connection);

    ResultSetMetaData newResultSetMetaData(
        OptiqStatement statement,
        OptiqPrepare.PrepareResult prepareResult);
}

// End Factory.java
