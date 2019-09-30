<template>
  <div class="Home" v-loading="pageLoading">
    <!-- 头部导航栏 -->
    <el-row class="header-row">
      <!-- 屏幕变小后的左侧菜单按钮 -->
      <div class="leftMuen-btn" v-if="navShow">
        <span class="menu-btn" @click="showAside()">
          <i class="el-icon-s-unfold"></i>
        </span>
      </div>

      <!-- 头部导航菜单 -->
      <el-col :span="rowOneCol.leftSpan" :style="`text-align:`+rowOneCol.navAlign">
        <div class="logo">
          <router-link :to="`/`">
            <h2>
              <img src="/static/favicon.ico" />
              {{msg}}
            </h2>
          </router-link>
        </div>

        <div class="nav-right" v-if="!navShow">
          <ul>
            <li>
              <b>
                <router-link :to="URL.home">Home</router-link>
              </b>
            </li>
            <li>
              <b>
                <a target="_blank" :href="URL.blogUrl">Blog</a>
              </b>
            </li>
            <li>
              <b>
                <a :href="URL.github" target="_blank">GitHub</a>
              </b>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
                x="0px"
                y="0px"
                viewBox="0 0 100 100"
                width="20"
                height="20"
                class="icon outbound"
              >
                <path
                  fill="currentColor"
                  d="M18.8,85.1h56l0,0c2.2,0,4-1.8,4-4v-32h-8v28h-48v-48h28v-8h-32l0,0c-2.2,0-4,1.8-4,4v56C14.8,83.3,16.6,85.1,18.8,85.1z"
                />
                <polygon
                  fill="currentColor"
                  points="45.7,48.7 51.3,54.3 77.2,28.5 77.2,37.2 85.2,37.2 85.2,14.9 62.8,14.9 62.8,22.9 71.5,22.9"
                />
              </svg>
            </li>
            <li>
              <div class="search-li">
                <span>
                  <i class="el-icon-search"></i>
                </span>
                <el-autocomplete
                  style="border-radius: 20px"
                  :class="[inputFocusClass]"
                  @focus="inputFocus()"
                  :fetch-suggestions="querySearchAsync"
                  @select="handleSelect"
                  placeholder="关键字"
                  v-model="searchValue"
                ></el-autocomplete>
              </div>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>

    <!-- 内容 -->
    <el-row>
      <!-- 左侧菜单 -->
      <el-col :class="['aside']" :span="rowTwoCol.asideSpan">
        <el-aside style="width:100%">
          <el-row v-if="isAdmin" style="width:100%">
            <el-col class="operate" :span="2">
              <el-link
                @click="logout"
                type="primary"
                :underline="false"
                icon="el-icon-switch-button"
              ></el-link>
            </el-col>
            <el-col :span="22" class="operate">
              <el-link @click="publish" type="primary" :underline="false" icon="el-icon-plus"></el-link>
              <el-link
                @click="showAddFloder"
                type="primary"
                :underline="false"
                icon="el-icon-folder-opened"
              ></el-link>
            </el-col>
          </el-row>
          <el-row style="width:100%">
            <el-col :span="24">
              <ul class="left-menu-ul" v-for="(item ,index) in leftMenuTreeData" :key="index">
                <li>
                  <div class="menu-one-li">
                    <span class="tiltle">{{item.name}}</span>
                  </div>
                  <ol
                    @click="check(son.articleId)"
                    :class="['left-menu-ul-li-ol',olSelected==son.articleId?'olCheck':'']"
                    v-for="(son,n) in item.children"
                    :key="n"
                  >{{son.name}}</ol>
                </li>
              </ul>
            </el-col>
          </el-row>
        </el-aside>
      </el-col>
      <!-- 右侧内容 -->
      <el-col :span="rowTwoCol.mainSpan">
        <el-main>
          <el-row v-if="isAdmin" class="content-row-nav">
            <el-col :span="24">
              <el-link
                @click="edit(article.id)"
                type="primary"
                :underline="false"
                icon="el-icon-edit"
              ></el-link>
              <el-link type="primary" :underline="false" icon="el-icon-share"></el-link>
              <el-link
                type="primary"
                @click="deleteArticle(article.id)"
                :underline="false"
                icon="el-icon-delete"
              ></el-link>
            </el-col>
          </el-row>
          <!-- <router-view /> -->
          <div class="Article" v-loading="contentLoading">
            <mavon-editor
              :ishljs="true"
              v-model="article.content"
              :subfield="false"
              defaultOpen="preview"
              :editable="false"
              :toolbars="toolbarView"
              :navigation="true"
              @navigationToggle="navigationToggle"
              style="border:none;padding:0"
            ></mavon-editor>
          </div>
        </el-main>
      </el-col>
      <el-col :span="rowTwoCol.right">
        <div class="rowTwoCol-right" style="width:100%">
          <div class="rowTwoCol-right-top">
            <img src="/static/timg.jpeg" />
            <div style="text-algin:left">Study hard and make progress every day</div>
          </div>
          <div class="rowTwoCol-right-nav">
            <div class="rowTwoCol-right-nav-title">
              <span>菜单</span>
            </div>
            <div class="rowTwoCol-right-nav-list"></div>
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 添加文件夹 弹框-->
    <div class="addFloader">
      <el-dialog
        :close-on-press-escape="false"
        :close-on-click-modal="false"
        :destroy-on-close="true"
        title="目录"
        close="closeFloderDialog"
        :visible.sync="showFloder"
      >
        <el-row>
          <el-col :span="8">
            <el-link
              @click="closeFloderDialog"
              type="primary"
              :underline="false"
              icon="el-icon-back"
            ></el-link>
          </el-col>
          <el-col :span="8" style="text-align:center">
            <el-link type="primary" :underline="false">目录列表</el-link>
          </el-col>
          <el-col :span="8" style="text-align:right">
            <el-link
              @click="handlerAddFloder"
              type="primary"
              :underline="false"
              :icon="addNewFloderBtnIcon"
            ></el-link>
          </el-col>
        </el-row>
        <el-table :data="menusTableData">
          <el-table-column property="name" label="目录名" width="150"></el-table-column>
          <el-table-column property="sort" label="排序"></el-table-column>
          <el-table-column property="address" label="操作">
            <template slot-scope="scope">
              <el-button @click="handleRemoveView(scope.row)" type="text" size="small">编辑</el-button>
              <el-button @click="handleRemoveMeun(scope.row.id)" type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row v-if="showNewMenu" :gutter="20">
          <el-col :span="8">
            <el-input v-model="newMenu.name" />
          </el-col>
          <el-col :span="3">
            <el-input type="number" v-model="newMenu.sort" />
          </el-col>
          <el-col :span="13">
            <el-button v-if="showSaveMenuBtn" @click="saveNewMenu" type="primary">保存</el-button>
            <el-button v-if="!showSaveMenuBtn" @click="updateNewMenu" type="primary">保存</el-button>
          </el-col>
        </el-row>
      </el-dialog>
    </div>

    <!-- 屏幕缩小的时候左侧菜单从这里显示 -->
    <el-drawer
      title="导航菜单"
      :visible.sync="drawer"
      direction="ltr"
      :before-close="handleCloseDrawer"
      size="60%"
    >
      <div style="height:100%">
        <el-scrollbar style="height:100%">
          <el-row>
            <el-col :span="24">
              <ul class="miniNav">
                <li>
                  <b>
                    <el-autocomplete
                      :class="[inputFocusClass]"
                      @focus="inputFocus()"
                      :fetch-suggestions="querySearchAsync"
                      @select="handleSelect"
                      placeholder="关键字"
                    ></el-autocomplete>
                  </b>
                </li>
                <li>
                  <b>
                    <router-link :to="URL.home">Home</router-link>
                  </b>
                </li>
                <li>
                  <b>
                    <a target="_blank" :href="`/`">Blog</a>
                  </b>
                </li>
                <li>
                  <b>GitHub</b>
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    aria-hidden="true"
                    x="0px"
                    y="0px"
                    viewBox="0 0 100 100"
                    width="20"
                    height="20"
                    class="icon outbound"
                  >
                    <path
                      fill="currentColor"
                      d="M18.8,85.1h56l0,0c2.2,0,4-1.8,4-4v-32h-8v28h-48v-48h28v-8h-32l0,0c-2.2,0-4,1.8-4,4v56C14.8,83.3,16.6,85.1,18.8,85.1z"
                    />
                    <polygon
                      fill="currentColor"
                      points="45.7,48.7 51.3,54.3 77.2,28.5 77.2,37.2 85.2,37.2 85.2,14.9 62.8,14.9 62.8,22.9 71.5,22.9"
                    />
                  </svg>
                </li>
              </ul>
            </el-col>
          </el-row>
          <el-row v-if="isAdmin" style="width:100%">
            <el-col :span="2" class="operate">
              <el-link
                @click="logout"
                type="primary"
                :underline="false"
                icon="el-icon-switch-button"
              ></el-link>
            </el-col>
            <el-col :span="22" class="operate">
              <el-link @click="publish" type="primary" :underline="false" icon="el-icon-plus"></el-link>
              <el-link
                @click="showAddFloder"
                type="primary"
                :underline="false"
                icon="el-icon-folder-opened"
              ></el-link>
            </el-col>
          </el-row>
          <el-row style="width:100%">
            <el-col :span="24">
              <ul class="left-menu-ul" v-for="(item ,index) in leftMenuTreeData" :key="index">
                <li>
                  <div class="menu-one-li">
                    <span>{{item.name}}</span>
                  </div>
                  <ol
                    @click="check(son.articleId)"
                    :class="['left-menu-ul-li-ol',olSelected==son.articleId?'olCheck':'']"
                    v-for="(son,n) in item.children"
                    :key="n"
                  >{{son.name}}</ol>
                </li>
              </ul>
            </el-col>
          </el-row>
        </el-scrollbar>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isAdmin: false,
      contentLoading: false,
      pageLoading: false,
      drawer: false,
      msg: "MemoryOverflow",
      URL: {
        home: "/home",
        github: "https://github.com/memoryoverflow",
        blogUrl: "https://www.memoryoverflow.cn/blog"
      },
      // 显示导航
      navShow: false,
      // 显示左侧菜单
      asideShow: true,
      // 显示 小导航
      minNavShow: false,
      // 是否显示菜单列表弹框
      showFloder: false,
      // 添加新的文件夹
      showNewMenu: false,
      addNewFloderBtnIcon: "el-icon-plus",
      showSaveMenuBtn: true,
      // 搜索输入框
      searchValue: "",
      // 添加文件夹
      newMenu: {
        sort: 99,
        name: "",
        parentId: 0
      },
      rowOneCol: {
        navAlign: "",
        leftSpan: 24,
        rightSpan: 16
      },
      rowTwoCol: {
        asideSpan: 4,
        mainSpan: 16,
        right: 4
      },
      window: {
        min: 650, //
        minChange: 1040, // 小于它的时候
        screenWidth: 0
      },
      inputFocusClass: {
        inputFocusClass: ""
      },
      article: {
        id: 0,
        content: ""
      },
      toolbarView: {
        navigation: true // 导航目录
      },
      // 表格数据
      menusTableData: [],
      // 左侧菜单数据
      leftMenuTreeData: [],
      // 选中菜单样式
      olSelected: "",
      r: false,
      //当前选择的文章id
      currentSelectedArticleId: ""
    };
  },
  created() {
    this.onresize();
    this.menusList();
    this.leftMenuTrees();
    this.checkIsAdminLogin();
    console.log(window.localStorage.getItem("memoryoverflow.login"))
  },
  mounted() {
    const _this = this;
    // _this.handlerTocMeun();
    this.baiduPublish();
    window.onresize = () => {
      return (() => {
        _this.onresize();
      })();
    };
  },
  // 监控屏幕宽度
  methods: {
    // 退出
    logout() {
      let _this = this;
      this.isAdmin = false;
      window.localStorage.removeItem("memoryoverflow.login");
      _this.$post("/logout", {}, function(res) {
        console.log(res);
      });
    },
    // 显示右边导航
    handlerTocMeun() {
      setTimeout(() => {
        document.querySelector(".rowTwoCol-right-nav-list").innerHTML = "";
        let ul = document
          .getElementsByClassName("v-show-content")[0]
          .getElementsByTagName("ul");
        if (ul.item(0) == null || ul.item(0) == undefined) {
        } else {
          document
            .querySelector(".rowTwoCol-right-nav-list")
            .append(ul.item(0));
        }
        console.log(ul);
      }, 500);
    },
    navigationToggle(status, value) {},
    init() {
      var olSelected = window.localStorage.getItem("olSelected");
      if (olSelected) {
        this.olSelected = olSelected;
      } else {
        this.olSelected = this.leftMenuTreeData[0].children[0].articleId;
      }
      this.check(this.olSelected);
    },
    handleCloseDrawer(done) {
      done();
    },
    leftMenuTrees() {
      this.pageLoading = true;
      var _this = this;
      _this.$get("/menu/tree", {}).then(res => {
        if (res.code == 0) {
          _this.leftMenuTreeData = res.data;
          this.pageLoading = false;
          _this.init();
        }
      });
    },
    // 获取菜单数据
    menusList() {
      var _this = this;
      _this.$get("/menu/list", {}).then(res => {
        if (res.code == 0) {
          _this.menusTableData = res.data;
          if (res.data == null || res.data.length == 0) {
            _this.$notify.warning("当前没有目录结构数据,先登录再发表吧");
          }
        }
      });
    },
    // 发布文章
    publish() {
      this.$router.push({
        path: "/write/publish"
      });
    },
    // 删除文章
    deleteArticle(id) {
      var _this = this;
      _this
        .$confirm("此操作将相关的删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          _this.$del("/know/delete/" + id, {}).then(res => {
            if (res.code == 0) {
              window.localStorage.removeItem("olSelected");
              _this.leftMenuTrees();
              _this.$notify.success(res.msg);
              // _this.check(_this.leftMenuTreeData[0].children[0].articleId);
            }
          });
        })
        .catch(() => {
          _this.$notify.info("已取消删除");
        });
    },
    // 文章编辑
    edit(id) {
      this.$router.push({
        path: "/write/edit/" + id
      });
    },

    // 编辑目录
    handleRemoveView(row) {
      var _this = this;
      this.newMenu = row;
      this.newMenu.name = row.name;
      this.newMenu.sort = row.sort;
      this.showSaveMenuBtn = false;
      this.showNewMenu = true;
      this.addNewFloderBtnIcon = "el-icon-minus";
    },
    // 删除目录
    handleRemoveMeun(id) {
      var _this = this;
      this.$confirm("此操作将相关的删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          _this.$del("/menu/delete/" + id, {}).then(res => {
            if (res.code == 0) {
              _this.$notify.success("已删除");
              _this.menusList();
            }
          });
        })
        .catch(() => {
          _this.$notify.info("已取消删除");
        });
    },
    // 弹出添加目录
    showAddFloder() {
      this.showFloder = true;
    },
    // 关闭添加文件夹的回调
    closeFloderDialog() {
      this.showFloder = false;
    },
    // 添加保存文件夹
    handlerAddFloder() {
      if (this.showNewMenu) {
        this.showNewMenu = false;
        this.addNewFloderBtnIcon = "el-icon-plus";
      } else {
        this.showNewMenu = true;
        this.addNewFloderBtnIcon = "el-icon-minus";
      }
    },
    updateNewMenu() {
      var _this = this;
      if (
        _this.newMenu.name.trim() == "" ||
        _this.newMenu.name.trim() == null
      ) {
        _this.$notify.error("名称不能为空");
        return;
      }
      _this.$put("/menu/update", _this.newMenu).then(res => {
        if (res.code == 0) {
          _this.$notify.success(res.msg);
          _this.menusList();
          _this.leftMenuTrees();
          _this.newMenu.name = "";
          _this.showNewMenu = false;
          this.addNewFloderBtnIcon = "el-icon-plus";
          _this.newMenu.name = "";
          _this.newMenu.sort = 99;
        }
      });
    },
    saveNewMenu() {
      var _this = this;
      if (
        _this.newMenu.name.trim() == "" ||
        _this.newMenu.name.trim() == null
      ) {
        _this.$notify.error("名称不能为空");
        return;
      }

      _this.$post("/menu/save", _this.newMenu).then(res => {
        if (res.code == 0) {
          _this.menusList();
          _this.leftMenuTrees();
          _this.$notify.success(res.msg);
          _this.newMenu.name = "";
          _this.showNewMenu = false;
          this.addNewFloderBtnIcon = "el-icon-plus";
        }
      });
    },
    // 点击左侧菜单
    check(articleId) {
      var _this = this;

      if (_this.currentSelectedArticleId == articleId) {
        return;
      }
      _this.currentSelectedArticleId = articleId;

      _this.contentLoading = true;
      _this.$get("/know/selectById/" + articleId, {}).then(res => {
        if (res.code == 0) {
          _this.drawer = false;
          _this.contentLoading = false;
          _this.minNavShow = false;
          _this.article = res.data.knowledge;
          if (_this.article == null) {
            _this.$notify.warning("当前没有文章内容数据");
            return;
          }
          _this.olSelected = _this.article.id;
          window.localStorage.setItem("olSelected", _this.article.id);
          // 右侧菜单
          _this.handlerTocMeun();
        }
      });
    },
    inputFocus() {},
    // 导航异步搜索
    querySearchAsync(queryString, cb) {
      var _this = this;
      _this.$get("/menu/listNePidNull", { name: queryString }).then(res => {
        if (res.code == 0) {
          var arr = res.data;
          arr.forEach(element => {
            // 非得要value字段才行
            element["value"] = element["name"];
          });
          cb(arr);
        }
      });
    },
    // 导航搜索结果 选中触发
    handleSelect(item) {
      this.check(item.articleId);
    },
    checkIsAdminLogin() {
      var flag = window.localStorage.getItem("memoryoverflow.login");
      if (flag) {
        this.isAdmin = true;
      } else {
        this.isAdmin = false;
      }
    },
    // 左侧菜单
    showAside() {
      this.drawer = true;
      if (this.asideShow) {
        // 还原
        // this.asideShow = false;
        // this.rowTwoCol.asideSpan = 6;
        // this.rowTwoCol.mainSpan = 18;
      } else {
        // this.asideShow = true;
        // this.rowTwoCol.asideSpan = 0;
        // this.rowTwoCol.mainSpan = 24;
      }
    },
    // 显示导航菜单
    showNavMuen() {
      if (this.minNavShow) {
        this.minNavShow = false;
      } else {
        this.minNavShow = true;
      }
    },
    // 控制 左侧菜单的显示与否
    onresize() {
      const _this = this;
      _this.window.screenWidth = document.body.clientWidth;
      if (_this.window.screenWidth < _this.window.minChange) {
        _this.rowOneCol.leftSpan = 24;
        _this.rowOneCol.navAlign = "center";
        _this.navShow = true;

        _this.rowTwoCol.asideSpan = 0;
        _this.rowTwoCol.mainSpan = 24;
        _this.rowTwoCol.right = 0;
      } else {
        // 还原导航
        _this.rowOneCol.leftSpan = 24;
        _this.navShow = false;
        _this.rowOneCol.navAlign = "";

        //  还原左侧菜单
        _this.rowTwoCol.asideSpan = 4;
        _this.rowTwoCol.mainSpan = 16;
        _this.rowTwoCol.right = 4;
      }
    },
    baiduPublish() {
      console.log("push");
      var bp = document.createElement("script");

      var curProtocol = window.location.protocol.split(":")[0];

      if (curProtocol === "https") {
        bp.src = "https://zz.bdstatic.com/linksubmit/push.js";
      } else {
        bp.src = "http://push.zhanzhang.baidu.com/push.js";
      }

      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(bp, s);
    }
  },
  watch: {
    $route(to, from) {}
  }
};
</script>

