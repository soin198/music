import {createApp} from "vue";
import ElementPlus from "element-plus";
import {ElMessage} from 'element-plus';
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "element-plus/dist/index.css";
import "./assets/css/index.scss";
import "./assets/icons/index.js";

import {Store} from "vuex";

declare module "@vue/runtime-core" {
    interface State {
        count: number;
    }

    interface ComponentCustomProperties {
        $store: Store<State>;
    }
}
const app = createApp(App);
app.use(ElementPlus).use(store).use(router);
app.config.globalProperties.$message = ElMessage;
app.mount('#app')
