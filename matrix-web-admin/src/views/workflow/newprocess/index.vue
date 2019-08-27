<template>
  <div style="padding: 15px;">
    <div class="process-wrapper">
      <el-card :body-style="{ padding: '0px',overflow: 'hidden' }" class="card-body" v-for="(list,index) in processList" :key="index" @click.native="launch(list)">
        <div class="left">
          <p class="title">{{ list.name[0]}}</p>
        </div>
        <div class="right">
          <p class="process-name">{{list.name}}</p>
          <p class="process-desc">描述</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { processList } from '@/api/workflow/process'
const COLORS = ['#b84592', '#ff4f81', '#ff6c5f', '#ffc168', '#2dde98', '#1cc7d0']
export default {
  data() {
    return {
      listQuery: {
        category: undefined,
        page: 1,
        pageSize: 100
      },
      processList: []
    }
  },
  methods: {
    launch(list) {
      this.$router.push({ path: `/workflow/${list.key}`, query: {uid: this.uid, key: list.key}})
    },
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      processList(this.listQuery).then(response => {
        this.processList = response.data.list
        this.listLoading = false
        this.totalCount = response.data.totalCount
        this.processList.forEach((item) => {
          var random = Math.floor(Math.random() * COLORS.length)
          item.color = COLORS[random]
        })
      })
    }
  },
  created() {
    this.init()
  }
//   beforeRouteEnter(to, from, next) {
//     var requests = DICTMAPS.map(item => DictService.get(item));
//     requests.push(Process.query({ page: 1, pageSize: 999 }));
//     Promise.all(requests).then(([processCategory, processList]) => {
//       next(vm => {
//         let processCategorys = processCategory.data.data.library;
// //      this.processCategorys = processCategory.data.data.library;
//
//         let resdata = processList.data;
//         let list = resdata.data.list;
//         list.forEach((item) => {
//
//           let random = Math.floor(Math.random() * COLORS.length);
//           item.color = COLORS[random];
//
//           if (!processCategorys[item.category].list) {
//             processCategorys[item.category].list = [];
//           }
//           processCategorys[item.category].list.push(item);
//
//         });
//         vm.processList = processCategorys;
//         vm.$nextTick(() => vm.$emit('loaded'));
//       });
//     });
}
</script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
.levelbar{
  height: 64px;
  line-height: 64px;
}
.process-wrapper {
  overflow: hidden;
  .tips {
    height: 40px;
    line-height: 40px;
    color: #b84592
  }
  .card-body {
    width: 300px;
    float: left;
    margin: 10px;
    cursor: pointer;
    .left {
      float: left;
      width: 100px;
      height: 100px;
      padding: 10px;
      // background-image: linear-gradient(90deg,#0af,#0085ff);
      background: rgba(80, 191, 255, 0.92);
      .title {
        width: 100%;
        height: 100%;
        text-align: center;
        font-size: 30px;
        color: white;
      }
    }
    .right {
      width: 160px;
      height: 100px;
      float: left;
      padding: 10px;
      position: relative;
      background: rgba(80, 191, 12, 0.92);
      .process-name {
        font-size: 16px;
        height: 24px;
        line-height: 24px;
      }
      .process-desc {
        height: 24px;
        line-height: 24px;
      }
      .action {
        position: absolute;
        right: 10px;
        bottom: 10px;
      }
    }
  }
}
</style>
