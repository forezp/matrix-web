const CONFIG = {
  'launch': {
    disabledRangetime: false,
    disabledVacationType: false,
    disabledReason: false,
    disabledRemarks: false,
    showApprover: true,
    showApproveComment: false,
    showSubmitButton: true,
    showApproveButton: false,
    showReApplyButton: false,
    showSaveButton: false,
    nextStep: 'audit1'
  },
  'audit1': {
    disabledRangetime: true,
    disabledVacationType: true,
    disabledReason: true,
    disabledRemarks: true,
    showApprover: true,
    showApproveComment: true,
    showSubmitButton: false,
    showApproveButton: true,
    showReApplyButton: false,
    showSaveButton: false,
    nextStep: 'audit2'
  },
  'audit2': {
    disabledRangetime: true,
    disabledVacationType: true,
    disabledReason: true,
    disabledRemarks: true,
    showApprover: false,
    showApproveComment: false,
    showSubmitButton: false,
    showApproveButton: false,
    showSaveButton: true,
    showReApplyButton: false
  },
  // 此流程'audit3'为重新申请节点
  'audit3': {
    disabledRangetime: false,
    disabledVacationType: false,
    disabledReason: false,
    disabledRemarks: false,
    showApprover: true,
    showApproveComment: false,
    showSubmitButton: false,
    showApproveButton: false,
    showReApplyButton: true,
    showSaveButton: false,
    nextStep: 'audit1'
  },
  'todetail': {
    disabledRangetime: true,
    disabledVacationType: true,
    disabledReason: true,
    disabledRemarks: true,
    showApprover: false,
    showApproveComment: false,
    showSubmitButton: false,
    showApproveButton: false,
    showReApplyButton: false,
    showSaveButton: false
  }
}

export default {
  props: {
    form: Object,
    orgTree: Array,
    posTree: Array,
    disabled: Boolean,
    config: Object,
    approvers: Array
  },
  data() {
    return {
      formConfig: undefined,
      employeeType: [],
      education: [],
      rules: {
        rangetime: [
          {required: true, message: '请选择请假时间!'}
        ],
        reason: [
          {required: true, message: '请输入请假原因!'}
        ],
        remarks: [
          {required: true, message: '请输入备注!'}
        ],
        type: [
          {required: true, message: '请选择请假类型!'}
        ]
      },
      errorAlert: false
    }
  },
  methods: {
    change() {
      this.$emit('timeChange')
    },
    submitForm(formName, isApprove) {
      if (isApprove === 1) {
        Object.assign(this.rules, {nextUserId: [{required: true, message: '请选择审批人!'}]});
      } else {
        Object.assign(this.rules, {nextUserId: []})
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.errorAlert = false
          this.$emit('submit', isApprove)
        } else {
          console.log('error submit!!')
          this.errorAlert = true
          return false;
        }
      })
    }
  },
  created() {
  },
  beforeRouteEnter(to, from, next) {
    let {uid, key, step, processInstanceId} = to.query;
    if (step) {
      if (step === 'audit1') {
        this.formConfig = CONFIG[step]
      } else if (step === 'audit2') {
        this.formConfig = CONFIG[step]
      } else if (step === 'audit3') {
        this.formConfig = CONFIG[step]
      } else if (step === 'todetail') {
        this.formConfig = CONFIG[step]
      }
    } else {
      this.formConfig = CONFIG.launch
    }
  }
}
