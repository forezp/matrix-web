import { getList, save} from '@/api/workflow/model'
export default {
  data() {
    return {
      showTree: false,
      formVisible: false,
      formTitle: '添加字典',
      isAdd: true,
      form: {
        id: '',
        name: '',
        category: '',
        key: '',
        desc: '',
        version: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入字典名称', trigger: 'blur'},
          {min: 1, max: 50, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ],
        key: [
          {required: true, message: '请输入字典名称', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
        ]
      },
      defaultProps: {
        label: 'typeName',
        children: 'children'
      },
      typeListQuery: {
        page: 1,
        pageSize: 100
      },
      listQuery: {
        page: 1,
        pageSize: 10,
        category: undefined
      },
      list: null,
      typeData: null,
      listLoading: true,
      selRow: {},
      totalCount: undefined,
      href: '',
      adminApi: process.env.ADMIN_API

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
      }).catch(() => {
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.codeId = ''
      this.listQuery.codeName = ''
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
        id: '',
        codeId: '',
        codeName: '',
        typeId: '',
        typeName: '',
        sort: '',
        remarks: ''
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
          if (this.form.id !== '') {
            update(this.form.id,this.form).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              self.fetchData()
              self.formVisible = false
            })
          } else {
            save(this.form).then(response => {
              let location = response.data;
              // window.open(`https://taichi-dev.xiaoantimes.com/static/activiti${location}`);
              // window.location.href = `https://taichi-dev.xiaoantimes.com/static/activiti${location}`;
              this.href = `/static/activiti${location}`;
              this.$nextTick(() => {
                var a = this.$refs.a;
                var e = document.createEvent('MouseEvents');
                e.initEvent('click', true, true);
                a.dispatchEvent(e);
              });
              this.$router.back();

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
        this.formTitle = '修改字典'
        this.form = this.selRow
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
    handleNodeClick(data, node) {
      this.form.typeId = data.typeId
      this.form.typeName = data.typeName
      this.showTree = false
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
