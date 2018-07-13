<#include "footer.ftl">
<#include "header.ftl">
<#assign ctx=request.contextPath/>
<#--
  page_css 当前页面JS
  page_css 当前页面CSS全路径
  page_title 当前页面需要显示的标题
  page_metas 当前页面的meta
-->
<#macro html page_js="" page_css="" page_title="" page_metas=[]>
<!doctype html>
<html lang="zh-CN">
<head>
    <title>${page_title}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <#list page_metas as meta>
        ${meta}
    </#list>
    <#--公共样式部分-->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <#--当前页面css部分-->
    <#if page_css!="">
        <link href="${page_css}" rel="stylesheet">
    </#if>
    <#--公共JS部分 -->
    <script src="${ctx}/js/jquery.js"></script>
</head>
<body>
<div class="wrapper">
    <@header/>
    <#-- 自定义内容部分,会被插入 -->
    <#nested />
</div>
    <@footer/>
    <#--当前页面JS部分-->
    <#if page_js!="">
        <script src="${page_js}"></script>
    </#if>
</body>
</html>
</#macro>