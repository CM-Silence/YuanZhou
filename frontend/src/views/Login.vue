<template>
  <div class="login-body">
    <div class="login-container">
      <div class="head">
        <img class="logo" src="@/assets/logo.png"  alt="圆舟，启动！"/>
        <div class="name">
          <div class="title">CirArk</div>
          <div class="tips">圆舟在线教育平台</div>
        </div>
      </div>
      <el-form label-position="top"
               :rules="state.rules"
               :model="state.ruleForm"
               status-icon
               label-width="auto"
               ref="loginForm"
               class="login-form">

        <el-form-item label="账号" prop="username">
          <el-input
              type="text"
              v-model.trim="state.ruleForm.username"
              autocomplete="off"
              :prefix-icon="User">

          </el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
              type="password"
              v-model.trim="state.ruleForm.password"
              autocomplete="off"
              :prefix-icon="Lock">

          </el-input>
        </el-form-item>

        <el-form-item>
          <br>
          <el-checkbox v-model="state.remember" @change="!state.remember">记住密码</el-checkbox>
          <el-button type="primary" text style="margin-left: 130px" @click="state.settingFormVisible = true">一键注册</el-button>
          <el-button color="#d9c98b" style="width: 100%; margin-top: 20px" type="primary" @click="submitForm(loginForm)" :loading="state.loading" round>
            <el-text style="color: white">登录</el-text>
          </el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>

  <el-dialog
      v-model="state.settingFormVisible"
      title="用户注册"
      width="500"
      center
  >

    <el-form :model="state.registerForm" :rules="state.rules" ref="myRegisterForm" label-position="top" status-icon>

      <el-form-item
          label="用户名"
          prop="username"
      >
        <el-input
            type="text"
            v-model.trim="state.registerForm.username"
            autocomplete="off"
        />

      </el-form-item>

      <el-form-item
          label="密码"
          prop="password"
      >
        <el-input
            type="password"
            v-model.trim="state.registerForm.password"
            autocomplete="off"
        />

      </el-form-item>

      <el-form-item
          label="确认密码"
          prop="confirm"
      >
        <el-input
            type="password"
            v-model.trim="state.registerForm.confirm"
            autocomplete="off"
        />

      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="state.settingFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitRegisterForm(myRegisterForm)">
          确定
        </el-button>
      </div>
    </template>

  </el-dialog>
</template>

<script setup>
import {ref, reactive, inject, onMounted} from "vue";
import router from "@/router/index.js";
import { ElMessage } from 'element-plus'

const axios = inject("axios")
const loginForm = ref(null)
import {Lock, User} from "@element-plus/icons-vue";
import {isSame, isPasswordValid} from "@/utils/validator"
import {axiosPost} from "@/utils/axiosUtil.js";

onMounted(initialize)

const myRegisterForm = ref(null)

const state = reactive({
  ruleForm: {
    username: '',
    password: ''
  },
  registerForm:{
    username: '',
    password: '',
    confirm: '',
  },
  remember: false,
  loading: false,
  settingFormVisible: false,
  rules: {
    username: [
      { required: 'true', message: '账号不能为空', trigger: 'blur' },
      { min: 6, max: 16, message: '账号长度需要在6-16个字符之间', trigger: 'blur' },
    ],
    password: [
      { required: 'true', message: '密码不能为空', trigger: 'blur' },
      { min: 6, max: 16, message: '密码长度需要在6-16个字符之间', trigger: 'blur' },
      { validator: isPasswordValid, trigger: 'blur' },

    ],
    confirm: [
      { required: 'true', message: '请再输入一次密码', trigger: 'blur' },
      { validator: (rule, value, callback) => isSame(rule, value, callback, state.registerForm.password, '两次输入的密码不一致'), trigger: 'blur' },
    ]
  }
})

const user = reactive({
  username: localStorage.getItem("username") || '',
  password: localStorage.getItem("password") || '',
  remember: localStorage.getItem("remember") || '0',
})

function initialize(){
  if(user.remember === '1') {
    state.ruleForm.username = user.username
    state.ruleForm.password = user.password
    state.remember = true
  }
}

async function submitRegisterForm(form) {
  if (!form) return
  await form.validate(async (valid) => {
    if (valid) {
      const result = await axiosPost({
        url: '/user/register',
        data: state.registerForm,
        name: 'register'
      })
      if(result){
        ElMessage.success('用户注册成功')
        for (const i in state.registerForm) {
          state.registerForm[i] = ''
        }
      }
      state.settingFormVisible = false
    }
  })
}

const submitForm = async (form) => {
  console.log(form)
  form.validate(async (valid) => {
    if (valid) {
      const data = {
        username: state.ruleForm.username,
        password: state.ruleForm.password
      };

      if (state.ruleForm.username && state.ruleForm.password) {
        const result = await axiosPost({url: '/user/login', data: data, name: 'login'})
        if(result && result.data.code === 201){
          // 需要将返回的数据存入Store中
          localStorage.setItem("token", result.data.data.token)
          localStorage.setItem("user", JSON.stringify(result.data.data.user))
          // 记住账号密码
          if (state.remember) {
            localStorage.setItem("username", state.ruleForm.username)
            localStorage.setItem("password", state.ruleForm.password)
            localStorage.setItem("remember", state.remember ? '1' : '0')
          } else {
            localStorage.setItem("username", '')
            localStorage.setItem("password", '')
            localStorage.setItem("remember", '0')
          }

          ElMessage.success("登录成功")
          await router.push("/home/homePage")
        }
        else if(result && result.data.code === 202){
          ElMessage.error("账号或密码错误！")
        }
      }
      else {
        ElMessage.error("账号和密码不能为空！")
      }
      state.loading = false
    }
  })
}
</script>

<style scoped>
.login-body {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
  width: 100vw;
  height: 100vh;
  background: linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5)), url("@/assets/background.jpeg") no-repeat center;
  background-size: cover;
}
.login-container {
  width: 420px;
  height: 450px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 21px 41px 0 rgba(0, 0, 0, 0.2);
}
.head {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 0 20px 0;
}
.head img {
  width: 100px;
  height: 100px;
  margin-right: 20px;
}
.head .title {
  font-size: 28px;
  color: #d9c98b;
  font-weight: bold;
}
.head .tips {
  font-size: 18px;
  color: #999;
}
.login-form {
  width: 70%;
  margin: 0 auto;
}
</style>