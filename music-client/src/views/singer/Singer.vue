<template>
  <div class="play-list-container">
    <yin-nav :styleList="SingerType" :activeName="activeName" @click="handleChangeView"></yin-nav>
    <play-list :playList="data" path="singer-detail"></play-list>
    <el-pagination
        class="pagination"
        background
        layout="total, prev, pager, next"
        :current-page="params.page"
        :page-size="params.pageSize"
        :total="totalRows"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import {SingerType} from "@/enums";
import {SingerManager} from "@/api/singer";
import {ElMessage} from "element-plus";
const { proxy } = getCurrentInstance();

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

</script>
