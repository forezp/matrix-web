import { deleteUser, getList, saveUser, remove, setRole } from '@/api/system/user'
import { list as deptList } from '@/api/system/dept'
import { parseTime } from '@/utils/index'
import { roleTreeListByIdUser } from '@/api/system/role'
// 权限判断指令
import permission from '@/directive/permission/index.js'

export default {
  directives: { permission },
  data() {
    return {
      roleDialog: {
        visible: false,
        roles: [],
        roleTree: [],
        checkedRoleKeys: [],
        defaultProps: {
          id: 'id',
          label: 'name',
          children: 'children'
        }
      },
      formVisible: false,
      formTitle: '添加用户',
      deptTree: {
        show: false,
        data: [],
        defaultProps: {
          id: 'id',
          label: 'simpleName',
          children: 'children'
        }
      },
      isAdd: true,
      form: {
        id: '',
        userId: '',
        realname: '',
        birthday: '',
        sex: 1,
        email: '',
        password: '',
        orgId: '',
        status: 1,
        deptid: 1,
        simpleName: '',
        mobile: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入登录账号', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入email', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1,
        pageSize: 20,
        userId: undefined,
        realname: undefined
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
      deptList().then(response => {
        this.deptTree.data = response.data
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.list
        this.listLoading = false
        this.total = response.data.totalCount
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.account = ''
      this.listQuery.name = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

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
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        id: '',
        userId: '',
        realname: '',
        birthday: '',
        sex: 1,
        email: '',
        password: '',
        rePassword: '',
        simpleName: '',
        status: true,
        orgId: 1,
        mobile: ''
      }
    },
    add() {
      this.resetForm()
      this.formTitle = '添加用户'
      this.formVisible = true
      this.isAdd = true
    },
    validPasswd() {
      if (!this.isAdd) {
        return true
      }
      if (this.form.password !== this.form.rePassword) {
        return false
      }
      if (this.form.password === '' || this.form.rePassword === '') {
        return false
      }
      return true
    },
    saveUser() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.validPasswd()) {
            var form = self.form
            if (form.status === true) {
              //启用
              form.status = 1
            } else {
              //冻结
              form.status = 2
            }
            // form.birthday = parseTime(form.birthday, '{y}-{m}-{d}')
            form.createtime = parseTime(form.createtime)
            saveUser(form).then(response => {
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.fetchData()
              this.formVisible = false
            })
          } else {
            this.$message({
              message: '提交失败',
              type: 'error'
            })
          }
        } else {
          console.log('error submit!!')
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
        this.form.status = this.selRow.statusName === '启用'
        this.form.password = ''
        this.formTitle = '修改用户'
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
              message: '删除成功',
              type: 'success'
            })
            this.fetchData()
          }).catch( err=>{
            this.$notify.error({
              title: '错误',
              message:err,
            })
          })
        }).catch(() => {
        })
      }
    },
    handleNodeClick(data, node) {
      this.form.orgId = data.orgId
      this.form.simpleName = data.simpleName
      this.deptTree.show = false
    },

    openRole() {
      if (this.checkSel()) {
        roleTreeListByIdUser(this.selRow.userId).then(response => {
          this.roleDialog.roles = response.data.roleDTOList
          this.roleDialog.checkedRoleKeys = response.data.selectIds
          this.roleDialog.visible = true
        })
      }
    },
    setRole() {
      var checkedRoleKeys = this.$refs.roleTree.getCheckedKeys()
      var roleIds = ''
      for (var index in checkedRoleKeys) {
        roleIds += checkedRoleKeys[index] + ','
      }
      var data = {
        userId: this.selRow.userId,
        roleIds: roleIds
      }
      setRole(data).then(response => {
        this.roleDialog.visible = false
        this.fetchData()
        if (response.code !== 0){
          this.$message({
            message: response.message,
            type: 'warning'
          })
        }else {
          this.$message({
            message: '提交成功',
            type: 'success'
          })
        }
      })
    }

  }
}
