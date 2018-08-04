<#assign ctx=request.contextPath/>

<#macro footer>
<div class="bottom">
    <!--大屏幕显示footer-->
    <div class="hidden-sm hidden-xs">
        <div class="container">
            <div class="row footer-1">
                <div class="col-md-3">
                    <img src="${ctx}/app/img/footer/footer-word1.jpg"/>
                    <span>详尽的数据报表</span>
                </div>
                <div class="col-md-3">
                    <img src="${ctx}/app/img/footer/footer-word2.jpg"/>
                    <span>7×24小时售后支持</span>
                </div>
                <div class="col-md-3">
                    <img src="${ctx}/app/img/footer/footer-word3.jpg"/>
                    <span>定制化大数据解决方案</span>
                </div>
                <div class="col-md-3">
                    <img src="${ctx}/app/img/footer/footer-word4.jpg"/>
                    <span>毫秒级数据反馈</span>
                </div>
            </div>
        </div> <!--footer-1-->
        <hr />
        <div class="container">
            <div class="row footer-2">
                <div class="col-sm-2">
                    <h4>产品</h4>
                    <ul>
                        <li><a href="#1">文本语义理解</a></li>
                        <li><a href="#1">智能推荐引擎</a></li>
                        <li><a href="#1">垂直搜索引擎</a></li>
                        <li><a href="#1">数据采集挖掘</a></li>
                        <li><a href="#1">用户画像</a></li>
                    </ul>
                </div>
                <div class="col-md-2">
                    <h4>解决方案</h4>
                    <ul>
                        <li><a href="#1">电商大数据服务</a></li>
                        <li><a href="#1">媒体大数据服务</a></li>
                        <li><a href="#1">微信大数据服务</a></li>
                    </ul>
                </div>
                <div class="col-md-2">
                    <h4>常见问题</h4>
                    <ul>
                        <li><a href="#1">数据上报常见问题</a></li>
                        <li><a href="#1">大数据平台功能介绍</a></li>
                        <li><a href="#1">文本挖掘相关问题</a></li>
                        <li><a href="#2">数据抓取相关问题</a></li>
                    </ul>
                </div>
                <div class="col-md-2">
                    <h4>便捷入口</h4>
                    <ul>
                        <li><a href="#1">公众号大师平台</a></li>
                        <li><a href="#1">大数据平台</a></li>
                        <li><a href="#1">公众号排行入口</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h4>联系我们</h4>
                    <ul>
                        <li>电话：400-175-9889</li>
                        <li>官方微信号：DataGrand_</li>
                        <li>邮箱：contact@datagrand.com</li>
                        <li>地址：上海市浦东新区亮秀路112号Y1座</li>
                        <li><img src="${ctx}/app/img/footer/qrcode.png" alt="二维码" /></li>
                    </ul>
                </div>
            </div>
        </div> <!--footer-2-->
        <hr />
    </div>
    <!--小屏幕显示footer-->
    <div class="visible-sm-block visible-xs-block">
        <ul>
            <li>电话：400-175-9889</li>
            <li>官方微信号：DataGrand_</li>
            <li>邮箱：contact@datagrand.com</li>
            <li>地址：上海市浦东新区亮秀路112号Y1座</li>
            <li><img src="${ctx}/app/img/footer/qrcode.png" alt="二维码" /></li>
        </ul>
        <hr />
        <a href="#2">达观数据大师app下载</a>
        <hr />
    </div> <!--hidden-->
    <div class="footer-3 text-center">
        Copyright DataGrand Tech Inc. All Rights Reserved. 沪ICP备15028292-1号
    </div>
</div>
</#macro>