<style>
/* 抽屉 */
.Home .el-scrollbar {
  height: 100% !important;
}
.Home .el-scrollbar__wrap {
  overflow: scroll;
  overflow-y: auto;
}
a {
  color: inherit;
}
h2 {
  margin: 0;
  padding-left: 50px;
  line-height: 70px;
}
.operate {
  text-align: right;
  margin: 16px 0;
}
.operate .el-link {
  display: inline-block;
}
.operate .el-link,
.content-row-nav .el-link {
  font-size: 25px;
  width: 50px;
}

.menu-btn {
  line-height: 70px;
  font-size: 30px;
  padding-right: 20px;
  cursor: pointer;
}
.logo,
.nav-right {
  display: inline-block;
}
.header-row {
  background: rgb(24, 31, 38);
  color: white;
}
.nav-right {
  text-align: right;
  height: 8vh;
}
.header-row .logo img {
  width: 30px;
  vertical-align: -5px;
  margin-right: 10px;
}
.nav-right .el-input,
.miniNav .el-input {
  width: 100%;
}
.nav-right ul {
  margin: 0;
}
.nav-right a {
  color: inherit;
}
.nav-right b:hover {
  border-bottom: 2px solid green;
}
svg {
  margin-left: -5px;
  color: gray;
  position: relative;
  top: 3px;
}
.header-row {
  border-bottom: 1px solid #ebebeb;
}
.nav-right .search-li {
  border-bottom: 1px solid grey;
  height: 57px;
}
.nav-right ul li {
  cursor: pointer;
  display: inline-block;
  line-height: 70px;
  font-size: larger;
  font-family: "宋体";
  padding: 0 20px;
}
.aside {
  height: 91vh;
  border-right: none;
  overflow-y: auto;
}
.rowTwoCol-right {
  height: 91vh !important;
  overflow-y: auto;
}
.asideFloat {
  float: right;
  background: red;
}
.Home .el-main {
  height: 91vh;
}
/* 隐藏滚动条 */
.Home .el-main::-webkit-scrollbar,
.Home .rowTwoCol-right::-webkit-scrollbar,
.aside::-webkit-scrollbar {
  display: none;
  /*滚动条整体样式*/
  /*高宽分别对应横竖滚动条的尺寸*/
  /* width: 3px !important; 
  height: 3px !important;
  background: #ffffff !important;
  cursor: pointer !important; */
}

