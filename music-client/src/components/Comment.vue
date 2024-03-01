<template>
  <div class="comment">
    <h2 class="comment-title">
      <span>评论</span>
      <span class="comment-desc">共 {{ totalRows }} 条评论</span>
    </h2>
    <el-input class="comment-input" type="content" placeholder="期待您的精彩评论..." :rows="2" v-model="submitParams.content"/>
    <el-button class="sub-btn" type="primary" @click="submitComment()">发表评论</el-button>
  </div>
  <ul class="popular">
    <li v-for="(comment, index) in commentList" :key="index">
      <el-image class="popular-img" fit="contain" :src="comment.userPic"/>
      <div class="popular-msg">
        <ul>
          <li class="name">{{ comment.nickName }}</li>
          <li class="time">{{ formatDate(comment.createTime) }}</li>
          <li class="content">{{ comment.content }}</li>
        </ul>
      </div>
      <div ref="up" class="comment-ctr" @click="clickLike(comment.commentId)">
        <div>
          <yin-icon :icon="iconList.Support"></yin-icon>
          {{ comment.click }}
        </div>
        <el-icon v-if="comment.userId === userId" @click="omit(comment.commentId)">
          <delete/>
        </el-icon>
      </div>
    </li>
  </ul>
</template>

<script lang="ts">
import {defineComponent, ref, toRefs, computed, watch, reactive, onMounted} from "vue";
import {useStore} from "vuex";
import {Delete} from "@element-plus/icons-vue";
import YinIcon from "@/components/layouts/YinIcon.vue";
import mixin from "@/mixins/mixin";
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
    playId: Number || String
  },
  setup(props) {
    const store = useStore();
    const {checkStatus} = mixin();
    const {playId} = toRefs(props);
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

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     */
    async function omit(commentId) {
      const {code, message} = (await CommentManager.omit(commentId)) as Response;
      if (code === 200) {
        await commentGet();
      } else {
        ElMessage.error(message)
      }
    }

    /**
     * 对评论点赞
     *
     * @param commentId 评论ID
     */
    async function clickLike(commentId) {
      if (!checkStatus()) {
        return;
      }
      const {code, message} = (await CommentManager.clickLike(commentId)) as Response;
      if (code === 200) {
        await commentGet();
      } else {
        ElMessage.error(message)
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
      submitComment,
      clickLike,
      formatDate,
      omit,
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
