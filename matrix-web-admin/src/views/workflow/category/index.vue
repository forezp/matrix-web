<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.categoryId" placeholder="请输入分类ID"></el-input>
        </el-col>
        <el-col :span="6">
          <el-input v-model="listQuery.categoryName" placeholder="请输入分类名称"></el-input>
        </el-col>
        <el-col :span="6">
          <el-input v-model="listQuery.pCategoryId" placeholder="请输入分类父ID"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" icon="el-icon-plus" @click.native="add">{{ $t('button.add') }}</el-button>
          <el-button type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">
      <el-table-column label="分类ID">
        <template slot-scope="scope">
          {{scope.row.categoryId}}
        </template>
      </el-table-column>
      <el-table-column label="分类名称">
        <template slot-scope="scope">
          {{scope.row.categoryName}}
        </template>
      </el-table-column>
      <el-table-column label="父分类ID">
        <template slot-scope="scope">
          {{scope.row.pcategoryId}}
        </template>
      </el-table-column>

      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>

    </el-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <template>
              是否为父分类
              <el-radio-group :disabled= "rationDisabled" v-model="radio" @change="rationchangeHandler">
                <el-radio class="radio" label="1">是</el-radio>
                <el-radio class="radio" label="2">否</el-radio>
              </el-radio-group>
            </template>
          </el-col>
          <el-col :span="12">
            <el-form-item label="分类ID" prop="categoryId">
              <el-input v-model="form.categoryId" :disabled="groupIdInputDisabled"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="分类名称" prop="categoryName">
              <el-input v-model="form.categoryName"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12" v-if="pCategoryIdDisabled">
            <el-form-item   label="父分类ID" prop="pCategoryId">
              <el-input v-model="form.pCategoryId" :disabled="pCategoryIdDisabled"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12" v-if="!pCategoryIdDisabled">
            <el-form-item label="父分类ID" >
              <el-input
                placeholder="请选择父分类ID"
                v-model="form.pCategoryId"
                @click.native="showTree = !showTree"
              >
              </el-input>
              <el-tree v-if="showTree"
                       empty-text="暂无数据"
                       :expand-on-click-node="false"
                       :data="pCategoryData"
                       :props="defaultProps"
                       @node-click="handleNodeClick"
                       class="input-tree">
              </el-tree>
            </el-form-item>
          </el-col>

        </el-row>
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

<script src="./category.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

