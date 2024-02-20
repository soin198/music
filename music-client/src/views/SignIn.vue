<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-div">
      <a-button @click="show = true">账号登录</a-button>
      <a-button @click="show = false">验证码登录</a-button>
    </div>
    <div v-if="show">
      <el-form ref="signInForm" status-icon :model="paramsForm" :rules="SignInRules">
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="paramsForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="paramsForm.password"
                    @keyup.enter="handleLoginIn"/>
        </el-form-item>
        <el-form-item class="sign-btn">
          <el-button @click="gotoRegister">注册</el-button>
          <el-button type="primary" @click="handleLoginIn">确认登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-else>
      <el-form ref="codeForm" :model="codeLoginForm" :rules="CodeLoginRules" status-icon>
        <el-form-item prop="phone">
          <el-input v-model.trim="codeLoginForm.phone" placeholder="请输入手机号" :maxlength="11"
                    oninput="value=value.replace(/[^0-9]/g,'')"/>
        </el-form-item>
        <el-form-item prop="validCode">
          <el-input v-model.trim="codeLoginForm.validCode" placeholder="请输入验证码" :maxlength="6"
                    oninput="value=value.replace(/[^0-9]/g,'')"/>
        </el-form-item>
        <el-form-item class="sign-btn">
          <el-button type="primary" @click="getCode">获取验证码</el-button>
          <el-button type="primary" @click="codeLogin">确认登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, getCurrentInstance} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {CoreManager} from "@/api/core";
import {NavName, RouterName, SignInRules, SMS, CodeLoginRules} from "@/enums";
import {SmsManager} from "@/api/sms";
import {ElMessage} from "element-plus/es";

export default defineComponent({
  components: {
    YinLoginLogo,
  },
  setup: function () {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();
    const show = ref(true)

    // 登录用户名密码
    const paramsForm = reactive({
      username: "",
      password: "",
    });
    const codeLoginForm = reactive({
      phone: "",
      validCode: ""
    })

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
      if (200 === code) {
        successLogin(items);
      } else {
        ElMessage.error(message);
      }
    }

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
      if (200 === code) {
        ElMessage.success("发送成功")
      } else {
        ElMessage.error(message)
      }
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
      if (code === 200) {
        successLogin(items);
      } else {
        ElMessage.error(message)
      }
    }

    //登录成功处理
    function successLogin(items) {
      ElMessage.success("登录成功");
      proxy.$store.commit("setUserId", items.userId);
      proxy.$store.commit("setUsername", items.username);
      proxy.$store.commit("setPhoto", items.photo);
      proxy.$store.commit("setToken", items.token);
      changeIndex(NavName.Home);
      routerManager(RouterName.Home, {path: RouterName.Home});
    }

    function gotoRegister() {
      routerManager(RouterName.register, {path: RouterName.register});
    }

    return {
      paramsForm,
      SignInRules,
      handleLoginIn,
      gotoRegister,
      show,
      getCode,
      codeLogin,
      codeLoginForm,
      CodeLoginRules
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
