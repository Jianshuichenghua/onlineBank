<template>
  <div class="header">
    <!-- <div class="header_l">
      <a href="javascript:;" class="logoLink"
        >Menu</a
      >
    </div> -->
    <div class="header_r">
      <div class="headr_d1">
        <!-- <side-collapse class="sidecoll"></side-collapse> -->
        <bread-crumb class="bread"></bread-crumb>
      </div>
      <div class="headr_d2">
        <ul class="headrUl clearFixed">
          <li id="domFullScreen">
            <full-screen></full-screen>
          </li>
          <li id="domPersonal">
            <user-dropdown></user-dropdown>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import SideCollapse from "@/components/SideCollapse";
import BreadCrumb from "@/components/BreadCrumb";
import UserDropdown from "@/components/UserDropdown";
import FullScreen from "@/components/FullScreen";
export default {
  components: {
    SideCollapse,
    BreadCrumb,
    UserDropdown,
    FullScreen,
  },
  methods: {
    toggleMsgShow() {
      this.$store.commit("app/SET_MSGISOPEN");
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  color: #48aee9;
}

li:hover {
  background-color: #48aee9 !important;
}
.el-breadcrumb__item:last-child {
  color: #fff;
}
</style>
