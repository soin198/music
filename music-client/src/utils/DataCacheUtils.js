const TOKEN_KEY = "auth.token";
const USER_FISSION_KEY = "user.fission";
const DataCache = {
    //token缓存
    tokenCache: (token) => {
        localStorage.setItem(TOKEN_KEY, token);
    },
    getToken: () => {
        return localStorage.getItem(TOKEN_KEY);
    },
    //清除token缓存
    clearTokenCache: () => {
        localStorage.removeItem(TOKEN_KEY);
    },
    userFission: {
        get: () => {
            return localStorage.getItem(USER_FISSION_KEY) ?? "";
        },
        save: (userFissionId) => {
            localStorage.setItem(USER_FISSION_KEY, userFissionId);
        },
        clear: () => {
            localStorage.removeItem(USER_FISSION_KEY);
        }
    }
}


export default DataCache;