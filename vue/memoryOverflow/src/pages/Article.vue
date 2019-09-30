<template>
  <div class="Article">
    <mavon-editor
      :ishljs="true"
      v-model="value"
      :subfield="false"
      defaultOpen="preview"
      editable="false"
      :toolbars="toolbarView"
      style="border:none;padding:0"
    ></mavon-editor>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "",
      toolbarView: {
        navigation: true // 导航目录
      }
    };
  }
};
</script>

<style>
.v-note-show,
.mavon-editor {
  border: none !important;
}
/* 导航条的 不让它扶起来 */
.v-note-wrapper .v-note-op.shadow,
.v-note-op,
.shadow {
  box-shadow: none !important;
}
.v-note-wrapper .v-note-panel .v-note-show .v-show-content,
.v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
  background: white !important;
}
.single-show,
mavon-editor {
  border: none !important;
}
</style>

