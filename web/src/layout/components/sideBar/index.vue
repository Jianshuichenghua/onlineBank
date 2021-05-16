<template>
  <div class="sideBar" id="domSideBar">
    <div class="menu-container">
      <h3 class="menu-title">Menu</h3>
      <el-scrollbar>
        <el-menu
          :default-active="activeMenu"
          class="el-menu-vertical-demo"
          background-color="#f0f3f4"
          text-color="#48aee9"
          active-text-color="rgb(79, 148, 212)"
          mode="vertical"
          :collapse-transition="false"
          :collapse="opened"
        >
          <sidebar-item
            v-for="item in routes"
            :key="item.path"
            :item="item"
            :basePath="item.path"
          ></sidebar-item>
        </el-menu>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import SidebarItem from "./SideBarItem";
import { mapGetters } from "vuex";
export default {
  components: { SidebarItem },
  computed: {
    ...mapGetters(["routes", "opened"]),
    // booleanOpen() {
    //   return this.opened === 'true' ? true : false
    // },
    activeMenu() {
      return this.$route.path;
    },
  },
};
</script>
<style scoped>
.menu-title {
  color: #48aee9;
  font-size: 40px;
  margin: 20px;
}
.sideItem {
  border: 1px solid hsl(0, 2%, 89%);
}

.menu-container {
  float: right;
}
.el-menu {
  /* border: 1px solid hsl(0, 2%, 89%); */
}
</style>