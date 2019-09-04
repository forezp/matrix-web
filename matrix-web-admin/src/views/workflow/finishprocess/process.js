import {processList, updateState, updateCategory} from '@/api/workflow/process'
import {categoryList} from '@/api/workflow/category'
import { finishTaskList } from '@/api/workflow/task'
export default {
  data() {
    return {
      isUpdate: false,
      formVisible: false,
      groupIdInputDisabled: false,
      formTitle: '添加任务',
      isAdd: true,
      form: {
        id: '',
        category: '',
        procDefId: ''
      },
      listCategoryQuery: {
        page: 1,
        pageSize: 100
      },
      rules: {
        categoryId: [
          {required: true, message: '请输入分类ID', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        categoryName: [
          {required: true, message: '请输入分类名', trigger: 'blur'}
        ]

      },
      showTree: false,
      defaultProps: {
        id: 'id',
        label: 'categoryName',
        children: 'children'
      },
      listQuery: {
        category: undefined,
        page: 1,
        pageSize: 100
      },
      listPCategoryQuery: {
        pCategoryId: 0,
        page: 1,
        pageSize: 100
      },
      totalCount: 0,
      list: null,
      categoryData: null,
      listLoading: true,
      selRow: {},
      radio: '2',
      pCategoryIdDisabled: false,
      rationDisabled: false
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
      this.fetchPCategoryData()
    },
    fetchData() {
      this.listLoading = true
      finishTaskList(this.listQuery).then(response => {
        this.list = response.data.tasks
        this.listLoading = false
        this.totalCount = response.data.totalCount
      })
    },
    fetchPCategoryData() {
      categoryList(this.listCategoryQuery).then(response => {
        this.categoryData = response.data.list
      })
    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.category = data.categoryId
      this.showTree = false
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.categoryId = ''
      this.listQuery.pCategoryId = ''
      this.listQuery.categoryName = ''
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
      this.formTitle = '添加分类'
      this.formVisible = true
      this.isAdd = true
      this.isUpdate = false
      this.groupIdInputDisabled = false
      this.pCategoryIdDisabled = false
      this.rationDisabled = false
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
    updateCategoryVue() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updateCategory(this.form.procDefId, this.form.category).then(response => {
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
      if (this.selRow && this.selRow.processonDefinitionId) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.form.procDefId = this.selRow.processonDefinitionId
        this.formTitle = '修改分类'
        this.formVisible = true
        this.groupIdInputDisabled = true
        this.isUpdate = true
        this.pCategoryIdDisabled = true
        this.rationDisabled = true
      }
    },
    updateStateVue(data) {
      var suspend = data.suspend
      var state
      console.info(suspend)
      if (suspend) {
        state = 'active'
        this.$confirm('确定激活吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateState(state, data.processonDefinitionId).then(response => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      } else {
        state = 'suspend'
        this.$confirm('确定挂载吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateState(state, data.processonDefinitionId).then(response => {
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
