<template>
  <div class="Index">
    <a target="_blank" :href="URL.github">
      <div class="right-top">
        <img src="/static/github2.png" />
      </div>
    </a>
    <div class="main-top">
      <div class="logo">
        <img src="/static/logo.png" />
      </div>
      <div class="title">
        <h2 style="padding-left:0">{{msg}}</h2>
      </div>
      <div style="text-align:center">
        <p>
          <code>Java Core Basics</code>
        </p>
        <p>Java 核心基础强化</p>
      </div>
      <div class="mian-top-btn">
        <button class="github-btn">
          <a target="_blank" :href="URL.github">
            <img src="/static/github.png" />
            <span>GitHub</span>
          </a>
        </button>
        <button class="github-started" @click="jumpHome(URL.home)">
          Get Started
          <i class="el-icon-right"></i>
        </button>
      </div>
    </div>
    <div class="main-bottom">
      <div class>
        <a target="_blank" :href="URL.blog">
          <img style="width:150px;height:150px" src="/static/kafei_white.png" />
        </a>
      </div>
      <div>
        <span style="color:white">博客~ 🍲</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      msg: "MemoryOverFlow",
      URL: {
        github: "https://github.com/memoryoverflow",
        blog: "https://www.memoryoverflow.cn/blog/",
        home: "/home"
      }
    };
  },
  mounted() {
    this.baiduPublish();
  },
  created() {
    this.checkLogin();
  },
  methods: {
    checkLogin() {
      
    },
    jumpHome(url) {
      this.$router.push({ path: url });
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
  }
};
</script>

<style scoped>
.right-top {
  cursor: pointer;
  position: fixed;
  top: -150px;
  right: -22px;
  border-top: 150px solid transparent;
  border-bottom: 150px solid transparent;
  border-left: 150px solid lightcyan;
  -moz-transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
}
.right-top img {
  position: absolute;
  top: 10px;
  right: 90px;
  -moz-transform: rotate(90deg);
  -webkit-transform: rotate(90deg);
}
.Index {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  position: fixed;
  height: 100%;
  background: rgb(80, 185, 156);
}
.main-top {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
}
.main-top code {
  font-size: 50px;
}
.mian-top-btn button {
  margin: 20px;
  border: none;
  cursor: pointer;
}
.mian-top-btn a {
  color: black;
}
.mian-top-btn button:visited {
  border: none;
}
.mian-top-btn button:active {
  border: none;
}
.main-bottom {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.main-bottom div:nth-child(1){
  cursor: pointer;
}
.github-btn img {
  width: 30px;
  vertical-align: middle;
}
.github-btn span {
  line-height: 2em;
}
.github-started {
  line-height: 33px;
}
h1 {
  margin: 0;
  padding: 0;
  font-size: 70px;
}
code {
  color: yellow;
}
</style>
