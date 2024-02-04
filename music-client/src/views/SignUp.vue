<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-head">
      <span>用户注册</span>
    </div>
    <el-form ref="signUpForm" label-width="70px" status-icon :model="register" :rules="SignUpRules">
      <el-form-item prop="username" label="用户名">
        <el-input v-model="register.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input type="password" placeholder="密码" v-model="register.password"></el-input>
      </el-form-item>
      <el-form-item prop="sex" label="性别">
        <el-radio-group v-model="register.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">保密</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="phoneNum" label="手机">
        <el-input placeholder="手机" v-model="register.phoneNum"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="register.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item prop="birth" label="生日">
        <el-date-picker type="date" placeholder="选择日期" v-model="register.birth"
                        style="width: 100%"></el-date-picker>
      </el-form-item>
      <el-form-item prop="introduction" label="签名">
        <el-input type="textarea" placeholder="签名" v-model="register.introduction"></el-input>
      </el-form-item>
      <el-form-item prop="location" label="地区">
        <el-select v-model="register.location" placeholder="地区" style="width: 100%">
          <el-option v-for="item in AREA" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="sign-btn">
        <el-button @click="goBack()">登录</el-button>
        <el-button type="primary" @click="handleSignUp(formRef)">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, getCurrentInstance} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {HttpManager} from "@/api";
import {getBirth} from "@/utils";
import {AREA, RouterName, NavName, SignUpRules} from "@/enums";

export default defineComponent({
  components: {
    YinLoginLogo,
  },
  setup() {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();
    const register = reactive({
      username: "",
      password: "",
      sex: "",
      phoneNum: "",
      email: "",
      birth: new Date(),
      introduction: "",
      location: "",
    })
    async function handleSignUp() {
      let canRun = true;
      (proxy.$refs["signUpForm"] as any).validate((valid) => {
        if (!valid) return (canRun = false);
      });
      if (!canRun) return;
      const result = (await HttpManager.SignUp(register)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result.success) {
        changeIndex(NavName.SignIn);
        routerManager(RouterName.SignIn, {path: RouterName.SignIn});
      }
    }

    return {
      SignUpRules,
      AREA,
      register,
      handleSignUp,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
