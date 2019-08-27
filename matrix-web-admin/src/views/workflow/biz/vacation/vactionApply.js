export default {
  data() {
    return {
      employeeType: [],
      education: [],
      form: {
        name: '',
        reason: '',
        remarks: '',
        nextUserId: '',
        approveComments: '',
        procDefKey: '',
        applyDate: +new Date(),
        employeeId: '',
        employeeName: '',
        orgId: '',
        orgName: '',
        jobId: '',
        jobName: '',
        processId: '',
        type: '',
        startTime: '',
        endTime: '',
        rangetime: [],
        dateLong: ''

      },
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
    }
  },
  created() {
    this.init()
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
    },
    init() {
      var step = this.$route.query.step
      if (step) {
        if (step === 'audit1') {
          this.disabledRangetime = true
          this.disabledVacationType = true
          this.disabledReason = true,
          this.disabledRemarks = true,
          this.showApprover = true,
          this.showApproveComment = true,
          this.showSubmitButton = false,
          this.showApproveButton = true,
          this.showReApplyButton = false,
          this.showSaveButton = false,
          this.nextStep = 'audit2'
        } else if (step === 'audit2') {
          this.disabledRangetime = true,
          this.disabledVacationType = true,
          this.disabledReason = true,
          this.disabledRemarks = true,
          this.showApprover = false,
          this.showApproveComment = false,
          this.showSubmitButton = false,
          this.showApproveButton = false,
          this.showSaveButton = true,
          this.showReApplyButton = false
        } else if (step === 'todetail') {
        }
      } else {
        this.disabledRangetime = false,
        this.disabledVacationType = false,
        this.disabledReason = false,
        this.disabledRemarks = false,
        this.showApprover = true,
        this.showApproveComment = false,
        this.showSubmitButton = true,
        this.showApproveButton = false,
        this.showReApplyButton = false,
        this.showSaveButton = false,
        this.nextStep = 'audit1'
      }
    }
  }
}
