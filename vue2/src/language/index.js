/*
 * @Author: pcf pcf
 * @Date: 2022-07-19 10:29:46
 * @LastEditors: pcf pcf
 * @LastEditTime: 2022-08-03 16:09:34
 * @FilePath: \itanri2-admin-front\src\assets\i18n\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import Vue from "vue"; //引入vue
import VueI18n from 'vue-i18n';
import elementEnLocale from 'element-ui/lib/locale/lang/en' //element英文包
import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'//中文包
import enLocale from "./en";
import zhLocale from "./zh";

Vue.use(VueI18n) // 通过插件的形式挂载

// 引入本地
const messages = {
    en: {
        message: 'hello',
        ...enLocale,
        ...elementEnLocale
    },
    zh: {
        message: '你好',
        ...zhLocale,
        ...elementZhLocale
    }
}

// 创建国际化实例
const i18n = new VueI18n({
    // 设置默认语言
    locale:sessionStorage.getItem('locale') || 'zh',
    messages,
})
export default i18n