<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.category" placeholder="请输入分类ID"></el-input>
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
          <el-button type="danger" icon="el-icon-delete" @click.native="remove">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">
      <el-table-column label="流程ID">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="流程分类">
        <template slot-scope="scope">
          {{scope.row.category}}
        </template>
      </el-table-column>
      <el-table-column label="模型标识">
        <template slot-scope="scope">
          {{scope.row.key}}
        </template>
      </el-table-column>
      <el-table-column label="模型名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="版本号">
        <template slot-scope="scope">
          {{scope.row.version}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <a class="el-button el-button--primary el-button--small is-plain"
             :href="`/static/activiti/modeler.html?modelId=${scope.row.id}`" target="_blank">编辑</a>
          <el-button size="small" @click="deploy(scope.row)">部署</el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="60%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <!--<el-form-item label="流程分类" prop="category">-->
        <!--<el-input v-model="form.category"  minlength=1></el-input>-->
        <!--</el-form-item>-->


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

        <el-form-item label="流程名称" prop="name">
          <el-input v-model="form.name" minlength=1 :disabled="!isAdd"></el-input>
        </el-form-item>
        <el-form-item label="流程描述" prop="desc" v-if="isAdd">
          <el-input v-model="form.desc" minlength=1 ></el-input>
        </el-form-item>
        <el-form-item label="流程标识" prop="key">
          <el-input v-model="form.key" minlength=1 :disabled="!isAdd"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
        <a ref="a" :href="href" target="_blank" style="display:none"></a>
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

