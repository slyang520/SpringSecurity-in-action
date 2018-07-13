<#include "layout/layout.ftl">

<@html
       page_js="${ctx}/js/app/index.js"
       page_css="${ctx}/css/app/index.css"
       page_title="我的Index页面"
       page_metas=[
       '<meta http-equiv="expires" content="31 Dec 2008">',
       '<meta name="keywords" content="Spring boot,Spring cloud">'
       ]
>
<div class="test_css" style="padding: 50px 0">
    <p>hello</p>
</div>

</@html>