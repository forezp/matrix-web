import { getCurrentUser, getList } from '@/api/system/user'
import { postVacation } from '@/api/workflow/vacation'

export default {
  data() {
    return {
      employeeType: [],
      education: [],
      form: {
        name: '',
        userId: '',
        reason: '',
        remarks: '',
        nextUserId: '',
        approveComments: '',
        procDefKey: '',
        applyDate: +new Date(),
        processId: '',
        type: '',
        startTime: '',
        endTime: '',
        rangetime: [],
        dateLong: '',
        step: ''
      },
      listQuery: {
        page: 1,
        pageSize: 999
      },
      currentUser: {
        userId: '',
        realname: ''
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
      nextStep: 'audit1',
      approvers: Array
    }
  },
  created() {
    this.init()
  },
  methods: {
    submitForm(formName, isApprove) {
      if (isApprove === 1) {
        Object.assign(this.rules, {nextUserId: [{required: true, message: '请选择审批人!'}]});
      } else {
        Object.assign(this.rules, {nextUserId: []})
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.submit()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    submit() {
      var rangetime = this.form.rangetime
      var startTime, endTime;
      if (rangetime) {
        startTime = new Date(rangetime[0]).getTime();
        endTime = new Date(rangetime[1]).getTime();
      }
      let {step, processInstanceId, key} = this.$route.query;
      this.form.processId = processInstanceId
      this.form.procDefKey = key
      this.form.step = step
      this.form.startTime = startTime
      this.form.endTime = endTime
      this.form.userId = this.currentUser.userId
      if (step) {
        console.info(step)
      } else {
        postVacation(this.form).then(response => {
          this.$message({
            message: '提交成功',
            type: 'success'
          })
          this.$router.back()
        })
      }
    },
    getCurrentUser() {
      getCurrentUser().then(response => {
        this.currentUser = response.data
        this.form.name = response.data.realname
      })
    },
    getApproves() {
      getList(this.listQuery).then(response => {
        this.approvers = response.data.list
      })
    },
    change() {
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
        this.nextStep = 'audit1',
        this.getCurrentUser()
        this.getApproves()
      }
    }
  }
}
