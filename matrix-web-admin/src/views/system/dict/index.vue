<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="3">
          <el-input v-model="listQuery.codeId" placeholder="请输入字典编码"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input v-model="listQuery.codeName" placeholder="请输入字典名称"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input v-model="listQuery.typeId" placeholder="请输入字典分类"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input v-model="listQuery.typeName" placeholder="请输入字典分类名称"></el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button type="danger" icon="el-icon-delete" @click.native="remove">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>


    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
    @current-change="handleCurrentChange">
      <el-table-column label="字典编码">
        <template slot-scope="scope">
          {{scope.row.codeId}}
        </template>
      </el-table-column>
      <el-table-column label="字典名称">
        <template slot-scope="scope">
          {{scope.row.codeName}}
        </template>
      </el-table-column>
      <el-table-column label="字典分类">
        <template slot-scope="scope">
          {{scope.row.typeId}}
        </template>
      </el-table-column>
      <el-table-column label="分类名称">
        <template slot-scope="scope">
          {{scope.row.typeName}}
        </template>
      </el-table-column>
      <el-table-column label="序号">
        <template slot-scope="scope">
          {{scope.row.sort}}
        </template>
      </el-table-column>
      <el-table-column label="备注">
        <template slot-scope="scope">
          {{scope.row.remarks}}
        </template>
      </el-table-column>


    </el-table>


    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="60%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="字典编码" prop="codeId">
          <el-input v-model="form.codeId"  minlength=1></el-input>
        </el-form-item>
        <el-form-item label="编码名称" prop="codeName">
          <el-input v-model="form.codeName"  minlength=1></el-input>
        </el-form-item>
          <el-form-item label="字典分类" >
            <el-input
              placeholder="请选择字典"
              v-model="form.typeName"
              readonly="readonly"
              @click.native="showTree = !showTree">
            </el-input>
            <el-tree v-if="showTree"
                     empty-text="暂无数据"
                     :expand-on-click-node="false"
                     :data="typeData"
                     :props="defaultProps"
                     @node-click="handleNodeClick"
                     class="input-tree">
            </el-tree>
          </el-form-item>

        <el-form-item type="number" label="字段排序" prop="sort">
          <el-input v-model="form.sort"  minlength=1></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks"  minlength=1></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

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

<script src="./dict.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
