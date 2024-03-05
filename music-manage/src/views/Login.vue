<template>
  <div class="login-container">
    <div class="title">24.music</div>
    <div class="login">
      <el-form>
        <el-form-item prop="username">
          <el-input v-model.trim="userName" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="请输入密码" v-model.trim="secretKey" @keyup.enter="submitForm"/>
        </el-form-item>
        <el-form-item>
          <el-button class="login-btn" type="primary" @click="submitForm">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref} from "vue";
import mixin from "@/mixins/mixin";
import {RouterName} from "@/enums";
import {CoreManager} from "@/api/core"
import {Message} from "@/message/CustomMessage"

export default defineComponent({
  setup() {
    const {routerManager} = mixin();
    const userName = ref("")
    const secretKey = ref("")
    const rules = reactive({
      username: [{required: true, message: "请输入用户名", trigger: "blur"}],
      password: [{required: true, message: "请输入密码", trigger: "blur"}],
    });

    async function submitForm() {
      const {code, message} = (await CoreManager.login(userName.value, secretKey.value)) as Response;
      if (code === 200) {
        Message.success("登录成功")
        routerManager(RouterName.Info, {path: RouterName.Info});
      } else {
        Message.error(message)
      }
    }

    return {
      rules,
      submitForm,
      userName,
      secretKey
    };
  },
});
</script>

<style scoped>
.login-container {
  position: relative;
  background: url("../assets/images/logo.png");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}

.title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}

.login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;

}

.login-btn {
  width: 100%;
}
</style>
