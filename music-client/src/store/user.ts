export default {
    state: {
        userId: "",
        username: "",
        userPic: "",
    },
    getters: {
        userId: (state) => state.userId,
        username: (state) => state.username,
        userPic: (state) => state.userPic,
    },
    mutations: {
        setUserId: (state, userId) => {
            state.userId = userId;
        },
        setUsername: (state, username) => {
            state.username = username;
        },
        setUserPic: (state, userPic) => {
            state.userPic = userPic;
        },
    },
};
