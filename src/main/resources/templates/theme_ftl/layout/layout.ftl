<#import "header.ftl" as header_namespace>
<#import "footer.ftl" as footer_namespace>
<#import "error.ftl" as error_namespace>

<#assign ctx=request.contextPath/>
<#--
  page_placeholder_footers 当前页面JS[]
  page_placeholder_headers 当前页面CSS[]
  page_title 当前页面需要显示的标题
  page_metas 当前页面的meta
-->
<#macro html page_placeholder_headers=[] page_placeholder_footers=[] page_title="" page_metas=[]>
<!doctype html>
<html lang="zh-CN">
<head>
    <title>${page_title}</title>
    <link rel="icon" type="image/x-icon" >
    <link rel="icon" href="${ctx}/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <#list page_metas as meta>
        ${meta}
    </#list>
    <#--公共样式部分-->
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <#--当前页面css部分-->
    <#list page_placeholder_headers as placeholder_header>
        ${placeholder_header}
    </#list>
    <#--公共JS部分 -->
    <script src="${ctx}/js/jquery.js"></script>
</head>
<body>
<div class="wrapper">
    <@header_namespace.header/>
    <#-- 自定义内容部分,会被插入 -->
    <#attempt>
        <#nested />
        <#recover>
        <#-- 错误处理 -->
        <@error_namespace.error/>
    </#attempt>
</div>
    <@footer_namespace.footer/>
    <#--当前页面JS部分-->
    <#list page_placeholder_footers as placeholder_footer>
        ${placeholder_footer}
    </#list>
</body>
</html>
</#macro>