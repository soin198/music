import { getCurrentInstance, computed } from "vue";
import { useStore } from "vuex";
import { LocationQueryRaw } from "vue-router";
import { RouterName } from "@/enums";
import { HttpManager } from "@/api";

interface routerOptions {
  path?: string;
  query?: LocationQueryRaw;
}

export default function () {
  const { proxy } = getCurrentInstance();

  const store = useStore();
  const token = computed(() => store.getters.token);

  // 获取歌曲名
  function getSongTitle(str) {
    return str.split("-")[1];
  }

  // 判断登录状态
  function checkStatus(status?: boolean) {
    if (!token.value && status !== false) {
      (proxy as any).$message({
        message: "登录失效....",
        type: "error",
      });
      return false;
    }
    return true;
  }

  // 播放
  function playMusic({musicId, musicName, singerName, compose, index, musicImage, audio, musicList}) {
    proxy.$store.dispatch("playMusic", {
      //音乐ID
      musicId: musicId,
      //音乐名称
      musicName: musicName,
      //歌手名称
      singerName: singerName,
      //歌词
      compose: compose,
      //索引
      index: index,
      //歌曲图片
      musicImage: musicImage,
      //歌曲音频
      audio: audio,
      //当前歌曲列表
      musicList: musicList,
    });
  }


  // 下载
  async function downloadMusic({ songUrl, songName }) {
    if (!songUrl) {
      (proxy as any).$message({
        message: "下载链接为空！",
        type: "error",
      });
      console.error("下载链接为空！");
      return;
    }

    const result = (await HttpManager.downloadMusic(songUrl)) as ResponseBody;
    const eleLink = document.createElement("a");
    eleLink.download = `${songName}.mp3`;
    eleLink.style.display = "none";
    // 字符内容转变成 blob 地址
    const blob = new Blob([result.data]);
    eleLink.href = URL.createObjectURL(blob);
    document.body.appendChild(eleLink); // 触发点击
    eleLink.click();
    document.body.removeChild(eleLink); // 移除
  }

  // 导航索引
  function changeIndex(value) {
    proxy.$store.commit("setActiveNavName", value);
  }
  // 路由管理
  function routerManager(routerName: string | number, options: routerOptions) {
    switch (routerName) {
      case RouterName.Search:
        proxy.$router.push({ path: options.path, query: options.query });
        break;
      case RouterName.Home:
      case RouterName.SongSheet:
      case RouterName.SongSheetDetail:
      case RouterName.Singer:
      case RouterName.SingerDetail:
      case RouterName.Personal:
      case RouterName.PersonalData:
      case RouterName.Setting:
      case RouterName.SignIn:
      case RouterName.register:
      case RouterName.SignOut:
      case RouterName.Lyric:
      case RouterName.Error:
      default:
        proxy.$router.push({ path: options.path });
        break;
    }
  }

  function goBack(step = -1) {
    proxy.$router.go(step);
  }

  return {
    getSongTitle,
    changeIndex,
    checkStatus,
    playMusic,
    routerManager,
    goBack,
    downloadMusic,
  };
}
