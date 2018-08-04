<#import "layout/layout.ftl" as layout_namespace>
<#assign ctx=request.contextPath/>

<@layout_namespace.html
page_title="我的Index页面"
page_metas=[
'<meta http-equiv="expires" content="31 Dec 2008">',
'<meta name="keywords" content="Spring boot,Spring cloud">'
]
page_placeholder_headers=[
'<link href="${ctx}/app/css/index.css" rel="stylesheet">'
]
page_placeholder_footers=[
'<script src="${ctx}/app/js/index.js"></script>'
]
>
<div class="content-swiper">
    <img src="${ctx}/app/img/index/banner2.jpg"/>
    <div class="swiper-guide">
        <div>fdsf</div>
        <h1>hello</h1>
        <h2>hello</h2>
        <h3>hello</h3>
        <h4>hello</h4>
        <h5>hello</h5>
        <h6>hello</h6>
    </div>
</div>

</@layout_namespace.html>