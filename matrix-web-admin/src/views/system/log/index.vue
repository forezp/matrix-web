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
          <el-input v-model="listQuery.createBy" placeholder="操作者"></el-input>
        </el-col>
        <el-col :span="4">
          <el-select v-model="listQuery.method" placeholder="请求类型">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
           <el-form-item label="日志的RequestId">
             <span>{{ props.row.requestId }}</span>
           </el-form-item>
           <el-form-item label="请求类型">
             <span>{{ props.row.method }}</span>
           </el-form-item>
           <el-form-item label="请求地址">
             <span>{{ props.row.url }}</span>
           </el-form-item>
           <el-form-item label="请求的用户">
             <span>{{ props.row.createBy }}</span>
           </el-form-item>
           <el-form-item label="请求参数">
             <span>{{ props.row.request }}</span>
           </el-form-item>
           <el-form-item label="响应时间">
             <span>{{ props.row.duration }}</span>
           </el-form-item>
           <el-form-item label="请求时间">
             <span>{{ props.row.createTime }}</span>
           </el-form-item>
           <el-form-item label="响应状态">
             <span>{{ props.row.resonseCode }}</span>
           </el-form-item>
           <el-form-item label="响应">
             <span>{{ props.row.response }}</span>
           </el-form-item>
           <el-form-item label="请求ip">
             <span>{{ props.row.ip }}</span>
           </el-form-item>


         </el-form>
       </template>
     </el-table-column>

     <el-table-column
       label="用户"
       prop="createBy">
     </el-table-column>
     <el-table-column
       label="请求类型"
       prop="method">
     </el-table-column>
     <el-table-column
       label="请求地址"
       prop="url">
     </el-table-column>
     <el-table-column
       label="时间"
       prop="createTime">
     </el-table-column>
   </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.pageSize"
      :total="totalCount"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext">
    </el-pagination>

  </div>
</template>

<script src="./log.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