::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  /* border-radius: 5px !important;
  -webkit-box-shadow: inset 0 0 2px rgba(240, 240, 240, 0.5) !important;
  background: rgba(63, 98, 131, 0.8) !important;
  cursor: pointer !important; */
}

::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  /* -webkit-box-shadow: inset 0 0 2px rgba(240, 240, 240, 0.5) !important;
  border-radius: 0 !important;
  background: rgba(240, 240, 240, 0.5) !important;
  cursor: pointer !important; */
}

.leftMuen-btn {
  position: fixed;
  left: 5px;
  top: 0px;
  z-index: 2000;
}
.miniNav {
  border-top: none;
  padding-right: 10px;
}
.miniNav li {
  cursor: pointer;
  display: block;
  padding-left: 20px;
  margin: 10px 0px;
  border-left: 2px solid #409eff;
}
.miniNav li:hover {
  color: #409eff;
}
.nav-right .el-input__inner:focus,
.miniNav .el-input__inner:focus {
  border-color: #42b983 !important;
}
.nav-right .el-input__inner {
  /* border-radius: 20px !important; */
  border: none !important;
  /* border-bottom: 1px solid ghostwhite !important; */
  background: rgb(24, 31, 38);
}
.addFloader .el-link {
  padding: 0 15px;
  font-size: 25px;
}
.content-row-nav {
  margin-bottom: 20px;
}

