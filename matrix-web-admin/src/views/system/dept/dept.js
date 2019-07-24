import treeTable from '@/components/TreeTable'
import { list, save, del } from '@/api/system/dept'

export default {
  name: 'customTreeTableDemo',
  components: { treeTable },
  data() {
    return {
      expandAll: true,
      data: [],
      formVisible: false,
      formTitle: '',
      isAdd: false,

      showTree: false,
      defaultProps: {
        id: 'id',
        label: 'simpleName',
        children: 'children'
      },
      showLevelTree: false,
      defaultLevelProps: {
        label: 'name',
        children: 'children'
      },
      form: {
        id: '',
        simpleName: '',
        fullName: '',
        pid: '',
        sortNo: '',
        remarks: '',
        level: '',
        levelName: ''
      },
      rules: {
        simpleName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        fullName: [
          { required: true, message: '请输入编码', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        order: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ]
      },
      levelData: [
        { id: 10, level: '10', name: '总公司' },
        { id: 11, level: '11', name: '中心' },
        { id: 12, level: '12', name: '部门' },
        { id: 13, level: '13', name: '室' },
        { id: 20, level: '20', name: '分公司' },
        { id: 21, level: '21', name: '分公司中心' },
        { id: 22, level: '22', name: '分公司部门' },
        { id: 23, level: '23', name: '分公司室' }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list().then(response => {
        this.data = response.data
        this.listLoading = false
      })
    },
    handleNodeClick(data, node) {
      console.log(data)
      this.form.pid = data.id
      this.form.pname = data.simpleName
      this.showTree = false
    },
    handleLevelNodeClick(data, node) {
      console.log(data)
      this.form.level = data.level
      this.form.levelName = data.name
      this.showLevelTree = false
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
    add() {
      this.form = {}
      this.formTitle = '添加菜单'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      var self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log('form', self.form)
          // const menuData = {id:self.form.id,simpleName:self.form.simpleName,fullName:self.form.fullName,order:self.form.order,pid:self.form.pid,comments:self.form.comments}//self.form
          const menuData = self.form
          menuData.parent = null
          save(menuData).then(response => {
            console.log(response)
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            self.fetchData()
            self.formVisible = false
          })
        } else {
          return false
        }
      })
    },
    edit(row) {
      this.form = row

      if (row.parent) {
        this.form.pid = row.parent.id
        this.form.pname = row.parent.simplename
      }
      this.formTitle = '编辑部门'
      this.formVisible = true
      this.isAdd = false
    },
    remove(row) {
      this.$confirm('确定删除该记录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        del(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.fetchData()
        })
      })
    }
  }
}
