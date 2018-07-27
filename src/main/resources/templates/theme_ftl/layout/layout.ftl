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
    <link href="${ctx}/bootstrapV3_3_7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        @font-face {
            font-family: 'Glyphicons Halflings';
            src: url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.eot');
            src: url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'),
            url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.woff2') format('woff2'),
            url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.woff') format('woff'),
            url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.ttf') format('truetype'),
            url('${ctx}/bootstrapV3_3_7/fonts/glyphicons-halflings-regular.svg#glyphicons_halflingsregular') format('svg');
        }
    </style>
    <#--当前页面css部分-->
    <#list page_placeholder_headers as placeholder_header>
        ${placeholder_header}
    </#list>
    <#--公共JS部分 -->
    <#-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <#-- jQuery 1.x 兼容IE8 -->
    <script src="${ctx}/jqueryV1_12_4/jquery.min.js"></script>
    <#-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${ctx}/bootstrapV3_3_7/js/bootstrap.min.js"></script>
    
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