/* 文件夹弹框 头部关闭 */
.v-modal {
  background: #ffffff !important;
  opacity: 0.96 !important;
}
.el-dialog__header {
  display: none;
}
.el-dialog__body {
  padding: 30px 20px 50px 20px;
}
.el-dialog__body .el-table {
  margin-bottom: 30px;
}

.el-dialog__body .el-row {
  padding-bottom: 20px;
}

/* 自定义mavendown 样式 */
.v-note-show,
.mavon-editor {
  border: none !important;
}
/* 导航条的 不让它扶起来 */
.Home .v-note-wrapper .v-note-op.shadow,
.Home .v-note-op,
.Home .shadow {
  box-shadow: none !important;
}
.Home .v-note-op {
  display: none !important;
}
.Home .v-note-wrapper .Home .v-note-panel .v-note-show .Home .v-show-content,
.Home .v-show-content-html {
  background: white !important;
  border: none !important;
}
.Home .shadow,
.Home .v-note-op,
.Home .v-note-wrapper .v-note-op.shadow,
.Home .v-note-show,
.Home .v-note-panel,
.v-note-wrapper {
  border: none !important;
}
.Home .single-show,
mavon-editor {
  border: none !important;
}
.Home .v-note-op {
  border: none !important;
}
.Home .v-show-content {
  background: white !important;
}

