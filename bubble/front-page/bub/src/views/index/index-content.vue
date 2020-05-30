<template>
  <div>
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '680px' }"
    >
      <a-carousel autoplay>
        <div><h3>人还是要梦想的。</h3></div>
        <div><h3>万一要实现了呢！</h3></div>
        <div><h3>如果你什么都不做。</h3></div>
        <div><h3>我想一定没有机会，一条水平线的人生真没有高度</h3></div>
      </a-carousel>
    </a-layout-content>
  </div>
</template>
<script>
export default {}
</script>
<style scoped>
    /* For demo */
    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 460px;
        line-height: 460px;
        background: #4bcffa;
        overflow: hidden;
    }

    .ant-carousel >>> .custom-slick-arrow {
        width: 25px;
        height: 25px;
        font-size: 25px;
        color: #fff;
        background-color: #0984e3;
        opacity: 0.3;
    }
    .ant-carousel >>> .custom-slick-arrow:before {
        display: none;
    }
    .ant-carousel >>> .custom-slick-arrow:hover {
        opacity: 0.5;
    }

    .ant-carousel >>> .slick-slide h3 {
        color: #fff;
    }
</style>
