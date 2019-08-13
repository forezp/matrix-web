<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.taskClassName" placeholder="请输入执行类名"></el-input>
        </el-col>
        <el-col :span="6">
          <el-input v-model="listQuery.taskGroupId" placeholder="请输入任务分组"></el-input>
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
      <el-table-column label="任务分片">
        <template slot-scope="scope">
          {{scope.row.schedName}}
        </template>
      </el-table-column>
      <el-table-column label="任务名">
        <template slot-scope="scope">
          {{scope.row.triggerSimpleName}}
        </template>
      </el-table-column>
      <el-table-column label="执行类" width="300">
        <template slot-scope="scope">
          {{scope.row.triggerName}}
        </template>
      </el-table-column>
      <el-table-column label="任务分组">
        <template slot-scope="scope">
          {{scope.row.trifggerGroupName}}
        </template>
      </el-table-column>

      <el-table-column label="任务表达式">
        <template slot-scope="scope">
          {{scope.row.cronExpression}}
        </template>
      </el-table-column>

      <el-table-column label="最近执行时间">
        <template slot-scope="scope">
          {{scope.row.prevFireTimeStr}}
        </template>
      </el-table-column>

      <el-table-column label="任务下次执行时间">
        <template slot-scope="scope">
          {{scope.row.nextFireTimeStr}}
        </template>
      </el-table-column>
      <el-table-column label="任务状态">
        <template slot-scope="scope">
          {{scope.row.triggerState}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="success" icon="el-icon-log" size="mini" @click.native="resume(scope.row)"
                     v-if="scope.row.triggerState=='PAUSED'">恢复
          </el-button>
          <el-button type="danger" icon="el-icon-log" size="mini" @click.native="pause(scope.row)"
                     v-if="scope.row.triggerState=='WAITING'">暂停
          </el-button>
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
            <el-form-item label="任务名" prop="taskName">
              <el-input v-model="form.taskName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="定时规则" prop="cronExpression">
              <el-input v-model="form.cronExpression"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="执行类" prop="taskClassName">
              <el-input v-model="form.taskClassName" type="textarea"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务分组" >
              <el-input
                placeholder="请选择任务分组"
                v-model="form.taskGroupName"
                readonly="readonly"
                @click.native="showTree = !showTree">
              </el-input>
              <el-tree v-if="showTree"
                       empty-text="暂无数据"
                       :expand-on-click-node="false"
                       :data="groupData"
                       :props="defaultProps"
                       @node-click="handleNodeClick"
                       class="input-tree">
              </el-tree>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="saveOrUpdate">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./task.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

