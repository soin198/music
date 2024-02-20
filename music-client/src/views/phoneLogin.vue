<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-head">
      <span>手机号登录</span>
    </div>
    <el-form ref="signInForm" status-icon>
      <el-form-item prop="phone">
        <el-input placeholder="手机号" v-model.trim="phone"/>
      </el-form-item>
      <el-form-item prop="code" v-if="isOpen">
        <el-input placeholder="验证码" v-model="code"/>
      </el-form-item>
      <el-form-item class="sign-btn">
        <el-button type="primary" @click="getCode">获取验证码</el-button>
        <el-button type="primary" @click="handleLoginIn">确认登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, getCurrentInstance} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {HttpManager} from "@/api";
import {NavName, RouterName, SignInRules, SMS} from "@/enums";

export default defineComponent({
  components: {
    YinLoginLogo,
  },
  setup: function () {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();
    const phone = ref(null);
    const code = reactive(null);
    const isOpen = ref(false);

    async function handleLoginIn() {
      const {code, message} = (await HttpManager.generateCode(phone, SMS.LOGIN)) as Response;
      if (code !== 200) {
        (proxy as any).$message({
          message: message,
          type: "error",
        });
        return;
      }
      (proxy as any).$message({
        message: "验证码发送成功",
        type: "success",
      });
      isOpen.value = true
      changeIndex(NavName.Home);
      routerManager(RouterName.Home, {path: RouterName.Home});
    }

    async function getCode() {
      const {code, message} = (await HttpManager.generateCode(phone.value, SMS.LOGIN)) as Response;
      if (200 !== code) {
        (proxy as any).$message({
          message: message,
          type: "error",
        });
        return;
      }
      isOpen.value = true
    }

    return {
      SignInRules,
      handleLoginIn,
      phone,
      isOpen,
      code,
      getCode
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
