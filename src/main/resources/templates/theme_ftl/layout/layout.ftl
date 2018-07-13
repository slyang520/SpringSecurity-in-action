<#include "footer.ftl">
<#include "header.ftl">
<#assign ctx=request.contextPath/>

<#macro html page_title="">
<!doctype html>
<html lang="zh-CN">
<head>
    <title>${page_title}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!--公共样式部分-->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <!--公共JS部分 -->
    <script src="${ctx}/js/jquery.js"></script>
</head>
<body>
<div class="wrapper">
    <@header/>
    <!-- 自定义内容部分,会被插入 -->
    <#nested />
</div>
    <@footer/>
</body>
</html>
</#macro>