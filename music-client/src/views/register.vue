<template>
  <yin-login-logo></yin-login-logo>
  <div class="sign">
    <div class="sign-head">
      <span>注册账户</span>
    </div>
    <el-form ref="registerForm" label-width="70px" status-icon :model="register" :rules="SignUpRules">
      <el-form-item prop="username" label="用户名">
        <el-input v-model.trim="register.username" placeholder="用户名" clearable/>
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input type="password" placeholder="密码" v-model.trim="register.password" clearable/>
      </el-form-item>
      <el-form-item prop="sex" label="性别">
        <el-radio-group v-model="register.sex">
          <el-radio v-model="register.sex" label="WOMEN">女</el-radio>
          <el-radio v-model="register.sex" label="MALE">男</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="phone" label="手机">
        <el-input placeholder="手机" v-model.trim="register.phone"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model.trim="register.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item prop="birth" label="生日">
        <el-date-picker type="date" placeholder="选择日期" v-model="register.birth" style="width: 100%"/>
      </el-form-item>
      <el-form-item prop="resume" label="签名">
        <el-input type="textarea" placeholder="签名" v-model.trim="register.resume"/>
      </el-form-item>
      <el-form-item prop="codes" label="地区">
        <el-cascader v-model="codes" placeholder="地区" :options="areaCodes" collapse-tags clearable/>
      </el-form-item>
      <el-form-item class="sign-btn">
        <el-button @click="goBack()">登录</el-button>
        <el-button type="primary" @click="handleSignUp(formRef)">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, getCurrentInstance, ref} from "vue";
import mixin from "@/mixins/mixin";
import YinLoginLogo from "@/components/layouts/YinLoginLogo.vue";
import {HttpManager} from "@/api";
import {formatDate} from "@/utils";
import {RouterName, NavName, SignUpRules} from "@/enums";
import {cityCodeQuery} from "@/static/js/area"

export default defineComponent({
  components: {
    YinLoginLogo,
  },
  setup: function () {
    const {proxy} = getCurrentInstance();
    const {routerManager, changeIndex} = mixin();
    const codes = ref([]);
    const register = reactive({
      username: null,
      password: null,
      sex: null,
      phone: null,
      email: null,
      birth: null,
      resume: null,
      province: null,
      city: null,
      region: null
    })
    const areaCodes = cityCodeQuery();

    async function handleSignUp() {
      let canRun = true;
      (proxy.$refs["registerForm"] as any).validate((valid) => {
        if (!valid) return (canRun = false);
      });
      if (!canRun) return;
      register.birth = formatDate(register.birth);
      const value = codes.value;
      if (null !== value) {
        register.province = value[0]
        register.city = value[1]
        register.region = value[2]
      }
      const {code, items, message} = await HttpManager.register(register) as Response;
      if (200 !== code) {
        (proxy as any).$message({
          message: message,
          type: "error",
        });
        return;
      }
      if (items) {
        changeIndex(NavName.SignIn);
        routerManager(RouterName.SignIn, {path: RouterName.SignIn});
      }
    }

    return {
      SignUpRules,
      register,
      handleSignUp,
      areaCodes,
      codes
    };
  },
});


</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";
</style>
