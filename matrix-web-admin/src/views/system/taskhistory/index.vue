<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.triggerName" placeholder="请输入任务执行类"></el-input>
        </el-col>
        <el-col :span="6">
          <el-input v-model="listQuery.groupId" placeholder="请输入任务组ID"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
    @current-change="handleCurrentChange">
      <el-table-column label="任务执行类">
        <template slot-scope="scope">
          {{scope.row.triggerName}}
        </template>
      </el-table-column>
      <el-table-column label="任务组ID" >
        <template slot-scope="scope">
          {{scope.row.groupId}}
        </template>
      </el-table-column>
      <el-table-column label="任务入参">
        <template slot-scope="scope">
          {{scope.row.params}}
        </template>
      </el-table-column>

      <el-table-column label="任务响应">
        <template slot-scope="scope">
          {{scope.row.result}}
        </template>
      </el-table-column>

      <el-table-column label="任务执行时间">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="totalCount"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>
  </div>
</template>

<script src="./taskhistory.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

