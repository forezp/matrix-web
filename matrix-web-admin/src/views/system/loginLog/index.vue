<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="24">
        <el-col :span="4">
          <el-date-picker type="date" placeholder="起始日期" v-model="listQuery.beginTime" value-format="yyyy-MM-dd"
                          style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-date-picker type="date" placeholder="结束日期" v-model="listQuery.endTime"  value-format="yyyy-MM-dd"
                          style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.userId" placeholder="用户名"></el-input>
        </el-col>

        <el-col :span="8">
          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
          <el-button type="danger" icon="el-icon-delete" @click.native="clear">{{ $t('button.clear') }}</el-button>
        </el-col>
      </el-row>
      <br>

    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
     <el-table-column type="expand">
       <template slot-scope="props">
         <el-form label-position="left" inline class="guns-table-expand">
           <el-form-item label="用户id">
             <span>{{ props.row.loginName }}</span>
           </el-form-item>
           <el-form-item label="用户姓名">
             <span>{{ props.row.realname }}</span>
           </el-form-item>
           <el-form-item label="登录IP">
             <span>{{ props.row.ip }}</span>
           </el-form-item>
           <el-form-item label="登录结果">
             <span>{{ props.row.statusName }}</span>
           </el-form-item>
           <el-form-item label="登录时间">
             <span>{{ props.row.loginTime }}</span>
           </el-form-item>
         </el-form>
       </template>
     </el-table-column>

     <el-table-column
       label="登录名"
       prop="loginName">
     </el-table-column>
     <el-table-column
       label="登录结果"
       prop="statusName">
     </el-table-column>
     <el-table-column
       label="登录IP"
       prop="ip">
     </el-table-column>
     <el-table-column
       label="登录时间"
       prop="loginTime">
     </el-table-column>
   </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.paseSize"
      :total="totalCout"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>

  </div>
</template>

<script src="./loginLog.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
