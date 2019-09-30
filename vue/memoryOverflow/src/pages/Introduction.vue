<template>
  <div class="Introduction">
    <h1>简介</h1>
    <div class>
      <img src="/static/javalogo.jpg" />
      <p>
        <code class="code">Java Code Basics</code>：
        Java 核心基础知识点 整理。
      </p>
    </div>
    <hr />
    <h1>联系方式</h1>
    <div class="myself">
      <ul>
        <li>
          <b>
            <a :href="blogUrl">个人博客</a>
          </b>
        </li>
        <li>
          <b>微信：{{user.wechat}}</b>
        </li>
        <li>
          <b><a :href="gitHub">GitHub</a></b>
        </li>
        <li>
          <b>{{user.email}}</b>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        email: "memoryoverflows@163.com",
        wechat: "l18776416225",
        blogUrl:'https://www.memoryoverflow.cn/blog',
        gitHub:'https://github.com/lyj08',
      }
    };
  },
  methods: {
    init() {
      // 834px
    }
  }
};
</script>

<style>
.Introduction {
  padding: 30px 100px;
}
.Introduction img {
  height: 80px;
  vertical-align: middle;
  display: inline;
}
.Introduction p {
  display: inline;
  font-family: "Courier New", Courier, monospace;
}
.Introduction div {
  text-align: center;
}
.Introduction ul li {
  line-height: 3em;
  font-size: 20px;
  color: var(--theme-color, #42b983);
  font-weight: bolder;
  cursor: pointer;
}
.Introduction ul li b {
  border-bottom: 1px solid var(--theme-color, #42b983);
}
.code {
  background: #ebebeb;
  padding: 5px;
  border-radius: 5px;
  color: orange;
  font-size: 20px;
}
</style>
