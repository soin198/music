export default {
    state: {
        userId: "",
        username: "",
        photo:""
    },
    getters: {
        userId: (state) => state.userId,
        username: (state) => state.username,
        photo: (state) => state.photo,
    },
    mutations: {
        setUserId: (state, userId) => {
            state.userId = userId;
        },
        setUsername: (state, username) => {
            state.username = username;
        },
        setPhoto: (state, photo) => {
            state.photo = photo;
        },
    },
};
