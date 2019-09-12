import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'dashboard',
    hidden: false,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
    },
    {
      path: '/account/updatePwd',
      name: '修改密码',
      component: () => import('@/views/account/updatePwd.vue'),
      hidden: true,
      meta: { title: '修改密码' }

    }
    ]
  },
  { path: '/404', component: () => import('@/views/404'), hidden: true }

]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    redirect: '#',
    name: 'System',
    alwaysShow: true,
    meta: {
      roles: ['administrator'],
      title: 'systemMgr',
      icon: 'table'
    },
    children: [
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('@/views/system/menu/index'),
        meta: {
          title: 'menuMgr'
        }
      },
      {
        path: 'dept',
        name: 'Department',
        component: () => import('@/views/system/dept/index'),
        meta: {
          title: 'deptMgr'
        }
      },
      {
        path: 'mgr',
        name: 'Account',
        component: () => import('@/views/system/user/index'),
        meta: {title: 'userMgr' }
      },
      {
        path: 'role',
        name: 'roleMgr',
        component: () => import('@/views/system/role/index'),
        meta: { title: 'roleMgr' }
      },
      {
        path: 'dict',
        name: 'Dict',
        component: () => import('@/views/system/dict/index'),
        meta: { title: 'dictMgr' }
      },
      {
        path: 'dicttype',
        name: 'DictType',
        component: () => import('@/views/system/dicttype/index'),
        meta: { title: 'dictTypeMgr' }
      },
      {
        path: 'cfg',
        name: 'Config',
        component: () => import('@/views/system/cfg/index'),
        meta: {
          title: 'configMgr'
        }
      }
    ]
  },
  {
    path: '/workflow',
    component: Layout,
    redirect: '#',
    name: 'Workflow',
    alwaysShow: true,
    meta: {
      roles: ['administrator', 'developer'],
      title: 'workflowMgr',
      icon: 'documentation'
    },
    children: [
      {
        path: 'modelx',
        name: 'Modelx',
        component: () => import('@/views/workflow/modelx/index'),
        meta: { title: 'modelxMgr' }
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/workflow/category/index'),
        meta: { title: 'categoryMgr' }
      },
      {
        path: 'process',
        name: 'Process',
        component: () => import('@/views/workflow/process/index'),
        meta: { title: 'processMgr' }
      },
      {
        path: 'newProcess',
        name: 'NewProcess',
        component: () => import('@/views/workflow/newprocess/index'),
        meta: { title: 'newProcessMgr' }
      },
      {
        path: 'commonvaction',
        name: 'VactionApply',
        component: () => import('@/views/workflow/biz/vacation/index'),
        hidden: true,
        meta: { title: 'vactionApplyMgr' }
      },
      {
        path: 'myprocess',
        name: 'myprocess',
        component: () => import('@/views/workflow/myprocess/index'),
        meta: { title: 'myprocessMgr' }
      },
      {
        path: 'todoprocess',
        name: 'todoprocess',
        component: () => import('@/views/workflow/todoprocess/index'),
        meta: { title: 'todpprocessMgr' }
      },
      {
        path: 'finishprocess',
        name: 'finishprocess',
        component: () => import('@/views/workflow/finishprocess/index'),
        meta: { title: 'finishprocessMgr' }
      }
    ]
  },
  {
    path: '/task',
    component: Layout,
    redirect: '#',
    name: 'Task',
    alwaysShow: true,
    meta: {
      roles: ['administrator', 'developer'],
      title: 'taskMgr',
      icon: 'documentation'
    },
    children: [
      {
        path: 'task',
        name: 'Task',
        component: () => import('@/views/system/task/index'),
        meta: { title: 'taskMgr' }
      },
      {
        path: 'taskGroup',
        name: 'TaskGroup',
        component: () => import('@/views/system/taskgroup/index'),
        meta: { title: 'taskGroupMgr' }
      },
      {
        path: 'taskhistory',
        name: 'taskhistory',
        component: () => import('@/views/system/taskhistory/index.vue'),
        hidden: false,
        meta: { title: 'taskhistory' }
      }
    ]
  },
  // {
  //   path: '/cms',
  //   component: Layout,
  //   redirect: '#',
  //   name: 'Cms',
  //   alwaysShow: true,
  //   meta: {
  //     roles: ['administrator', 'developer'],
  //     title: 'cmsMgr',
  //     icon: 'documentation'
  //   },
  //   children: [
  //     {
  //       path: 'banner',
  //       name: 'Banner',
  //       component: () => import('@/views/cms/banner/index'),
  //       meta: { title: 'bannerMgr' }
  //     },
  //     {
  //       path: 'channel',
  //       name: 'Channel',
  //       component: () => import('@/views/cms/channel/index'),
  //       meta: { title: 'channelMgr' }
  //     },
  //     {
  //       path: 'article',
  //       name: 'Article',
  //       component: () => import('@/views/cms/article/index'),
  //       meta: { title: 'articleMgr' }
  //     },
  //     {
  //       path: 'article/edit',
  //       name: 'Edit Article',
  //       component: () => import('@/views/cms/article/edit.vue'),
  //       hidden: true,
  //       meta: { title: 'editArticle' }
  //     },
  //     {
  //       path: 'contacts',
  //       name: 'Contacts',
  //       component:() => import('@/views/cms/contacts/index'),
  //       meta: { title: 'contactsMgr' }
  //     },
  //     {
  //       path: 'file',
  //       name: 'File',
  //       component:  () => import('@/views/cms/file/index'),
  //       meta: {
  //         title: 'fileMgr'
  //       }
  //     }
  //   ]
  // },
  {
    path: '/optionMgr',
    component: Layout,
    redirect: '#',
    name: 'optionMgr',
    alwaysShow: true,
    meta: {
      roles: ['administrator', 'developer'],
      title: 'optionMgr',
      icon: 'example'
    },
    children: [
      {
        path: 'druid',
        name: 'druid',
        component:() => import('@/views/operation/druid/index'),
        meta: { title: 'druid' }
      },
      {
        path: 'swagger',
        name: 'swagger',
        component:() => import('@/views/operation/api/index'),
        meta: { title: 'swagger' }
      },
      {
        path: 'loginLog',
        name: 'Login Log',
        component: () => import('@/views/system/loginLog/index'),
        meta: { title: 'loginLog' }
      },
      {
        path: 'log',
        name: 'Bussiness Log',
        component: () => import('@/views/system/log/index'),
        meta: { title: 'bussinessLog' }
      }
    ]
  },
  { path: '/404', component: () => import('@/views/404'), hidden: true }
]

