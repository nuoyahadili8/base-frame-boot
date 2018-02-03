/**
 * Created by chenyi on 2017/12/20.
 */

$(document).ready(function () {
    //默认显示菜单
    createMenu("sys/menu/user?_" + $.now());

});
//生成菜单
function createMenu(url) {
    $(".sidebar-menu").html("");
    $.getJSON(url, function (r) {
        for (var i = 0; i < r.menuList.length; i++) {
            var _li;
            if (r.menuList[i].type === 0) {
                _li = ['<li ><a href="javascript:;" title="'+r.menuList[i].name+'">',
                    '<i class="' + r.menuList[i].icon + '"></i>',
                    '<span>' + r.menuList[i].name + '</span>',
                    '<i class="fa fa-angle-left pull-right"></i>' +
                    '</a></li>'].join("");
                //是否有下级菜单
                if (r.menuList[i].list) {
                    var $li=$(_li);
                    $li.find("a").after('<ul class="treeview-menu"></ul>');
                    var childNodes = addMenu(r.menuList[i].list);
                    if (childNodes != "") {
                        $li.find(".treeview-menu").append(childNodes);
                        _li=$li.prop("outerHTML");
                    }
                }
            }
            if (r.menuList[i].type === 1) {
                _li = '$(<li><a class="cy-page" href="javascript:;" data-url="' + r.menuList[i].url + '" title="'+r.menuList[i].name+'"><i class="' + r.menuList[i].icon + '"></i> ' + r.menuList[i].name + '</a></li>)';
            }
            $(".sidebar-menu").append(_li);

        }

    });
}
//递归显示菜单 支持多级
function addMenu(list) {
    if (list) {
        var lis="";
        for (var i = 0; i < list.length; i++) {
            var _li;
            if (list[i].type === 0) {
                _li = ['<li ><a href="javascript:;" title="'+list[i].name+'">',
                    '<i class="' + list[i].icon + '"></i>',
                    '<span>' + list[i].name + '</span>',
                    '<i class="fa fa-angle-left pull-right"></i>' +
                    '</a></li>'].join("");
                //是否有下级菜单
                if (list[i].list) {
                    var $li=$(_li);
                    $li.find("a").after('<ul class="treeview-menu"></ul>');
                    var childNodes = addMenu(list[i].list);
                    if (childNodes != "") {
                        $li.find(".treeview-menu").append(childNodes);
                    }
                }
                _li= $li.prop("outerHTML");
            }
            if (list[i].type === 1) {
                _li = $('<li><a class="cy-page" href="javascript:;" data-url="' + list[i].url + '" title="'+list[i].name+'"><i class="' + list[i].icon + '"></i> ' + list[i].name + '</a></li>');
            }
            lis+=$(_li).prop("outerHTML");
        }
        return lis;
    }
}


$(window).on('resize', function () {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    $content.find('iframe').each(function () {
        $(this).height($content.height());
    });
}).resize();

function clearStorage() {
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    localStorage.clear();
    layer.close(index);
    layer.msg('清除成功 !', {icon: 1});
}

function updatePassword (){
    // Alert.alert("测试账号不提供修改密码,请下载代码体验完整版");
    //修改密码
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "修改密码",
        area: ['550px', '270px'],
        shadeClose: false,
        content: jQuery("#passwordLayer"),
        btn: ['修改','取消'],
        btn1: function (index) {
            var data = "password="+$("#password").val()+"&newPassword="+$("#newPassword").val();
            $.ajax({
                type: "POST",
                url: "sys/user/password",
                data: data,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        layer.close(index);
                        layer.alert('修改成功', function(index){
                            location.reload();
                        });
                    }else{
                        Msg.error(result.msg);
                    }
                }
            });
        }
    });
}

//打赏作者
function reward() {
    layer.open({
        title:'',
        type: 1,
        area: ['600px', '448px'], //宽高
        content: '<img src="/statics/img/cy/reward.png">'
    });
}
//公告
function notice(){
    layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '600px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,resize: false
        ,btn: ['打赏作者', '残忍拒绝']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: ['<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
            ,'1.为防止网友误删数据,已启用测试账号<br>&nbsp;&nbsp;&nbsp;如需管理员账号请加群<span style="color:#2991D9;" >275846351 &nbsp;&nbsp;&nbsp;<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5b2ec31ee55abc44722cf8b2c7f7807d5b44d9a08da06de2c589c305e4742364"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="cy-security" title="cy-security"></a></span><br><br>'
            ,'2.开源不易,如有帮助到你,请作者喝杯咖啡吧<br><br>'
            ,'3.打赏50元以上送<span style="color:#2991D9;" >定制视频</span>一份或<span style="color:#2991D9;" >html版前端框架 </span><br>'
            ,'&nbsp;&nbsp;&nbsp;(土豆作品地址<a href="http://id.tudou.com/i/UMTQ5MTY4MzM2MA" target="_blank" style="color:#2991D9;" >http://id.tudou.com/i/UMTQ5MTY4MzM2MA</a>)</div>'].join("")
        ,success: function(layero){
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'javascript:reward();'
            });
        }
    });
}
$(document).ready(function () {
    $.getJSON("sys/user/info?_"+$.now(), function(r){
        $(".username").html(r.user.username) ;
        notice();
    });


});