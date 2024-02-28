<template>
  <div class="play-bar" :class="{ show: !toggle }">
    <div class="fold" :class="{ turn: toggle }">
      <yin-icon :icon="iconList.ZHEDIE" @click="toggle = !toggle"></yin-icon>
    </div>
    <!--播放进度-->
    <el-slider class="progress" v-model="nowTime" @change="changeTime" size="small"></el-slider>
    <div class="control-box">
      <div class="info-box">
        <!--歌曲图片-->
        <el-image class="song-bar-img" fit="contain" :src="songPic" @click="goPlayerPage"/>
        <!--播放开始结束时间-->
        <div v-if="songId">
          <div class="song-info">{{ this.songTitle }} - {{ this.singerName }}</div>
          <div class="time-info">{{ startTime }} / {{ endTime }}</div>
        </div>
      </div>
      <div class="song-ctr">
        <yin-icon class="yin-play-show" :icon="playStateList[playStateIndex]" @click="changePlayState"/>
        <!--上一首-->
        <yin-icon class="yin-play-show" :icon="iconList.SHANGYISHOU" @click="prev"/>
        <!--播放-->
        <yin-icon :icon="playBtnIcon" @click="togglePlay"></yin-icon>
        <!--下一首-->
        <yin-icon class="yin-play-show" :icon="iconList.XIAYISHOU" @click="next"/>
        <!--音量-->
        <el-dropdown class="yin-play-show" trigger="click">
          <yin-icon v-if="volume !== 0" :icon="iconList.YINLIANG"/>
          <yin-icon v-else :icon="iconList.JINGYIN"></yin-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-slider class="yin-slider" style="height: 150px; margin: 10px 0" v-model="volume"
                         :vertical="true"/>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
      <div class="song-ctr song-edit">
        <!--收藏-->
        <yin-icon
            class="yin-play-show"
            :class="{ active: isShow }"
            :icon="isShow ? iconList.like : iconList.dislike"
            @click="converter"
        ></yin-icon>
        <!--下载-->
        <yin-icon
            class="yin-play-show"
            :icon="iconList.download"
            @click="
            downloadMusic({
              songUrl,
              songName: singerName + '-' + songTitle,
            })
          "
        ></yin-icon>
        <!--歌曲列表-->
        <yin-icon :icon="iconList.LIEBIAO" @click="changeAside"></yin-icon>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, computed, onMounted, watch} from "vue";
import {mapGetters, useStore} from "vuex";
import mixin from "@/mixins/mixin";
import YinIcon from "./YinIcon.vue";
import {formatSeconds} from "@/utils";
import {Icon, RouterName} from "@/enums";
import {CollectManager} from "@/api/collect"
import {ElMessage} from "element-plus";

