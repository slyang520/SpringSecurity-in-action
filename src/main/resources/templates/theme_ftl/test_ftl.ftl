<html>
<head>
    <title>Welcome!</title>
</head>
<body>

<p>We have these animals:</p>

<!--  name值为null，取name值会报错，可写成 name!"游客"   或 name! -->
<a href="${url}">${name!"游客"},${message}</a><br/>

<!-- name变量存在 且 判断是不为null  -->
<#if name??>
    <p>name is not null</p>
<#else>
    <p>name is null</p>
</#if>

<!-- 判断变量存在 -->
<#if name2?exists>
    <p>name2 exists</p>
<#else>
    <p>name2 not exists</p>
</#if>

<!-- 对象渲染 -->
<p>${my_obj.name}</p>
<p>${my_obj.address}</p>
<p>${my_obj.age}</p>
<p>${my_obj.height}</p>

<!-- 列表渲染 -->
<ul>
<#list my_list as obj>
    <li>
      <p>${obj.user_name}</p>
      <p>${obj.user_tel}</p>
    </li>
</#list>
</ul>

<!-- 错误处理 -->
<#attempt>
==输出：${my.name5}
<#recover>
错误recover block
</#attempt>


<!-- 当你使用了对空白、换行的格式(比如HTML或XML) 时压缩指令对于移除多余的空白 -->
<#compress>
<p>
1 2 3   4    5
test only
I said, test only
</p>
</#compress>

<!--不解析ftl-->
<#noparse>
   <div>
       ${message}
   </div>
</#noparse>

</body>
</html>
