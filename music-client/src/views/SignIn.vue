<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-head">
      <span>帐号登录</span>
    </div>
    <el-form ref="signInForm" status-icon :model="paramsForm" :rules="SignInRules">
      <el-form-item prop="username">
        <el-input placeholder="用户名" v-model="paramsForm.username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" placeholder="密码" v-model="paramsForm.password"
                  @keyup.enter="handleLoginIn"/>
      </el-form-item>
      <el-form-item class="sign-btn">
        <el-button @click="handleSignUp">注册</el-button>
        <el-button @click="phoneLogin">验证码登录</el-button>
        <el-button type="primary" @click="handleLoginIn">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, getCurrentInstance} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {CoreManager} from "@/api/core";
import {NavName, RouterName, SignInRules} from "@/enums";

export default defineComponent({
  components: {
    YinLoginLogo,
  },
  setup: function () {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();

    // 登录用户名密码
    const paramsForm = reactive({
      username: "",
      password: "",
    });

    async function handleLoginIn() {
      let canRun = true;
      (proxy.$refs["signInForm"] as any).validate((valid) => {
        if (!valid) return (canRun = false);
      });
      if (!canRun) return;
      const params = new URLSearchParams();
      params.append("username", paramsForm.username);
      params.append("password", paramsForm.password);
      const {code, items, message} = (await CoreManager.login(params)) as Response;
      if (code !== 200) {
        (proxy as any).$message({
          message: message,
          type: "error",
        });
        return;
      }
      (proxy as any).$message({
        message: "登录成功",
        type: "success",
      });
      proxy.$store.commit("setUserId", items.userId);
      proxy.$store.commit("setUsername", items.username);
      proxy.$store.commit("setPhoto", items.photo);
      proxy.$store.commit("setToken", items.token);
      changeIndex(NavName.Home);
      routerManager(RouterName.Home, {path: RouterName.Home});

    }

    function handleSignUp() {
      routerManager(RouterName.register, {path: RouterName.register});
    }

    function phoneLogin() {
      routerManager(RouterName.phoneLogin, {path: RouterName.phoneLogin});
    }

    return {
      paramsForm,
      SignInRules,
      handleLoginIn,
      handleSignUp,
      phoneLogin
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
