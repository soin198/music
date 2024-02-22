export default {
    state: {
        /**
         * 歌手ID
         */
        singerId: null,
    },
    getters: {
      singerId: (state) => state.singerId,
    },
    mutations: {
      singerId: (state, singerId) => {
            state.singerId = singerId;
        },

    },
    actions: {
        playMusic: ({commit}, {singerId}) => {
            commit("singerId", singerId);
        },
    },
};
