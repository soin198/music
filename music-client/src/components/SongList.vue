<template>
  <div class="content">
    <el-table highlight-current-row :data="dataList" @row-click="openMusic">
      <el-table-column prop="musicName" label="歌曲"/>
      <el-table-column prop="singerName" label="歌手"/>
      <el-table-column prop="resume" label="专辑"/>
      <el-table-column label="编辑" width="80" align="center">
        <template #default="scope">
          <el-dropdown>
            <el-icon @click="handleEdit(scope.row)">
              <MoreFilled/>
            </el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item
                    :icon="Download"
                    @click="
                    downloadMusic({
                      songUrl: scope.row.url,
                      songName: scope.row.name,
                    })
                  ">下载
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import {defineComponent, toRefs, computed} from "vue";
import {useStore} from "vuex";
import mixin from "@/mixins/mixin";
import {MoreFilled, Delete, Download} from "@element-plus/icons-vue";

export default defineComponent({
  components: {
    MoreFilled,
  },
  props: {
    songList: Array,
    show: {
      default: false
    }
  },
  emits: ["changeData"],
  setup(props) {
    const store = useStore();
    const {playMusic, downloadMusic} = mixin();
    const {songList} = toRefs(props);

    const songUrl = computed(() => store.getters.songUrl);
    const singerName = computed(() => store.getters.singerName);
    const songTitle = computed(() => store.getters.songTitle);
    const dataList = computed(() => {
      const list = [];
      songList.value.forEach((item: any, index) => {
        item["index"] = index;
        list.push(item);
      });
      return list;
    });

    function openMusic(music) {
      console.log(singerName.value)
      playMusic({
        //音乐ID
        musicId: music.musicId,
        //音乐名称
        musicName: music.musicName,
        //歌手名称
        singerName: singerName.value,
        //歌词
        compose: music.compose,
        //索引
        index: 1,
        //歌曲图片
        musicImage: music.imagePath,
        //歌曲音频
        audio: music.audio,
        //当前歌曲列表
        musicList: songList.value
      });
    }

    function handleEdit(row) {
      console.log(11111);
    }

    return {
      dataList,
      Delete,
      Download,
      songUrl,
      singerName,
      songTitle,
      openMusic,
      handleEdit,
      downloadMusic,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.content {
  background-color: $color-white;
  border-radius: $border-radius-songlist;
  padding: 10px;
}

.content:deep(.el-table__row.current-row) {
  color: $color-black;
  font-weight: bold;
}

.content:deep(.el-table__row) {
  cursor: pointer;
}

.icon {
  @include icon(1.2em, $color-black);
}
</style>
