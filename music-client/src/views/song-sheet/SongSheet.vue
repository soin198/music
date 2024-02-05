<template>
  <div class="play-list-container">
    <yin-nav :styleList="musicTypes" :activeName="activeName" @click="handleChangeView"></yin-nav>
    <play-list :playList="data" path="song-sheet-detail"></play-list>
    <el-pagination
        class="pagination"
        background
        layout="total, prev, pager, next"
        :current-page="page"
        :page-size="pageSize"
        :total="musicTrees.length"
        @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, computed} from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import {SONGSTYLE} from "@/enums";
import {HttpManager} from "@/api";

export default defineComponent({
  components: {
    YinNav,
    PlayList,
  },
  setup() {
    const activeName = ref("全部歌单");
    // 当前页
    const page = ref(1);
    // 每页条数
    const pageSize = ref(15);
    // 歌单类型
    const musicTypes = ref(SONGSTYLE); // 歌单导航栏类别
    // 歌单列表
    const musicTrees = ref([]); // 歌单
    const data = computed(() => musicTrees.value.slice((page.value - 1) * pageSize.value, page.value * pageSize.value));

    // 获取全部歌单
    async function getSongList() {
      musicTrees.value = ((await HttpManager.getSongList("CHINESE")) as ResponseBody).data;
      page.value = 1;
    }

    // 通过类别获取歌单
    async function getSongListOfStyle(style) {
      musicTrees.value = ((await HttpManager.getSongListOfStyle(style)) as ResponseBody).data;
      page.value = 1;
    }

    // 获取歌单
    async function handleChangeView(item) {
      activeName.value = item.name;
      musicTrees.value = [];
      if (item.name === "全部歌单") {
        await getSongList();
      } else {
        await getSongListOfStyle(item.name);
      }
    }

    // 获取当前页
    function handleCurrentChange(val) {
      page.value = val;
    }

    return {
      activeName,
      musicTypes,
      pageSize,
      page,
      musicTrees,
      data,
      handleChangeView,
      handleCurrentChange,
    };
  },
});
</script>
