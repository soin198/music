<template>
  <div class="container">
    <el-table height="550px" :data="data">
      <el-table-column label="用户名" prop="username" width="80" align="center"/>
      <el-table-column label="用户头像" width="102" align="center">
        <template v-slot="scope">
          <img :src="scope.row.photo" style="width: 80px"/>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="100px" align="center">
        <template v-slot="scope">
          <div v-if="scope.row.sex === 'MALE'">男</div>
          <div v-if="scope.row.sex === 'WOMEN'">女</div>
          <div v-if="scope.row.sex === 'OTHER'">其他</div>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号码" width="120" align="center"/>
      <el-table-column prop="email" label="邮箱" width="200" align="center"/>
      <el-table-column label="生日" width="150" align="center">
        <template v-slot="scope">
          <div>{{ getBirth(scope.row.birth) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="签名" width="300">
        <template v-slot="scope">
          <el-row>
            <el-col :span="20">
              <div class="text-ellipsis">{{ scope.row.resume}}</div>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="90" align="center">
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
        @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>

  <!-- 删除提示框 -->
  <yin-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></yin-del-dialog>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance, watch, ref, reactive, onMounted} from "vue";
import mixin from "@/mixins/mixin";
import {HttpManager} from "@/api";
import {RouterName} from "@/enums";
import YinDelDialog from "@/components/dialog/YinDelDialog.vue";
import {getBirth} from "@/utils";
import {UserManager} from "@/api/custom"

export default defineComponent({
  components: {
    YinDelDialog,
  },
  setup() {
    const params = reactive({
      page: 1,
      pageSize: 10
    })
    const data = ref([]);
    const totalRows = ref(0);

    const {proxy} = getCurrentInstance();
    const {routerManager} = mixin();

    async function page(val) {
      params.page = val;
      const {code, items} = (await UserManager.page(params)) as Response;
      if (code === 200) {
        data.value = items.data
        totalRows.value = items.totalRows;
      }
    }

    onMounted(() => {
      page(1);
    })

    // 获取当前页
    function handleCurrentChange(val) {
      params.page = val;
    }

    /**
     * 路由
     */
    function goCollectPage(id) {
      const breadcrumbList = reactive([
        {
          path: RouterName.Consumer,
          name: "用户管理",
        },
        {
          path: "",
          name: "收藏信息",
        },
      ]);
      proxy.$store.commit("setBreadcrumbList", breadcrumbList);
      routerManager(RouterName.Collect, {path: RouterName.Collect, query: {id}});
    }

    /**
     * 删除
     */
    const idx = ref(-1); // 记录当前要删除的行
    const delVisible = ref(false); // 显示删除框

    async function confirm() {
      const result = (await HttpManager.deleteUser(idx.value)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result) page(1);
      delVisible.value = false;
    }

    function deleteRow(id) {
      idx.value = id;
      delVisible.value = true;
    }

    return {
      data,
      totalRows,
      params,
      delVisible,
      handleCurrentChange,
      getBirth,
      deleteRow,
      confirm,
      attachImageUrl: HttpManager.attachImageUrl,
    };
  },
});
</script>

<style scoped>

.text-ellipsis {

  width: 50%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

</style>
