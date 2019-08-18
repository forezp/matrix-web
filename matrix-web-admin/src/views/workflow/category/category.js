import {addCategory, updateCategory, categoryList} from '@/api/workflow/category'

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
        categoryId: '',
        categoryName: '',
        pCategoryId: ''
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
        categoryId: undefined,
        categoryName: undefined,
        pCategoryId: undefined,
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
      pCategoryData: null,
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
      categoryList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
        this.totalCount = response.data.totalCount
      })
    },
    fetchPCategoryData() {
      categoryList(this.listPCategoryQuery).then(response => {
        this.pCategoryData = response.data.list
      })
    },
    rationchangeHandler(value) {
      console.info(value)
      if (value === '1') {
        console.info(value)
        this.pCategoryIdDisabled = true
        this.form.pCategoryId = 0
      } else {
        this.pCategoryIdDisabled = false
        this.form.pCategoryId = ''
      }
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
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.isUpdate) {
            updateCategory({
              id: self.form.id,
              categoryName: self.form.categoryName
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
            addCategory({
              id: self.form.id,
              categoryId: self.form.categoryId,
              categoryName: self.form.categoryName,
              pCategoryId: self.form.pCategoryId
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
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '修改分类'
        this.formVisible = true
        this.groupIdInputDisabled = true
        this.isUpdate = true
        this.form.pCategoryId = this.selRow.pcategoryId
        this.pCategoryIdDisabled = true
        this.rationDisabled = true
      }
    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.pCategoryId = data.categoryId
      this.showTree = false
    }
  }
}
