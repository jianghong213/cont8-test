<template>
  <el-container class="home-container">
    <!--顶部-->
    <el-header style="margin-right: 15px; width: 100%">
      <span class="nav-logo"><img src="../assets/img/logo.png"></span>
      <span class="head-title">CONT8 ONLINE BOOKSHOP</span>
      <!-- <el-avatar
        icon="el-icon-user-solid"
        style="color: #222; float: right; padding: 20px"
        >{{ this.$store.state.user.userName }}</el-avatar
      > -->
    </el-header>
    <!-- 主体 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="13%">
        <el-menu
          :default-active="$route.path"
          router
          text-color="black"
          active-text-color="red"
        >
          <el-menu-item
            v-for="(item, i) in navList"
            :key="i"
            :index="item.name"
          >
            <i :class="item.icon"></i>
            {{ item.title }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <!--路由占位符-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      navList: [
        { name: "/index", title: "Home", icon: "el-icon-s-home" },
        { name: "/user", title: "Users",icon:"el-icon-s-custom" },
        { name: "/book", title: "Books",icon:"el-icon-collection" },
      ],
    };
  },
};
</script>

<style >
.nav-logo img{
  position: absolute;
  width: 60px;
  padding-top: -1%;
  left: 5%;
}

.head-title {
  position: absolute;
  padding-top: 20px;
  left: 15%;
  font-size: 20px;
  font-weight: bold;
}


</style>
