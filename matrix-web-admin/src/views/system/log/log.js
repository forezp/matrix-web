import {clear, getList} from '@/api/system/log'

export default {
  data() {
    return {
      options: [{
        value: 'GET',
        label: 'GET请求'
      }, {
        value: 'POST',
        label: 'POST请求'
      }, {
        value: 'DELETE',
        label: 'DELETE请求'
      }, {
        value: 'PUT',
        label: 'PUT请求'
      }
      ],
      form: {
        name: '',
        id: ''
      },
      listQuery: {
        page: 1,
        pageSize: 10,
        beginTime: undefined,
        endTime: undefined,
        createBy: undefined,
        method: undefined
      },
      totalCount: 0,
      list: null,
      listLoading: true,
      selRow: {}
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
        this.totalCount = response.data.totalCount
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.beginTime = ''
      this.listQuery.endTime = ''
      this.listQuery.logName = ''
      this.listQuery.logType = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
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
    clear() {
      this.$confirm('确定清空数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids = ''
        for (var key in this.list) {
          var item = this.list[key]
          ids += item['id'] + '-'
        }
        console.log(ids)
        clear(ids).then(response => {
          this.$message({
            message: '清空成功',
            type: 'sucess'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    }

  }
}
