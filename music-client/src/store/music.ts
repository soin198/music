import { Icon } from "@/enums";

export default {
  state: {
    //音乐ID
    songId: "", // 音乐 ID
    songTitle: "", // 歌名
    songUrl: "", // 音乐 URL
    songPic: `/img/songPic/tubiao.jpg`, // 歌曲图片
    singerName: "", //  歌手名
    compose: [], // 处理后的歌词数据
  },
  getters: {
    songId: (state) => state.songId,
    songTitle: (state) => state.songTitle,
    songUrl: (state) => state.songUrl,
    songPic: (state) => state.songPic,
    singerName: (state) => state.singerName,
    compose: (state) => state.compose,
  },
  mutations: {
    setSongId: (state, songId) => {
      state.songId = songId;
    },
    setSongTitle: (state, songTitle) => {
      state.songTitle = songTitle;
    },
    setSongUrl: (state, songUrl) => {
      state.songUrl = songUrl;
    },
    setMusicImage: (state, songPic) => {
      state.songPic = songPic;
    },
    setSingerName: (state, singerName) => {
      state.singerName = singerName;
    },
    setAutoNext: (state, autoNext) => {
      state.autoNext = autoNext;
    },
    setCompose: (state, compose) => {
      state.compose = compose;
    },
    setIsPlay: (state, isPlay) => {
      state.isPlay = isPlay;
    },
    setPlayBtnIcon: (state, playBtnIcon) => {
      state.playBtnIcon = playBtnIcon;
    },
    setVolume: (state, volume) => {
      state.volume = volume;
    },
  },
  actions: {
    playMusic: ({commit}, {musicId, musicName, singerName, compose, index, musicImage, audio, musicList}) => {
      commit("setSongId", musicId);
      commit("setSongTitle", musicName);
      commit("setCompose", compose);
      commit("setCurrentPlayIndex", index);
      commit("setMusicImage", musicImage);
      commit("setSongUrl", audio);
      commit("setSingerName", singerName);
      commit("setCurrentPlayList", musicList);
    },
  },


};
