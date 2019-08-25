<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.category" placeholder="请输入分类名称"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="primary" icon="el-icon-edit" @click.native="edit">{{ $t('button.edit') }}</el-button>
          <el-button type="primary" icon="el-icon-edit" @click.native="transferModel"> 转模型 </el-button>
          <el-button type="danger" icon="el-icon-edit" @click.native="remove">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">

      <el-table-column label="流程发布ID">
        <template slot-scope="scope">
          {{scope.row.deploymentId}}
        </template>
      </el-table-column>
      <el-table-column label="名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="流程key">
        <template slot-scope="scope">
          {{scope.row.key}}
        </template>
      </el-table-column>
      <el-table-column label="版本">
        <template slot-scope="scope">
          {{scope.row.revision}}
        </template>
      </el-table-column>
      <el-table-column label="分类名称">
        <template slot-scope="scope">
          {{scope.row.category}}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          {{scope.row.suspendStr}}
        </template>
      </el-table-column>

      <el-table-column label="发布时间">
        <template slot-scope="scope">
          {{scope.row.deploymentTime}}
        </template>
      </el-table-column>
      <el-table-column prop="xmlName" label="流程XML" show-overflow-tooltip>
        <template slot-scope="scope">
          <a :href="`http://localhost:8082/process/resource?resType=xml&procDefId=${scope.row.processonDefinitionId}`" target="_blank">{{scope.row.xmlName}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="picName" label="流程图片" show-overflow-tooltip>
        <template slot-scope="scope">
          <a :href="`http://localhost:8082/process/resource?resType=image&procDefId=${scope.row.processonDefinitionId}`" target="_blank">{{scope.row.picName}}</a>
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope">

          <el-button size="small" @click="updateStateVue(scope.row)" type="danger" round
                     v-if="!scope.row.suspend">挂起</el-button>
          <el-button size="small" @click="updateStateVue(scope.row)" type="primary" round
                     v-if="scope.row.suspend ">激活</el-button>
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
            <el-form-item label="流程分类">
              <el-input
                placeholder="请选择流程分类"
                v-model="form.category"
                @click.native="showTree = !showTree"
              >
              </el-input>
              <el-tree v-if="showTree"
                       empty-text="暂无数据"
                       :expand-on-click-node="false"
                       :data="categoryData"
                       :props="defaultProps"
                       @node-click="handleNodeClick"
                       class="input-tree">
              </el-tree>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="updateCategoryVue">{{ $t('button.submit') }}</el-button>
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

<script src="./process.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

