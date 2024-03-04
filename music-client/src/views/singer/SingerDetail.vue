<template>
  <el-container>
    <el-aside class="album-slide">
      <el-image class="singer-img" fit="contain" :src="singer.singerPic"/>
      <div class="album-info">
        <h2>基本资料</h2>
        <ul>
          <li v-if="singer.sex === 'MALE'">性别：男</li>
          <li v-if="singer.sex === 'WOMEN'">性别：女</li>
          <li>生日：{{ singer.birth }}</li>
          <li>故乡：{{ singer.nationality }}</li>
        </ul>
      </div>
    </el-aside>
    <el-main class="album-main">
      <h1>{{ singer.name }}</h1>
      <p>{{ singer.resume }}</p>
      <song-list :songList="data"/>
    </el-main>
  </el-container>
</template>

<script lang="ts">
import {defineComponent, ref, computed, onMounted, reactive} from "vue";
import {useStore} from "vuex";
import mixin from "@/mixins/mixin";
import SongList from "@/components/SongList.vue";
import {HttpManager} from "@/api";
import {SingerManager} from "@/api/singer";
import {MusicManager} from "@/api/music"
import {ElMessage} from "element-plus";

export default defineComponent({
  components: {
    SongList,
  },
  setup() {

    const store = useStore();
    const data = ref([]);
    const totalRows = ref(0);
    const singer = ref({
      id: null,
      name: null,
      singerPic: null,
      sex: null,
      birth: null,
      nationality: null,
      resume: null
    })
    const params = reactive({
      page: 1,
      pageSize: 10,
      singerId: null
    })
    const singerId = computed(() => store.getters.singerId) as any;
    params.singerId = singerId.value;

    async function singerQueryById() {
      const {code, items, message} = (await SingerManager.singerQueryById(singerId.value)) as Response;
      if (code === 200) {
        singer.value = items;
      } else {
        ElMessage.error(message)
      }
    }

    async function page() {
      const {code, items, message} = (await MusicManager.page(params)) as Response;
      if (code === 200) {
        totalRows.value = items.totalRows;
        data.value = items.data;
      } else {
        ElMessage.error(message)
      }
    }

    onMounted(async () => {
      await Promise.all([page(), singerQueryById()]);
    });

    return {
      data,
      attachImageUrl: HttpManager.attachImageUrl,
      singer
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.album-slide {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;

  .singer-img {
    height: 250px;
    width: 250px;
    border-radius: 10%;
  }

  .album-info {
    width: 60%;
    padding-top: 2rem;

    li {
      width: 100%;
      height: 30px;
      line-height: 30px;
    }
  }
}

.album-main {
  p {
    color: rgba(0, 0, 0, 0.5);
    margin: 10px 0 20px 0px;
  }
}

@media screen and (min-width: $sm) {
  .album-slide {
    position: fixed;
    width: 400px;
  }
  .album-main {
    min-width: 600px;
    padding-right: 10vw;
    margin-left: 400px;
  }
}

@media screen and (max-width: $sm) {
  .album-slide {
    display: none;
  }
}
</style>
