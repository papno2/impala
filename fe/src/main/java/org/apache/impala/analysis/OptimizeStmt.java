// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.impala.analysis;

import org.apache.impala.catalog.FeIcebergTable;
import org.apache.impala.common.AnalysisException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of an OPTIMIZE statement used to execute table maintenance tasks in
 * Iceberg tables.
 */
public class OptimizeStmt extends InsertStmt {

  public static OptimizeStmt createOptimize(TableName targetTable) {
    List<SelectListItem> selectListItems = new ArrayList<>();
    selectListItems.add(SelectListItem.createStarItem(null));
    SelectList selectList = new SelectList(selectListItems);
    List<TableRef> tableRefs = new ArrayList<>();
    tableRefs.add(new TableRef(targetTable.toPath(), null));
    QueryStmt queryStmt = new SelectStmt(selectList, new FromClause(tableRefs), null, null, null, null, null);
    return new OptimizeStmt(targetTable, queryStmt);
  }

  protected OptimizeStmt(TableName targetTable, QueryStmt queryStmt) {
    super(null, targetTable, true, null, null, null, queryStmt, null, false);
  }

  @Override
  public void analyze(Analyzer analyzer) throws AnalysisException {
    if (isAnalyzed()) return;
    super.analyze(analyzer);
    if (!(table_ instanceof FeIcebergTable)) {
      throw new AnalysisException("OPTIMIZE is only supported for Iceberg tables.");
    }
  }

  @Override
  public void reset() {
    super.reset();
  }

}