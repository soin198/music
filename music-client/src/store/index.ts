import {createStore} from "vuex";
import configure from "./configure";
import user from "./user";
import song from "./song";
import sing from "./singer"

export default createStore({
    modules: {
        configure,
        user,
        song,
        sing
    },
});
