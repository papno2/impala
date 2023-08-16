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

import com.google.common.base.Preconditions;
import org.apache.impala.authorization.Privilege;
import org.apache.impala.catalog.FeIcebergTable;
import org.apache.impala.catalog.FeTable;
import org.apache.impala.common.AnalysisException;
import org.apache.impala.rewrite.ExprRewriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of an OPTIMIZE statement used to execute table maintenance tasks in
 * Iceberg tables.
 */
public class OptimizeStmt extends DmlStatementBase{

  // INSERT OVERWRITE statement
  private InsertStmt insertStmt_;
  private QueryStmt queryStmt_;
  private TableName targetTableName_;

  public static OptimizeStmt createOptimize(TableName targetTable) {
    return new OptimizeStmt(targetTable);
  }

  protected OptimizeStmt(TableName targetTable) {
    targetTableName_ = targetTable;
    List<SelectListItem> selectListItems = new ArrayList<>();
    selectListItems.add(SelectListItem.createStarItem(null));
    SelectList selectList = new SelectList(selectListItems);
    List<TableRef> tableRefs = new ArrayList<>();
    tableRefs.add(new TableRef(targetTable.toPath(), null));
    queryStmt_ = new SelectStmt(selectList, new FromClause(tableRefs), null,
            null, null, null, null);
    insertStmt_ = new InsertStmt(null, targetTable, true, null, null, null, queryStmt_,
            null, false);
  }

  public InsertStmt getInsertStmt() { return insertStmt_; }
  public QueryStmt getQueryStmt() { return queryStmt_; }

  @Override
  public void analyze(Analyzer analyzer) throws AnalysisException {
    if (isAnalyzed()) return;
    super.analyze(analyzer);
    insertStmt_.analyze(analyzer);

    if (table_ == null) {
      if (!targetTableName_.isFullyQualified()) {
        targetTableName_ =
                new TableName(analyzer.getDefaultDb(), targetTableName_.getTbl());
      }
      table_ = analyzer.getTable(targetTableName_, Privilege.ALL);
    } else {
      targetTableName_ = new TableName(table_.getDb().getName(), table_.getName());
      analyzer.registerPrivReq(builder ->
              builder.onTable(table_).allOf(Privilege.ALL).build());
    }
    if (!(table_ instanceof FeIcebergTable)) {
      throw new AnalysisException("OPTIMIZE is only supported for Iceberg tables.");
    }
  }

  @Override
  public void reset() {
    insertStmt_.reset();
    queryStmt_.reset();
  }

  @Override
  public List<Expr> getPartitionKeyExprs() {
    return insertStmt_.getPartitionKeyExprs();
  }

  @Override
  public List<Expr> getSortExprs() {
    return insertStmt_.getSortExprs();
  }

  @Override
  public void collectTableRefs(List<TableRef> tblRefs) {
    insertStmt_.collectTableRefs(tblRefs);
  }

  @Override
  public void rewriteExprs(ExprRewriter rewriter) throws AnalysisException {
    Preconditions.checkState(isAnalyzed());
    insertStmt_.rewriteExprs(rewriter);
  }

}