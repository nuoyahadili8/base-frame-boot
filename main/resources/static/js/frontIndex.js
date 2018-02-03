//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props:{item:{}},
    template:[
        '<li>',
        '<a v-if="item.type === 0" href="javascript:;">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span>{{item.name}}</span>',
        '<i class="fa fa-angle-left pull-right"></i>',
        '</a>',
        '<ul v-if="item.type === 0" class="treeview-menu">',
        '<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '</ul>',
        '<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle-o"></i> {{item.name}}</a>',
        '</li>'
    ].join('')
});

//iframe自适应
$(window).on('resize', function() {
    var $content = $('.content');
    $content.height($(this).height() - 120);
    $content.find('iframe').each(function() {
        $(this).height($content.height());
    });
}).resize();

//注册菜单组件
Vue.component('menuItem',menuItem);

var vm = new Vue({
    el:'#rrapp',
    data:{
        user:{},
        menuList:{},
        main:"sys/main.html",
        password:'',
        newPassword:'',
        navTitle:"控制台"
    },
    methods: {
        getMenuList: function (event) {
            $.getJSON("/frontframe/json/frontMenu.json", function(r){
                vm.menuList = r.menuList;
            });
        },
        getUser: function(){
            $.getJSON("sys/user/info?_"+$.now(), function(r){
                vm.user = r.user;
            });
        },
        updatePassword: function(){
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "修改密码",
                area: ['550px', '270px'],
                shadeClose: false,
                content: jQuery("#passwordLayer"),
                btn: ['修改','取消'],
                btn1: function (index) {
                    var data = "password="+vm.password+"&newPassword="+vm.newPassword;
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
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        }, clearStorage: function () {
            var index = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            localStorage.clear();
            layer.close(index);
            layer.msg('清除成功 !', {icon: 1});
        }
    },
    created: function(){
        this.getMenuList();
        this.getUser();
    },
    updated: function(){
        //路由
        var router = new Router();
        routerList(router, vm.menuList);
        router.start();
    }
});



function routerList(router, menuList){
    for(var key in menuList){
        var menu = menuList[key];
        if(menu.type == 0){
            routerList(router, menu.list);
        }else if(menu.type == 1){
            router.add('#'+menu.url, function() {
                var url = window.location.hash;

                //替换iframe的url
                vm.main = url.replace('#', '');

                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $("a[href='"+url+"']").parents("li").addClass("active");

                vm.navTitle = $("a[href='"+url+"']").text();
            });
        }
    }
}

//时间
function data(){
    var d=new Date(),str='';
    str +=d.getFullYear()+'年'; //获取当前年份
    str +=d.getMonth()+1+'月'; //获取当前月份（0——11）
    str +=d.getDate()+'日';
    return str;
}
function week(){
    var arr = new Array("日", "一", "二", "三", "四", "五", "六");
    var week = new Date().getDay();
    return "星期" + arr[week];
}

function time(){
    var d=new Date();
    var hours=d.getHours();
    var minutes = d.getMinutes()>9?d.getMinutes().toString():'0' + d.getMinutes();
    var seconds = d.getSeconds()>9?d.getSeconds().toString():'0' + d.getSeconds();
    var str = hours + ':' + minutes + ':' + seconds;
    return str;
}

setInterval(function(){
    $("#nowTime").children(".data").html(data());
    $("#nowTime").children(".week").html(week());
    $("#nowTime").children(".time").html(time());
},1000);
