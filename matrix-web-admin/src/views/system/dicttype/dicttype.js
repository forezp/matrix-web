import {remove, getList, save, update} from '@/api/system/dicttype'

export default {
  data() {
    return {
      formVisible: false,
      formTitle: '添加字典分类',
      isAdd: true,
      permissonVisible: false,
      form: {
        id: '',
        typeId: '',
        typeName: '',
        typeDescribe: '',
        remarks: ''
      },
      rules: {
        typeId: [
          {required: true, message: '请输入字典名称', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ],
        typeName: [
          {required: true, message: '请输入字典名称', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ]
      },
      listQuery: {
        typeId: undefined,
        typeName: undefined,
        page: 1,
        pageSize: 10
      },
      list: null,
      totalCount: undefined,
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
      getList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
        this.totalCount=response.data.totalCount
      }).catch(() => {
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.typeId = ''
      this.listQuery.typeName = ''
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
      this.form = {
        name: '',
        id: '',
        details: [],
        detail: []

      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加字典'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var id=self.form.id
          var typeId = self.form.typeId
          var typeName = self.form.typeName
          var typeDescribe = self.form.typeDescribe
          var remarks = self.form.remarks
          if (this.form.id !== '') {
            update({id: id, typeName: typeName,typeDescribe: typeDescribe,remarks: remarks }).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
            })
          } else {
            save({typeId: typeId, typeName: typeName,typeDescribe: typeDescribe,remarks: remarks }).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
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
        this.form=this.selRow
        this.formTitle = '修改字典'
        this.formVisible = true
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
    addDetail() {
      var details = this.form.details

      details.push({
        value: '',
        key: ''
      })
      this.form.details = details
    },
    removeDetail(detail) {
      var details = []
      this.form.details.forEach(function (val, index) {
        if (detail.key !== val.key) {
          details.push(val)
        }
      })
      this.form.details = details
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
    }
  }
}