export default defineComponent({
  components: {
    YinIcon,
  },
  setup() {
    const store = useStore();
    const {routerManager, playMusic, checkStatus, downloadMusic} = mixin();
    // 是否收藏
    const isShow = ref(false);

    const userId = computed(() => store.getters.userId);
    const songId = computed(() => store.getters.songId);
    const token = computed(() => store.getters.token);
    watch(songId, () => {
      likeBuild();
    });

    watch(token, (value) => {
      if (!value) isShow.value = false;
    });

    async function likeBuild() {
      if (!checkStatus(false)) return;
      isShow.value = ((await CollectManager.isLike(userId.value, songId.value)) as Response).items;
    }

    //切换收藏
    async function converter() {
      //判断是否登录
      if (!checkStatus()) {
        return;
      }
      const {code} = (isShow.value)
          //取消收藏
          ? (await CollectManager.cancelLike(userId.value, 1)) as Response
          //收藏
          : (await CollectManager.saveLike(userId.value, 1)) as Response;
      if (code === 200) {
        ElMessage.success("感谢您的喜欢~")
        isShow.value = true;
      } else {
        ElMessage.success("操作失败~")
        isShow.value = false;
      }
    }

    onMounted(() => {
      if (songId.value) likeBuild();
    });

    return {
      isShow,
      playMusic,
      routerManager,
      checkStatus,
      converter,
      downloadMusic
    };
  },
  data() {
    return {
      //开始时间
      startTime: "00:00",
      //结束时间
      endTime: "00:00",
      // 进度条的位置
      nowTime: 0,
      toggle: true,
      volume: 50,
      playState: Icon.XUNHUAN,
      playStateList: [Icon.XUNHUAN, Icon.LUANXU],
      playStateIndex: 0,
      iconList: {
        download: Icon.XIAZAI,
        ZHEDIE: Icon.ZHEDIE,
        SHANGYISHOU: Icon.SHANGYISHOU,
        XIAYISHOU: Icon.XIAYISHOU,
        YINLIANG: Icon.YINLIANG1,
        JINGYIN: Icon.JINGYIN,
        LIEBIAO: Icon.LIEBIAO,
        dislike: Icon.Dislike,
        like: Icon.Like,
      },
    };
  },
  computed: {
    ...mapGetters([
      "userId",
      "isPlay", // 播放状态
      "playBtnIcon", // 播放状态的图标
      "songId", // 音乐id
      "songUrl", // 音乐地址
      "songTitle", // 歌名
      "singerName", // 歌手名
      "songPic", // 歌曲图片
      "curTime", // 当前音乐的播放位置
      "duration", // 音乐时长
      "currentPlayList",
      "currentPlayIndex", // 当前歌曲在歌曲列表的位置
      "showAside", // 是否显示侧边栏
      "autoNext", // 用于触发自动播放下一首
    ]),
  },
  watch: {
    // 切换播放状态的图标
    isPlay(value) {
      this.$store.commit("setPlayBtnIcon", value ? Icon.ZANTING : Icon.BOFANG);
    },
    volume() {
      this.$store.commit("setVolume", this.volume / 100);
    },
    // 播放时间的开始和结束
    curTime() {
      this.startTime = formatSeconds(this.curTime);
      this.endTime = formatSeconds(this.duration);
      // 移动进度条
      this.nowTime = (this.curTime / this.duration) * 100;
    },
    // 自动播放下一首
    autoNext() {
      this.next();
    },
  },
  methods: {
    changeAside() {
      this.$store.commit("setShowAside", !this.showAside);
    },
    // 控制音乐播放 / 暂停
    togglePlay() {
      this.$store.commit("setIsPlay", this.isPlay ? false : true);
    },
    changeTime() {
      this.$store.commit("setChangeTime", this.duration * (this.nowTime * 0.01));
    },
    changePlayState() {
      this.playStateIndex = this.playStateIndex >= this.playStateList.length - 1 ? 0 : ++this.playStateIndex;
      this.playState = this.playStateList[this.playStateIndex];
    },
    // 上一首
    prev() {
      if (this.playState === Icon.LUANXU) {
        let playIndex = Math.floor(Math.random() * this.currentPlayList.length);
        playIndex = playIndex === this.currentPlayIndex ? playIndex + 1 : playIndex;
        this.$store.commit("setCurrentPlayIndex", playIndex);
        this.toPlay(this.currentPlayList[playIndex].url);
      } else if (this.currentPlayIndex !== -1 && this.currentPlayList.length > 1) {
        if (this.currentPlayIndex > 0) {
          this.$store.commit("setCurrentPlayIndex", this.currentPlayIndex - 1);
          this.toPlay(this.currentPlayList[this.currentPlayIndex].url);
        } else {
          this.$store.commit("setCurrentPlayIndex", this.currentPlayList.length - 1);
          this.toPlay(this.currentPlayList[this.currentPlayIndex].url);
        }
      }
    },
    // 下一首
    next() {
      if (this.playState === Icon.LUANXU) {
        let playIndex = Math.floor(Math.random() * this.currentPlayList.length);
        playIndex = playIndex === this.currentPlayIndex ? playIndex + 1 : playIndex;
        this.$store.commit("setCurrentPlayIndex", playIndex);
        this.toPlay(this.currentPlayList[playIndex].url);
      } else if (this.currentPlayIndex !== -1 && this.currentPlayList.length > 1) {
        if (this.currentPlayIndex < this.currentPlayList.length - 1) {
          this.$store.commit("setCurrentPlayIndex", this.currentPlayIndex + 1);
          this.toPlay(this.currentPlayList[this.currentPlayIndex].url);
        } else {
          this.$store.commit("setCurrentPlayIndex", 0);
          this.toPlay(this.currentPlayList[0].url);
        }
      }
    },
    // 选中播放
    toPlay(url) {
      if (url && url !== this.songUrl) {
        const song = this.currentPlayList[this.currentPlayIndex];
        this.playMusic({
          musicId: song.musicId,
          musicName: song.musicName,
          singerName: "IU",
          compose: song.compose,
          index: 0,
          musicImage: song.imagePath,
          audio: song.audio,
          musicList: [],
        })
      }
    },
    goPlayerPage() {
      this.routerManager(RouterName.Lyric, {path: `${RouterName.Lyric}/${this.songId}`});
    },
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/yin-play-bar.scss";
</style>
