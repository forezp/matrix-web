import { getTriggersHistory} from '@/api/system/task'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: '添加任务',
      isAdd: true,
      form: {
        id: '',
        groupId: '',
        triggerName: ''
      },
      rules: {
        groupId: [
          { required: true, message: '请输入任务名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        triggerName: [
          { required: true, message: '请输入执行类', trigger: 'blur' }
        ]

      },
      listQuery: {
        groupId: undefined,
        triggerName: undefined,
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
      getTriggersHistory(this.listQuery).then(response => {
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
    }
  }
}
