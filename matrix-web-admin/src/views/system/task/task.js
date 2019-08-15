import {remove, getList, save, pauseTask, resumeTask, taskGroupList} from '@/api/system/task'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: '添加任务',
      groupData: null,
      isAdd: true,
      combineId: {
        taskClassName: '',
        taskGroupId: ''
      },
      defaultProps: {
        label: 'groupName',
        children: 'children'
      },
      showTree: false,
      form: {
        id: '',
        taskName: '',
        taskClassName: '',
        taskGroupId: '',
        cronExpression: '',
        taskGroupName: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入任务名', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        jobClass: [
          {required: true, message: '请输入执行类', trigger: 'blur'}
        ],
        cron: [
          {required: true, message: '请输入定时规则', trigger: 'blur'}
        ]

      },
      listQuery: {
        taskClassName: undefined,
        taskGroupId: undefined,
        page: 1,
        pageSize: 10
      },
      groupListQuery: {
        page: 1,
        pageSize: 100
      },
      total: 0,
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
      this.fetchGroupData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
      })
    },
    fetchGroupData() {
      taskGroupList(this.groupListQuery).then(response => {
        this.groupData = response.data.list
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.taskGroupId = data.groupId
      this.form.taskGroupName = data.groupName
      this.showTree = false
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
    },
    saveOrUpdate(){
      if(this.isAdd){
        this.save()
      }else {
        this.update()
      }
    },
    update(){
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: self.form.id,
            taskName: self.form.taskName,
            taskClassName: self.form.taskClassName,
            taskGroupId: self.form.taskGroupId,
            cronExpression: self.form.cronExpression,
          }).then(response => {
            console.log(response)
            this.$message({
              message: '更新成功',
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
        } else {
          return false
        }
      })
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          save({
            id: self.form.id,
            taskName: self.form.taskName,
            taskClassName: self.form.taskClassName,
            taskGroupId: self.form.taskGroupId,
            cronExpression: self.form.cronExpression,
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
          return false
        }
      })
    },
    checkSel() {
      if (this.selRow && this.selRow.triggerName && this.selRow.triggerGroup) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    resume(row) {
      this.combineId.taskClassName = row.triggerName
      this.combineId.taskGroupId = row.triggerGroup
      this.$confirm('确定启用该定时任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        resumeTask(this.combineId).then(response => {
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    },
    pause(row) {
      this.combineId.taskClassName = row.triggerName
      this.combineId.taskGroupId = row.triggerGroup
      this.$confirm('确定停止该定时任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        pauseTask(this.combineId).then(response => {
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    },
    viewLog(taskId) {
      this.$router.push({path: '/system/taskLog', query: {taskId: taskId}})
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form.taskName = this.selRow.triggerSimpleName
        this.form.taskGroupId = this.selRow.triggerGroup
        this.form.taskGroupName=this.selRow.trifggerGroupName
        this.form.taskClassName = this.selRow.triggerName
        this.form.cronExpression = this.selRow.cronExpression
        this.formTitle = '修改任务'
        this.formVisible = true
      }
    },
    remove() {
      if (this.checkSel()) {
        this.combineId.taskClassName = this.selRow.triggerName
        this.combineId.taskGroupId = this.selRow.triggerGroup
        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(this.combineId).then(response => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    }

  }
}
