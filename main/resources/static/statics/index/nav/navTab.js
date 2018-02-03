/**
 * Created by chenyi on 2017/12/20.
*/
$(function () {
    //选中左侧菜单
    $(".sidebar .sidebar-menu").on("click", "a.cy-page", function () {
        var $this = $(this);
        var name = $this.text() || $this.attr("title");
        var url = unescape($this.attr("data-url")) || "";
        //判断该页面是否已存在
        if ($("#navTab").find("li[data-url='" + url + "']").length === 0) {
            var index = Loading.open(1,false);
            //如果不存在
            $("#navTab").find("li").removeClass("selected");
            //新增tab页
            var _li = ['<li tabid="tools-utils" class="selected" data-url="' + url + '">',
                '<a href="javascript:" title="' + name + '" class="tools-utils">',
                '<span>' + name + '</span>',
                '</a>',
                '<a href="javascript:;" class="close">close</a>',
                '</li>'].join("");
            $("#navTab").find("ul").append(_li);
            //新增右侧更多list
            $(".tabsMoreList").find("li").removeClass("selected");
            var moreli = '<li class="selected" data-url="'+url+'"><a href="javascript:"  title="' + name + '">' + name + '</a></li>';
            $(".tabsMoreList").append(moreli);

            $(".content").find("iframe").removeClass("cy-show");
            //打开iframe
            var iframe = $('<iframe class="cy-show" scrolling="yes" frameborder="0" style="width: 100%; height: 100%; overflow: visible; "></iframe>');
            $(iframe).attr("src", url);
            $(".content").append(iframe);
            $(iframe).load(function() {
                Loading.close(index);
            });

            //tab过多时
            var _lis = $(".tabsPageHeaderContent").find("li");
            var n = 0;
            for (var i = 0; i < _lis.length; i++) {
                n += $(_lis[i]).width();
            }

            //获取右侧区域宽度
            var _width = $("#navTab").width();
            if (n > parseInt(_width)-150 ) {
                $(".tabsRight,.tabsLeft").show();
            }


        }else{
            $("#navTab").find("li").removeClass("selected");
            $("#navTab").find("li[data-url='" + url + "']").addClass("selected");
            $(".content").find("iframe").removeClass("cy-show");
            $(".content").find("iframe[src='"+url+"']").addClass("cy-show");
            //更多列表
            $(".tabsMoreList").find("li").removeClass("selected");
            $(".tabsMoreList").find("li[data-url='"+url+"']").addClass("selected");
        }


    });
    //选中tab
    $(".tabsPageHeaderContent").on("click", "li", function () {
        //当前是第几个li
        var li_index = $(this).prevAll().length + 1;
        $(this).parents(".content-wrapper").find(".tabsMoreList").find("li").removeClass("selected");
        $(this).parents(".content-wrapper").find(".tabsMoreList").find("li:nth-child(" + li_index + ")").addClass("selected");
        var url=$(this).attr("data-url");
        $(".content").find("iframe").removeClass("cy-show");
        $(".content").find("iframe[src='"+url+"']").addClass("cy-show");
    });
    //关闭tab
    $(".tabsPageHeaderContent").on("click", ".close ", function (event) {

        //关闭页面
        var url=$(this).parents("li").attr("data-url");
        $(".content").find("iframe[src='"+url+"']").remove();

        if ($(this).parents("li").hasClass("selected")) {
            $(this).parents("li").prev().click();
        }

        var _lis = $(this).parents(".tabsPageHeaderContent").find("li");
        var n = 0;
        for (var i = 0; i < _lis.length; i++) {
            n += $(_lis[i]).width();
        }

        //获取右侧区域宽度
        var _width = $("#navTab").width();
        if (n < parseInt(_width) + 150) {
            $(".tabsRight,.tabsLeft").hide();
            $(this).parents(".content-wrapper").find(".navTab-tab").css("left", "0");
        }


        //当前是第几个li
        var li_index = $(this).parents("li").prevAll().length + 1;
        $(this).parents(".content-wrapper").find(".tabsMoreList").find("li:nth-child(" + li_index + ")").remove();
        $(this).parents("li").remove();
        event.stopPropagation();

    });
    //右侧更多
    $(".tabsMore, .tabsMoreList").on("click", function (event) {
        $(this).parents(".content-wrapper").find(".tabsMoreList").toggle();
        event.stopPropagation();
    });
    $("body").click(function () {
        $(".tabsMoreList").hide();
    });

    //右侧更多点击
    $(".tabsMoreList").on("click","li",function () {
        $(".tabsMoreList li").removeClass("selected");
        $(this).addClass("selected");
        var url=$(this).attr("data-url");
        //选中tab
        $("#navTab").find("li").removeClass("selected");
        $("#navTab").find("li[data-url='" + url + "']").addClass("selected");
        //显示iframe
        $(".content").find("iframe").removeClass("cy-show");
        $(".content").find("iframe[src='"+url+"']").addClass("cy-show");
    });
    //tab左移
    $(".tabsLeft").on("click", function () {
        var _left = $(this).parents(".content-wrapper").find(".navTab-tab").css("left");
        _left = parseInt(_left.substr(0, _left.length - 2));
        var abs = Math.abs(_left);
        //获取右侧区域宽度
        var _width = $("#navTab").width();
        if (parseInt(abs + 150) > _width) {
            Msg.info("拽不动了");
        } else {
            $(this).parents(".content-wrapper").find(".navTab-tab").css("left", _left - 150);

        }


    });
    //tab右移
    $(".tabsRight").on("click", function () {
        var _left = $(this).parents(".content-wrapper").find(".navTab-tab").css("left");
        _left = parseInt(_left.substr(0, _left.length - 2));
        if (_left < 0) {
            $(this).parents(".content-wrapper").find(".navTab-tab").css("left", _left + 150);
        }
        else {
            Msg.info("拽不动了");
        }
    })


    $(".tabsPage").on("click", "li", function () {
        $(this).parents("ul").find("li").removeClass("selected");
        $(this).addClass("selected")
    });
    $(".tabsPage").on("mouseover", "a.close", function () {
        $(this).addClass("hover");
    });
    $(".tabsPageb").on("mouseout", "a.close", function () {
        $(this).removeClass("hover");
    });
});

$(document).ready(function(){
    context.init({preventDoubleContext: false});
    context.settings({compress: true});
    context.attach('.navTab-tab li', [//attach为绑定的dom对象，可以使用类名或id，例如'.classname'
        {text: '刷新当前页面', action: function(e){

            e.preventDefault();
            var dataUrl=$(this).parents("ul").attr("data-url");
            var $iframe=$("section iframe[src='"+dataUrl+"']");
            $($iframe[0]).attr("src",dataUrl);

        }},
        {text: '关闭当前页面', action: function(e){
            e.preventDefault();
            var dataUrl=$(this).parents("ul").attr("data-url");
            $(".navTab-tab li[data-url='"+dataUrl+"']").find(".close").click();
        }},
        {text: '关闭其他页面', action: function(e){
            e.preventDefault();
            var dataUrl=$(this).parents("ul").attr("data-url");
            $(".navTab-tab li[data-url='"+dataUrl+"']").prevAll().find(".close").click();
            $(".navTab-tab li[data-url='"+dataUrl+"']").nextAll().find(".close").click();

        }},
        {text: '关闭所有页面', action: function(e){
            var dataUrl=$(this).parents("ul").attr("data-url");
            $(".navTab-tab li[data-url='"+dataUrl+"']").parents("ul").find("li .close").click();
        }},
    ]);
});
