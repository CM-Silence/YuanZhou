//获取用户对象
import {ref} from "vue";

//用户
export const CURRENT_USER = ref(getUser())
export const CURRENT_PAGE = ref(getCurrentPage())

export function getUser(){
    const userJson = localStorage.getItem("user") || '';
    if(!userJson){
        return ''
    }
    else{
        return JSON.parse(userJson)
    }
}

//设置用户
export function setUser(item){
    if(typeof item === 'string'){
        localStorage.setItem("user", item)
    }
    else if(typeof item === 'object'){
        localStorage.setItem("user", JSON.stringify(item))
    }
    refreshUser()
}

//更新用户
export function refreshUser(){
    CURRENT_USER.value = getUser()
}

//获取用户权限
export function getUserPermission(){
    const user = getUser()
    if(!user){
        return 0
    }
    else{
        return user.permission
    }
}

export function getCurrentPage(){
    const CURRENT_PATH = window.location.hash  // 获取当前路径，例如 "#/page/subpage"
    const pageList = CURRENT_PATH.split(/[#?]/)
    let index = pageList.length - 1
    if(CURRENT_PATH.includes('?')){
        index--
    }
    return pageList[index]
}

export function refreshCurrentPage(){
    CURRENT_PAGE.value = getCurrentPage()
}

export function setCurrentPage(path){
    CURRENT_PAGE.value = path
}