<template>
  <div class="container">
    <el-form :inline="true" size="small">
      <el-form-item label="歌曲名称">
        <el-input v-model.trim="params.musicName" placeholder="请输入歌曲名称" clearable/>
      </el-form-item>
      <el-form-item label="歌手名称">
        <el-input v-model.trim="params.singerName" placeholder="请输入歌手名称" clearable/>
      </el-form-item>
      <el-button type="primary" size="small" @click="page(1)">查询</el-button>
    </el-form>
    <el-table size="small"
              :data="data"
              v-loading="loading"
              element-loading-text="加载中...">
      <el-table-column label="歌曲图片" :width="150" align="center">
        <template v-slot="scope">
          <div class="singer-img">
            <img :src="scope.row.image" :width="50" :height="50" alt="加载失败..."/>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="歌手" prop="singerName" :width="150" align="center"/>
      <el-table-column label="歌曲名称" prop="musicName" :width="150" align="center"/>
      <el-table-column label="专辑" :width="150">
        <template v-slot="scope">
          <el-row>
            <el-col :span="20">
              <div class="text-ellipsis">{{ scope.row.resume }}</div>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="操作" :width="150" align="center">
        <template v-slot="scope">
          <el-button type="danger" @click="deleteRow(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        class="pagination"
        background
        layout="total, prev, pager, next"
        :current-page="params.page"
        :page-size="params.pageSize"
        :total="totalRows"
        @current-change="changeVal"
    >
    </el-pagination>

    <!-- 添加歌曲 -->
    <el-dialog></el-dialog>
  </div>
  <!-- 删除提示框 -->
  <yin-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"/>
</template>

<script lang="ts">
import {defineComponent, ref, reactive, onMounted} from "vue";
import YinDelDialog from "@/components/dialog/YinDelDialog.vue";
import {HttpManager} from "@/api/index";
import {getBirth} from "@/utils";
import {MusicManager} from "@/api/music"
import {Message} from "@/message/CustomMessage"

export default defineComponent({
  components: {
    YinDelDialog,
  },
  setup() {
    const params = reactive({
      page: 1,
      pageSize: 5,
      musicName: null,
      singerName: null
    })
    const data = ref([]);
    const totalRows = ref(0);
    const loading = ref(false)
    const delVisible = ref(false)
    onMounted(() => {
      page(1);
    })

    /**
     * 获取歌曲分页列表
     * @param val 当前页码
     */
    async function page(val) {
      loading.value = true
      params.page = val
      const {code, items} = (await MusicManager.page(params)) as Response;
      if (code === 200) {
        data.value = items.data
        totalRows.value = items.totalRows
      }
      loading.value = false
    }

    // 获取当前页
    function changeVal(val) {
      page(val);
    }

    /**
     * 删除
     */
    const idx = ref(-1); // 记录当前要删除的行
    const multipleSelection = ref([]); // 记录当前要删除的列表

    async function confirm() {
      const {code, message} = (await HttpManager.deleteSinger(idx.value)) as Response;
      if (code === 200) {
        Message.success("禁用成功")
        await page(1);
      } else {
        Message.error(message)
      }
      delVisible.value = false;
    }

    function deleteRow(id) {
      idx.value = id;
      delVisible.value = true;
    }

    function deleteAll() {
      for (let item of multipleSelection.value) {
        deleteRow(item.id);
        confirm();
      }
      multipleSelection.value = [];
    }

    return {
      data,
      totalRows,
      params,
      page,
      loading,
      delVisible,
      deleteAll,
      getBirth,
      changeVal,
      confirm,
      deleteRow,
    };
  },
});
</script>

<style scoped>
.singer-img {
  width: 100%;
  height: 50px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}

.text-ellipsis {

  width: 50%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
