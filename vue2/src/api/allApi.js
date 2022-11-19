import request from "@/utils/request";

//用户账号密码登录
export const userLogin = params =>{
    return request({
        url:'/userLogin',
        method:'post',
        params:params
    })
}

//用户注册账号
export const userRegister = params =>{
    return request({
        url:'/userRegister',
        method:'post',
        params:params
    })
}

//发送邮箱验证码
export const getCode = params =>{
    return request({
        url:'/getCode',
        method:'get',
        params:params
    })
}


//上传图片以及人脸检测
export const upload = params =>{
    return request({
        url:'/files/upload',
        method:'post',
        params:params
    })
}

//获得前几名的排名，做轮播图
export const getTopFace = params =>{
    return request({
        url:'/getTopFace',
        method:'post',
        params:params
    })
}

//获得所有颜值
export const getAllFace = params =>{
    return request({
        url:'/getAllFace',
        method:'post',
        params:params
    })
}

//插入faceID并在后台判断face颜值
export const insertFaceRelevance = params =>{
    return request({
        //faceId1,faceId2, userId
        url:'/insertFaceRelevance',
        method:'post',
        params:params
    })
}

//根据faceId1 获取颜值PK结果
export const getFaceRelevance = params =>{
    return request({
        url:'/getFaceRelevance',
        method:'post',
        params:params
    })
}

// 获取颜值PK左边的人脸列表
export const getFacePKLeft = params =>{
    return request({
        url:'/getFacePKLeft',
        method:'post',
        params:params
    })
}

// 获取颜值PK左边的人脸列表
export const getFacePKRight = params =>{
    return request({
        url:'/getFacePKRight',
        method:'post',
        params:params
    })
}

// 获取颜值PK左边的人脸列表
export const getFaceByUser = params =>{
    return request({
        url:'/getFaceByUser',
        method:'post',
        params:params
    })
}

// 更新是否公开
export const updateIsShow = params =>{
    return request({
        url:'/updateIsShow',
        method:'put',
        params:params
    })
}

// 是否删除
export const updateISDelete = params =>{
    return request({
        url:'/updateISDelete',
        method:'put',
        params:params
    })
}

// 是否删除
export const randomPK = params =>{
    return request({
        url:'/randomPK',
        method:'put',
        params:params
    })
}
