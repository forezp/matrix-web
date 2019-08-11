import {addTaskGroup, updateTaskGroup, taskGroupList} from '@/api/system/task'

export default {
  data() {
    return {
      isUpdate: false,
      formVisible: false,
      groupIdInputDisabled: false,
      formTitle: '添加任务',
      deptList: [],
      isAdd: true,
      form: {
        id: '',
        groupId: '',
        groupName: ''
      },
      rules: {
        groupId: [
          { required: true, message: '请输入任务名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        groupName: [
          { required: true, message: '请输入执行类', trigger: 'blur' }
        ]

      },
      listQuery: {
        groupId: undefined,
        groupName: undefined,
        page: 1,
        pageSize: 100,
      },
      totalCount: 0,
      list: null,
      listLoading: true,
      selRow: {}
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      taskGroupList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
        this.totalCount = response.data.totalCount
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.groupName = ''
      this.listQuery.groupId = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {}
    },
    add() {
      this.resetForm()
      this.formTitle = '添加任务'
      this.formVisible = true
      this.isAdd = true
      this.isUpdate = false
      this.groupIdInputDisabled = false
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(pageSize) {
      this.listQuery.pageSize = pageSize
      this.fetchData()
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.isUpdate) {
            updateTaskGroup({
              id: self.form.id,
              groupId: self.form.groupId,
              groupName: self.form.groupName
            }).then(response => {
              console.log(response)
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          } else {
            addTaskGroup({
              id: self.form.id,
              groupId: self.form.groupId,
              groupName: self.form.groupName
            }).then(response => {
              console.log(response)
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          }
        } else {
          return false
        }
      })
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    viewLog(taskId) {
      this.$router.push({path: '/system/taskLog', query: {taskId: taskId}})
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '修改任务'
        this.formVisible = true
        this.groupIdInputDisabled = true
        this.isUpdate = true
      }
    }
  }
}
