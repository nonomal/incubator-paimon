/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.flink.action;

import org.apache.paimon.flink.procedure.MigrateFileProcedure;

import org.apache.flink.table.procedure.DefaultProcedureContext;

import java.util.Map;

/** Migrate from external hive table to paimon table. */
public class MigrateFileAction extends ActionBase {

    private final String connector;
    private final String sourceTable;
    private final String targetTable;
    private final String tableProperties;
    private boolean deleteOrigin;
    private Integer parallelism;

    public MigrateFileAction(
            String connector,
            String sourceTable,
            String targetTable,
            boolean deleteOrigin,
            Map<String, String> catalogConfig,
            String tableProperties,
            Integer parallelism) {
        super(catalogConfig);
        this.connector = connector;
        this.sourceTable = sourceTable;
        this.targetTable = targetTable;
        this.deleteOrigin = deleteOrigin;
        this.tableProperties = tableProperties;
        this.parallelism = parallelism;
    }

    @Override
    public void run() throws Exception {
        MigrateFileProcedure migrateTableProcedure = new MigrateFileProcedure();
        migrateTableProcedure.withCatalog(catalog);
        migrateTableProcedure.call(
                new DefaultProcedureContext(env),
                connector,
                sourceTable,
                targetTable,
                deleteOrigin,
                parallelism);
    }
}