/* 左侧菜单样式 */
.left-menu-ul {
  font-size: 18px;
}
.left-menu-ul .menu-one-li {
  border-left: 3px solid #409eff;
  padding-left: 20px;
}

.left-menu-ul .menu-one-li .tiltle {
  font-size: 1.1em;
  font-weight: 700;
}

.left-menu-ul .left-menu-ul-li-ol {
  cursor: pointer;
  padding: 0.35rem 1rem 0.35rem 1.25rem;
  color: #2c3e50;
  font-size: 1em;
  font-weight: 400;
}
.left-menu-ul .left-menu-ul-li-ol:hover {
  color: #409eff;
}
.left-menu-ul .left-menu-ul-li-ol:active {
  color: #409eff;
}
.left-menu-ul ol {
  padding-inline-start: 20px !important;
}
.olCheck {
  color: #409eff;
}
.v-note-navigation-wrapper .v-note-navigation-title,
.v-note-navigation-wrapper .v-note-navigation-content {
  border: 1px solid #ebebeb !important;
}

/* 隐藏 markdown的导航 */
.Home .v-note-navigation-wrapper {
  display: none !important;
}

.Home .rowTwoCol-right-nav {
  padding: 5px 0x;
}

.Home .rowTwoCol-right-nav-title span {
  border-top: 2px solid #ebebeb;
  padding-top: 5px;
  margin-bottom: 10px;
  font-size: 18px;
  color: #409eff;
  font-weight: bolder;
}

.Home .rowTwoCol-right-nav ul:nth-child(1) {
  margin: 0px !important;
  padding: 0 0 0 0px !important;
}
.rowTwoCol-right-top {
  text-align: center;
  padding: 20px 20px 50px 0px;
}
.rowTwoCol-right-top img {
  width: 100%;
}

.Home .rowTwoCol-right-nav ul li {
  color: #444 !important;
  font-size: 16px !important;
  padding: 5px 0;
}
.Home .rowTwoCol-right-nav ul li:hover {
  color: #409eff !important;
}
.Home .rowTwoCol-right-nav ul ul {
  padding: 0 0 0 20px !important;
}
.Home .v-show-content h3:nth-last-of-type(1) {
  display: none !important;
}
.Home .markdown-body blockquote {
  border-left: 0.25em solid#409eff;
}
.rowTwoCol-right-nav-list {
  word-wrap: break-word;
  word-break: break-all;
  overflow: hidden;
}
</style>
