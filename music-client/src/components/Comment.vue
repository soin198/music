<template>
  <div class="comment">
    <h2 class="comment-title">
      <span>评论</span>
      <span class="comment-desc">共 {{ totalRows.value }} 条评论</span>
    </h2>
    <el-input class="comment-input" type="content" placeholder="期待您的精彩评论..." :rows="2" v-model="submitParams.content"/>
    <el-button class="sub-btn" type="primary" @click="submitComment()">发表评论</el-button>
  </div>
  <ul class="popular">
    <li v-for="(item, index) in commentList" :key="index">
      <el-image class="popular-img" fit="contain" :src="xx"/>
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.username }}</li>
          <li class="time">{{ formatDate(item.createTime) }}</li>
          <li class="content">{{ item.content }}</li>
        </ul>
      </div>
      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, index)">
        <div>
          <yin-icon :icon="iconList.Support"></yin-icon>
          {{ item.up }}
        </div>
        <el-icon v-if="item.userId === userId" @click="deleteComment(item.id, index)">
          <delete/>
        </el-icon>
      </div>
    </li>
  </ul>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance, ref, toRefs, computed, watch, reactive, onMounted} from "vue";
import {useStore} from "vuex";
import {Delete} from "@element-plus/icons-vue";
import YinIcon from "@/components/layouts/YinIcon.vue";
import mixin from "@/mixins/mixin";
import {HttpManager} from "@/api";
import {Icon} from "@/enums";
import {formatDate} from "@/utils";
import {CommentManager} from "@/api/comment";
import {ElMessage} from "element-plus";

export default defineComponent({
  components: {
    YinIcon,
    Delete,
  },
  props: {
    // 歌曲ID或歌单ID
    playId: Number || String,
    // 歌单（1）/歌曲（0）
    type: Number,
  },
  setup(props) {

    const {proxy} = getCurrentInstance();
    const store = useStore();
    const {checkStatus} = mixin();

    const {playId, type} = toRefs(props);
    const totalRows = ref(0);
    const pageQuery = reactive({
      page: 1,
      pageSize: 10,
      musicId: null
    })
    const submitParams = reactive({
      userId: null,
      musicId: null,
      content: ""
    })


    // 存放评论内容
    const commentList = ref([]);
    const iconList = reactive({
      Support: Icon.Support,
    });
    const userId = computed(() => store.getters.userId);
    const songId = computed(() => store.getters.songId);
    watch(songId, () => {
      commentGet();
    });

    /**
     * 获取所有歌曲评论
     */
    async function commentGet() {
      pageQuery.musicId = songId.value
      const {code, items, message} = (await CommentManager.page(pageQuery)) as Response;
      if (code === 200) {
        totalRows.value = items.totalRows
        commentList.value = items.data;
      } else {
        ElMessage.error(message);
      }
    }

    /**
     * 提交评论
     */
    async function submitComment() {
      if (!checkStatus()) return;
      submitParams.musicId = `${playId.value}`
      submitParams.userId = userId.value
      const {code, message} = (await CommentManager.submit(submitParams)) as Response;
      if (code === 200) {
        submitParams.content = "";
        ElMessage.success("评论成功~")
        await commentGet();
      } else {
        ElMessage.error(message)
      }
    }

    // 删除评论
    async function deleteComment(id, index) {
      const result = (await HttpManager.deleteComment(id)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });

      if (result.success) commentList.value.splice(index, 1);
    }

    // 点赞
    async function setSupport(id, up, index) {
      if (!checkStatus()) return;

      const params = new URLSearchParams();
      params.append("id", id);
      params.append("up", up + 1);

      const result = (await HttpManager.setSupport(params)) as ResponseBody;
      if (result.success) {
        await commentGet();
      }
    }

    onMounted(() => {
      commentGet();
    });

    return {
      submitParams,
      totalRows,
      userId,
      commentList,
      iconList,
      attachImageUrl: HttpManager.attachImageUrl,
      submitComment,
      setSupport,
      formatDate,
      deleteComment,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

/*评论*/
.comment {
  position: relative;
  margin-bottom: 60px;

  .comment-title {
    height: 50px;
    line-height: 50px;

    .comment-desc {
      font-size: 14px;
      font-weight: 400;
      color: $color-grey;
      margin-left: 10px;
    }
  }

  .comment-input {
    display: flex;
    margin-bottom: 20px;
  }

  .sub-btn {
    position: absolute;
    right: 0;
  }
}

/*热门评论*/
.popular {
  width: 100%;

  > li {
    border-bottom: solid 1px rgba(0, 0, 0, 0.1);
    padding: 15px 0;
    display: flex;

    .popular-img {
      width: 50px;
    }

    .popular-msg {
      padding: 0 20px;
      flex: 1;

      li {
        width: 100%;
      }

      .time {
        font-size: 0.6rem;
        color: rgba(0, 0, 0, 0.5);
      }

      .name {
        color: rgba(0, 0, 0, 0.5);
      }

      .content {
        font-size: 1rem;
      }
    }

    .comment-ctr {
      display: flex;
      align-items: center;
      width: 80px;
      font-size: 1rem;
      cursor: pointer;

      .el-icon {
        margin: 0 10px;
      }

      &:hover,
      :deep(.icon):hover {
        color: $color-grey;
      }
    }
  }
}

.icon {
  @include icon(1em);
}
</style>
