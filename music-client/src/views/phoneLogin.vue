<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-head">
      <span>手机号登录</span>
    </div>
    <el-form ref="codeForm" :model="codeLoginForm" :rules="CodeLoginRules" status-icon>
      <el-form-item prop="phone">
        <el-input v-model.trim="codeLoginForm.phone" placeholder="请输入手机号" :maxlength="11" oninput ="value=value.replace(/[^0-9]/g,'')"/>
      </el-form-item>
      <el-form-item prop="validCode" v-if="isOpen">
        <el-input v-model.trim="codeLoginForm.validCode" placeholder="请输入验证码" :maxlength="6" oninput ="value=value.replace(/[^0-9]/g,'')"/>
      </el-form-item>
      <el-form-item class="sign-btn">
        <el-button type="primary" @click="getCode">获取验证码</el-button>
        <el-button type="primary" @click="codeLogin">确认登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, reactive, getCurrentInstance} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {SmsManager} from "@/api/sms";
import {NavName, RouterName, CodeLoginRules, SMS} from "@/enums";

export default defineComponent({
  components: {YinLoginLogo},
  setup: function () {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();
    const codeLoginForm = reactive({
      phone: null,
      validCode: null
    })
    const isOpen = ref(false);

    async function getCode() {
      let value = true;
      (proxy.$refs["codeForm"] as any).validate((valid) => {
        if (!valid) {
          return (value = false);
        }
      });
      if (!value) {
        return;
      }
      const {code, message} = (await SmsManager.getCode(codeLoginForm.phone, SMS.LOGIN)) as Response;
      if (200 !== code) {
        (proxy as any).$message({
          message: message,
          type: "error",
        });
        return;
      }
      (proxy as any).$message({
        message: "发送成功",
        type: "success",
      });
      isOpen.value = true;
    }

    async function codeLogin() {
      let value = true;
      (proxy.$refs["codeForm"] as any).validate((valid) => {
        if (!valid) {
          return (value = false);
        }
      });
      if (!value) {
        return;
      }
      const {code, items, message} = (await SmsManager.codeLogin(codeLoginForm.phone, codeLoginForm.validCode, SMS.LOGIN)) as Response;
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
      proxy.$store.commit("setToken", items.token);
      changeIndex(NavName.Home);
      routerManager(RouterName.Home, {path: RouterName.Home});
    }

    return {
      CodeLoginRules,
      codeLogin,
      isOpen,
      getCode,
      codeLoginForm
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
