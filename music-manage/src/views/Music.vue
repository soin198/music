<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" @click="centerDialogVisible = true">添加歌手</el-button>
    </div>
    <el-table height="550px" size="small" :data="data">
      <el-table-column label="歌曲图片" :width="150" align="center">
        <template v-slot="scope">
          <div class="singer-img">
            <img :src="scope.row.image" :width="50" :height="50"/>
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
          <el-button @click="editRow(scope.row)">编辑</el-button>
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
  </div>

  <!-- 添加 -->
  <el-dialog title="添加歌手" v-model="centerDialogVisible">
    <el-form label-width="80px" :model="registerForm" :rules="singerRule">
      <el-form-item label="歌手名" prop="name">
        <el-input v-model="registerForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="registerForm.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">保密</el-radio>
          <el-radio :label="2">组合</el-radio>
          <el-radio :label="3">不明</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="故乡" prop="location">
        <el-input v-model="registerForm.location"></el-input>
      </el-form-item>
      <el-form-item label="出生日期" prop="birth">
        <el-date-picker type="date" v-model="registerForm.birth"></el-date-picker>
      </el-form-item>
      <el-form-item label="歌手介绍" prop="introduction">
        <el-input type="textarea" v-model="registerForm.introduction"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addsinger">确 定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 编辑弹出框 -->
  <el-dialog title="编辑" v-model="editVisible">
    <el-form label-width="60px" :model="editForm" :rules="singerRule">
      <el-form-item label="歌手" prop="name">
        <el-input v-model="editForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="editForm.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">保密</el-radio>
          <el-radio :label="2">组合</el-radio>
          <el-radio :label="3">不明</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生" prop="birth">
        <el-date-picker type="date" v-model="editForm.birth"></el-date-picker>
      </el-form-item>
      <el-form-item label="地区" prop="location">
        <el-input v-model="editForm.location"></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="introduction">
        <el-input type="textarea" v-model="editForm.introduction"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 删除提示框 -->
  <yin-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></yin-del-dialog>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance, ref, reactive, onMounted} from "vue";
import YinDelDialog from "@/components/dialog/YinDelDialog.vue";
import {HttpManager} from "@/api/index";
import {getBirth} from "@/utils";
import {MusicManager} from "@/api/music"

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
    const {proxy} = getCurrentInstance();
    onMounted(() => {
      page(1);
    })

    /**
     * 获取歌曲分页列表
     * @param val 当前页码
     */
    async function page(val) {
      params.page = val
      const {code, items} = (await MusicManager.page(params)) as Response;
      if (code === 200) {
        data.value = items.data
        totalRows.value = items.totalRows
      }
    }

    // 获取当前页
    function changeVal(val) {
      page(val);
    }

    function uploadUrl(id) {
      return HttpManager.attachImageUrl(`/singer/avatar/update?id=${id}`);
    }


    /**
     * 添加
     */
    const centerDialogVisible = ref(false);
    const registerForm = reactive({
      name: "",
      sex: "",
      birth: new Date(),
      location: "",
      introduction: "",
    });
    const singerRule = reactive({
      name: [{required: true, trigger: "change"}],
      sex: [{required: true, trigger: "change"}],
    });

    /**
     * 编辑
     */
    const editVisible = ref(false);
    const editForm = reactive({
      id: "",
      name: "",
      sex: "",
      pic: "",
      birth: new Date(),
      location: "",
      introduction: "",
    });

    function editRow(row) {
      editVisible.value = true;
      editForm.id = row.id;
      editForm.name = row.name;
      editForm.sex = row.sex;
      editForm.pic = row.pic;
      editForm.birth = row.birth;
      editForm.location = row.location;
      editForm.introduction = row.introduction;
    }

    async function saveEdit() {
      try {
        let datetime = getBirth(new Date(editForm.birth));
        let params = new URLSearchParams();
        params.append("id", editForm.id);
        params.append("name", editForm.name);
        params.append("sex", editForm.sex);
        params.append("birth", datetime);
        params.append("location", editForm.location);
        params.append("introduction", editForm.introduction);

        const result = (await HttpManager.updateSingerMsg(params)) as ResponseBody;
        (proxy as any).$message({
          message: result.message,
          type: result.type,
        });

        if (result.success) page(1);
        editVisible.value = false;
      } catch (error) {
        console.error(error);
      }
    }

    /**
     * 删除
     */
    const idx = ref(-1); // 记录当前要删除的行
    const multipleSelection = ref([]); // 记录当前要删除的列表
    const delVisible = ref(false); // 显示删除框

    async function confirm() {
      const result = (await HttpManager.deleteSinger(idx.value)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result.success) page(1);
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
      centerDialogVisible,
      editVisible,
      delVisible,
      registerForm,
      editForm,
      singerRule,
      deleteAll,
      attachImageUrl: HttpManager.attachImageUrl,
      getBirth,
      uploadUrl,
      editRow,
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
