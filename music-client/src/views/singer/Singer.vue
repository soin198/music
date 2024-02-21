<template>
  <div class="play-list-container">
    <yin-nav :styleList="SingerType" :activeName="activeName" @click="handleChangeView"></yin-nav>

    <div class="play-list">
      <div class="play-title" v-if="title">{{ 1111 }}</div>
      <ul class="play-body">
        <li class="card-frame" v-for="(item, index) in data" :key="index">
          <div class="card" @click="goAblum(item)">
            <img class="card-img" :src="item.base64" alt="图片">
            <div class="mask" @click="goAblum(item)">
              <yin-icon class="mask-icon" :icon="BOFANG"></yin-icon>
            </div>
          </div>
          <p class="card-name">{{ item.name || item.title }}</p>
        </li>
      </ul>
    </div>

    <play-list :playList="data" path="singer-detail"></play-list>
    <el-pagination
        class="pagination"
        background
        layout="total, prev, pager, next"
        :current-page="params.page"
        :page-size="params.pageSize"
        :total="totalRows"
        @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import {SingerType} from "@/enums";
import {SingerManager} from "@/api/singer";
import {ElMessage} from "element-plus";
import mixin from "@/mixins/mixin";
const { proxy } = getCurrentInstance();
const { routerManager } = mixin();

const activeName = ref("全部歌手");
const totalRows = ref(0);
const data = ref([]);
const params = reactive({
  page: 1,
  pageSize: 10,
  singerName: null,
  singerType: null
})

// 获取歌手分页列表
async function singerQuery() {
  const {code, items, message} = (await SingerManager.singerRandomQuery(params)) as Response;
  if (code === 200) {
    data.value = items.data;
    totalRows.value = items.totalRows
  } else {
    ElMessage.error(message)
  }
}

onMounted(() => {
  singerQuery();
});

// 切换页码
function handleCurrentChange(val) {
  params.page = val;
  singerQuery();
}

function handleChangeView(item) {
  params.singerType = item.type
  activeName.value = item.name;
  data.value = [];
  params.page = 1;
  singerQuery();
}

function goAblum(item) {
  proxy.$store.commit("setSongDetails", item);
  routerManager(path.value, { path: `/${path.value}/${item.id}` });
}


</script>
