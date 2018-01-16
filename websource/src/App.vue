<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-left">
          <span style="display: block;height: 40px;width: 390px;text-align: center;line-height: 40px;font-size: 40px;">欢迎来到叮当家</span>
          <!--<img src="./assets/washmore.png">-->
        </div>
        <div class="header-right">
          <el-button type="primary" round icon="el-icon-message">
            洗澡狂魔
          </el-button>
          <el-button round>登出</el-button>
        </div>
      </el-header>

      <el-container>
        <el-aside width="200px">
          <el-menu :default-openeds="['1','2']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>账单管理</template>
              <router-link to="/bill/add">
                <el-menu-item index="1-1">添加账单</el-menu-item>
              </router-link>
              <router-link to="/bill/manage">
                <el-menu-item index="1-2">账单列表</el-menu-item>
              </router-link>

              <el-menu-item index="1-3">草稿箱</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-setting"></i>资料维护</template>
              <el-menu-item index="2-1">成员信息</el-menu-item>
              <el-menu-item index="2-2">账单类型</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-message"></i>demo</template>
              <router-link to="/Demo">
                <el-menu-item index="3-1">
                  <i class="el-icon-setting"></i>
                  <span slot="title">杂例</span>
                </el-menu-item>
              </router-link>
              <el-menu-item index="3-2" @click="chooseToShow">
                <i class="el-icon-setting"></i>
                <span slot="title">路由</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
/*eslint-disable*/
  export default {
    name: 'demo',
    data() {
      return {}
    },
    methods: {
      chooseToShow() {
        this.$prompt('请输入需要查看的用户名', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^.+$/,
          inputErrorMessage: '用户名不能为空'
        }).then(({value}) => {
          this.$router.push({name: 'RouterDemoOne', params: {'userName': value}});
        }).catch((e) => {
          console.error(e)
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      }
    }
  }
</script>
<style>
  .el-header {
    top: 0px;
    left: 0px;
    z-index: 99;
    width: 100%;
    position: fixed;
  }

  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    height: 60px;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    position: fixed;
    margin-top: 60px;
    z-index: 98;
    left: 0px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    display: block;
    margin-top: 60px;
    margin-left: 200px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .header-right {
    float: right;
  }

  .header-left {
    float: left;
    margin-top: 10px;
  }
</style>
