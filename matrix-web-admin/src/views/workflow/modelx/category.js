import {getList, save, updateCategory, remove, deploy } from '@/api/workflow/model'
import {categoryList} from '@/api/workflow/category'

export default {
  data() {
    return {
      isUpdate: false,
      formVisible: false,
      groupIdInputDisabled: false,
      formTitle: '添加任务',
      isAdd: true,
      showTree: false,
      defaultProps: {
        id: 'id',
        label: 'categoryName',
        children: 'children'
      },
      listCategoryQuery: {
        page: 1,
        pageSize: 100
      },
      form: {
        id: '',
        category: '',
        name: '',
        desc: '',
        key: ''
      },
      rules: {
        category: [
          {required: true, message: '请输入分类ID', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入分类名', trigger: 'blur'}
        ]

      },
      listQuery: {
        category: undefined,
        page: 1,
        pageSize: 100
      },
      totalCount: 0,
      list: null,
      categoryData: null,
      listLoading: true,
      selRow: {},
      href: ''
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
      getList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
        this.totalCount = response.data.totalCount
      })
    },
    fetchPCategoryData() {
      categoryList(this.listCategoryQuery).then(response => {
        this.categoryData = response.data.list
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
      this.formTitle = '添加流程'
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
            updateCategory({
              id: self.form.id,
              category: self.form.category
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
            save(this.form).then(response => {
              console.log(response)
              var location = response.data
              this.href = `/static/activiti${location}`
              this.$nextTick(() => {
                var a = this.$refs.a
                var e = document.createEvent('MouseEvents')
                e.initEvent('click', true, true)
                a.dispatchEvent(e)
              })
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
    handleNodeClick(data, node) {
      console.log(data)
      this.form.category = data.categoryId
      this.showTree = false
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '修改分类'
        this.formVisible = true
        this.groupIdInputDisabled = true
        this.isUpdate = true
      }
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id
        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          remove(id).then(response => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    },
    deploy(data) {
      var id = data.id
      this.$confirm('确定部署吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deploy(id).then(response => {
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
