<template>
  <el-form ref="form" :inline="true" :model="form" :rules="rules" label-width="120px" class="works-flows-form">
    <h2 class="title">
      休假申请
    </h2>
    <div class="works-flows-form-container">
      <el-form-item class="form-item col-5" label="姓名:" prop="userId">
        <el-input disabled class="item" v-model="form.name"/>
      </el-form-item>
      <el-form-item class="form-item col-5" label="提交日期:">
        <el-date-picker disabled class="item" v-model="form.applyDate" ></el-date-picker>
      </el-form-item>
      <el-form-item class="form-item col-10" label="请假日期:" prop="rangetime">
        <el-date-picker
          class="item"
          :disabled="config.disabledRangetime"
          v-model="form.rangetime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="change"
        >
        </el-date-picker >
      </el-form-item>
      <el-form-item class="form-item col-10" label="实际请假天数:" prop="dateLong">
        <el-input  disabled class="item"  v-model="form.dateLong+'天'" ></el-input>
      </el-form-item>
      <el-form-item  class="form-item col-10" label="假期类型:" prop="type">
        <el-select :disabled="config.disabledVacationType"  class="item" v-model="form.type" placeholder="请选择请假类型">
          <el-option :label="item.codeName" :value="item.codeId" :key="item.codeId" v-for="item in vacationType"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="form-item col-10" label="请假原因:" prop="reason">
        <el-input :disabled="config.disabledReason" class="item" type="textarea" v-model="form.reason" ></el-input>
      </el-form-item>
      <el-form-item class="form-item col-10" label="备注:" prop="remarks">
        <el-input :disabled="config.disabledRemarks" class="item" type="textarea" v-model="form.remarks" ></el-input>
      </el-form-item>
      <el-form-item class="form-item col-10" label="请选择审批人:" prop="nextUserId" v-if="config.showApprover">
        <el-select class="item" v-model="form.nextUserId" placeholder="请选择审批人">
          <el-option
            v-for="item in approvers"
            :key="item.userId"
            :label="item.realname"
            :value="item.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="form-item col-10" label="审批意见:" v-show="config.showApproveComment">
        <el-input class="item" type="textarea" v-model="form.approveComments"></el-input>
      </el-form-item>
    </div>
    <el-form-item style="margin-top: 20px;float:right;">
      <el-button v-if="config.showSubmitButton" type="primary" @click="submitForm('form',1)">提交</el-button>
      <el-button v-if="config.showApproveButton" type="primary" @click="submitForm('form', 1)">同意</el-button>
      <el-button v-if="config.showSaveButton" type="primary" @click="submitForm('form', 1)">存档</el-button>
      <el-button v-if="config.showApproveButton" type="danger" @click="submitForm('form', 0)">不同意</el-button>
      <el-button v-if="config.showReApplyButton" type="primary" @click="submitForm('form', 1)">重新提交</el-button>
      <el-button v-if="config.showReApplyButton" type="danger" @click="submitForm('form', 0)">放弃申请</el-button>
      <el-button @click="$router.back()">取消</el-button>
    </el-form-item>
    <el-alert
      v-show="errorAlert"
      title="资料未填写完整"
      type="error"
      show-icon
      @close="errorAlert=false">
    </el-alert>
  </el-form>
</template>

<script src="./vactionApply.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
  .form-wrapper {
    .form {
      width: 824px;
      margin: auto;
      .title{
        text-align: center;
        line-height: 60px;
      }
      .tips {
        height: 40px;
        color: #9ea7b4;
        font-size: 12px;
      }
    }
  }
</style>
