/**
 * 依赖
 *  jqueryAjax
 *  $appcontext
 */

// var res = {
//     code:200,
//     message:'',
//     data:{
//       code: 0,
//       message:'',
//       data:null
//     }
// }

(function () {

    var app_context = window.$appcontext;
    var response_util = {
        isGlobelSuccess: function (res) {
            return res.code = 200;
        },
        isBusinessSuccess: function (res) {
            return res.code = 200 && (!res.data || (res.data && !res.data.code));
        }
    };

    function resBuinessError(res){

    }

    function resGlobelError(res){

    }

    $.ajaxSetup({
        beforeSend: function (request) {
            var token = window.localStorage.getItem('token');
            request.setRequestHeader("SSO_API_TOKEN", 'test');
        }
    });

    function httpRequest(request_type, url, data, successHandler, failHandler) {
        var _args = arguments.length;
        $.ajax({
            type: request_type,
            url: app_context.restBaseUrl + url,
            data: data,
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            success: function (response) {

                if (response_util.isGlobelSuccess(response)) {
                    if (response_util.isBusinessSuccess(response)) {
                        // code 200  code =0
                        if (_args >= 4)
                            successHandler(response.data)
                    } else {
                        // code 200  code !=0
                        if (_args >= 4) {
                            var result = successHandler(response.data);
                            if (result !== true) {
                                // 走业务错误的公共处理
                                resBuinessError();
                            }
                        } else {
                            // 走业务错误的公共处理
                            resBuinessError();
                        }
                    }
                } else {
                    // code != 200
                    if (_args >= 5) {
                        var result = failHandler(response);
                        if (result !== true) {
                            // 走全局错误的公共处理
                            resGlobelError();
                        }
                    } else {
                        // 走全局错误的公共处理
                        resGlobelError();
                    }
                }
            },
            error: function (e) {
                // case 网络错误....  server 500 404 ...
                console.log(e);
            }
        });
    }

    var httpclient = {
        post: function (url, data, successHandler, failHandler) {
            httpRequest("POST", url, data, successHandler, failHandler);
        },
        get: function (url, data, successHandler, failCallBack) {
            httpRequest("GET", url, data, successHandler, failHandler);
        }
    };

    // export httpclient
    window.$httpclient = httpclient;

})();