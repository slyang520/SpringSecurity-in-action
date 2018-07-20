<#import "layout/layout.ftl" as layout_namespace>
<#assign ctx=request.contextPath/>

<@layout_namespace.html
page_title="我的Index页面"
page_metas=[
'<meta http-equiv="expires" content="31 Dec 2008">',
'<meta name="keywords" content="Spring boot,Spring cloud">'
]
page_placeholder_headers=[
'<link href="${ctx}/css/app/index.css" rel="stylesheet">'
]
page_placeholder_footers=[
'<script src="${ctx}/js/app/index.js"></script>'
]
>
<div class="test_css" style="padding: 50px 0">
    <p>hello</p>
</div>

</@layout_namespace.html>