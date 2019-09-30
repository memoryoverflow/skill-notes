<template>
  <div class="Write">
    <div class="form">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="标题">
          <el-input v-model="article.title" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item label="上级目录">
          <el-select v-model="parentId" placeholder="目录">
            <el-option
              v-for="(item,index) in menus"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="序号">
          <el-input class="sort" v-model="menu.sort" placeholder="序号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click.native="save(-1,false)" type="primary">草稿</el-button>
          <el-button @click.native="save(1,false)" type="primary">保存</el-button>
          <el-button @click.native="save(1,true)" type="primary">保存并返回</el-button>
          <el-button @click.native="$router.go(-1)" type="primary">取消返回</el-button>
          <span v-text="`&emsp;`"></span>
        </el-form-item>
      </el-form>
    </div>
    <mavon-editor
      @imgAdd="imgAdd"
      @imgDel="imgDel"
      @save="saveImg"
      :subfield="true"
      :ishljs="true"
      ref="md"
      v-model="article.content"
      @keyup.65="altV"
    ></mavon-editor>
  </div>
</template>

<script>
export default {
  data() {
    return {
      saveOrUpdate: true, // true 新增
      article: {
        id: "",
        title: "",
        content: "",
        status: 1
      },
      menu: {
        id: "",
        name: "",
        parentId: "",
        sort: 99
      },
      parentId: 0,

      // 所有一级菜单
      menus: [{ id: 0, name: "无" }],
      url: {
        param: {
          id: this.$route.params.id
        },
        link: this.$route
      },
      scroll: "",
      screenWidth: 0
    };
  },
  created() {
    if (this.url.param.id != undefined) {
      this.saveOrUpdate = false;
      this.getKnow(this.url.param.id);
    } else {
      this.getMenus(this.menu.id);
    }
  },
  mounted() {
    window.addEventListener("scroll", this.screenSroll);
    window.onresize = () => {
      return (() => {
        _this.onresize();
      })();
    };
  },
  methods: {
    onresize() {
      _this.screenWidth = document.body.clientWidth;
    },
    screenSroll() {
      this.scroll =
        document.documentElement.scrollTop || document.body.scrollTop;
      console.log(this.scroll);
      if (this.scroll > 70) {
      }
    },
    save(status, isReturn) {
      var _this = this;
      this.article.status = status;
      var param = {
        title: _this.article.title,
        content: _this.article.content,
        menuName: _this.article.title,
        menuSort: _this.menu.sort,
        menuParentId: _this.parentId,
        status: status
      };

      if (_this.parentId == 0) {
        _this.$notify.error("请选择目录");
        return;
      }

      var url = "";
      if (this.saveOrUpdate) {
        url = "/know/save";
      } else {
        param.articleId = _this.article.id;
        param.menuId = _this.menu.id;
        url = "/know/update";
      }
      _this.$post(url, param).then(res => {
        if (res.code == 0) {
          _this.$notify.success({
            message: "已保存"
          });

          if (isReturn) {
            _this.$router.go(-1);
          }
          // 保存成功后切换为更改
          if (this.saveOrUpdate) {
            _this.saveOrUpdate = false;
            _this.article.id = res.data.id;
            _this.menu.id = res.data.menuId;
          }
        }
      });
    },
    getMenus(excludeId) {
      var _this = this;
      _this.$get("/menu/list", { excludeId: excludeId }).then(res => {
        if (res.code == 0) {
          res.data.forEach(element => {
            _this.menus.push(element);
          });
        }
      });
    },
    getKnow(id) {
      var _this = this;
      _this.$get("/know/selectById/" + id, {}).then(res => {
        if (res.code == 0) {
          _this.menu = res.data.menu;
          _this.parentId = _this.menu.parentId;
          _this.article = res.data.knowledge;
        }
        _this.getMenus(_this.menu.id);
      });
    },

    // 绑定@imgAdd event
    imgAdd(pos, $file) {
      var _this = this;
      var formdata = new FormData();
      formdata.append("file", $file);
      _this.$filePost("/upload/editor/img", formdata).then(res => {
        _this.$refs.md.$img2Url(pos, res.data);
      });
    },
    imgDel(file) {},
    saveImg(value, render) {
      console.log(value);
    }
  }
};
</script>

<style>
.Write {
  padding: 0 7%;
  height: 100vh;
}
.Write .form {
  padding: 20px 0 0 0;
  position: fixed;
  width: 100%;
  background: white;
  z-index: 1999;
}
.v-note-wrapper .v-note-op.shadow {
  width: 86%;
}
.sort {
  width: 70px;
}
/* 导航条的 不让它扶起来 */
.Write .v-note-wrapper .Write .v-note-op.shadow,
.Write .v-note-op,
.Write .shadow {
  box-shadow: none !important;
  border: 1px solid #ebebeb !important;
}
.Write .v-note-wrapper {
  position: relative;
  top: 100px;
}
.Write .v-note-op {
  width: 86%!important;
  position: fixed !important;
  top: 82px !important;
}
.Write .v-note-show {
  border-right: 1px solid #ebebeb !important;
  border-left: 1px solid #ebebeb !important;
  border-bottom: 1px solid #ebebeb !important;
}
.Write .v-note-panel {
  height: 50%;
  border-left: 1px solid #ebebeb !important;
}
.Write .content-input-wrapper,
.Write .v-show-content {
  height: 100%;
  padding: 50px 25px 15px 25px !important;
}
</style>
