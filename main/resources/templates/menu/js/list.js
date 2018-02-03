/**
 * Created by 陈熠 on 2017/6/23.
 *  email   :  228112142@qq.com
 */
/**跳转到添加页面*/
function add(url) {
    //$("body").load(url);
    //type：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    parent.layer.open({
        type: 2,
        title: '添加',
        shadeClose: false,
        shade: [0.3, '#000'],
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url
    });
}
/**跳转到修改页面*/
function edit(table_id, url) {
    var id = getSelectedRow(table_id, url);
    if (id != null) {
        parent.layer.open({
            type: 2,
            title: '修改',
            shadeClose: false,
            shade: [0.3, '#000'],
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: url + "/" + id
        });
    }
}
/**删除*/
function deleteBatch(table_id, url) {
    //获取选中的id
    var ids = getSelectedRows(table_id);
    if (ids != null) {
        confirm("确认删除？", function () {
            $.ajax({
                type: "post",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(ids),
                async: false,
                dataType: "json",
                success: function (R) {
                    if (R.code === 0) {
                        Msg.success('操作成功');
                        $(".search-btn").click();
                    } else {
                        Msg.error(R.msg);
                    }
                },
                error: function () {
                    Msg.error("系统繁忙");
                }
            });
        });

    }

}
//数据渲染对象
var Render = {
    /**
     *  Created by 陈熠 on 2017/6/22
     *  email  ：228112142@qq.com
     *  rowdata：当前行数据
     *  index  ：当前第几行
     *  value  ：当前渲染列的值
     */
    //渲染状态列
    customState: function (rowdata,renderData, index, value) {
        if (value === 0) {
            return '<span class="layui-btn layui-btn-mini layui-btn-warm">目录</span>';
        }
        if (value === 1) {
            return '<span class="layui-btn layui-btn-mini layui-btn-sys">菜单</span>';
        }
        if (value === 2) {
            return '<span class="layui-btn layui-btn-mini layui-btn-green">按钮</span>';
        }
        if (value == "" || value == null) {
            return "";
        }
        return value;
    },
    //渲染操作方法列
    customIcon: function (rowdata,renderData, index, value) {
        if (value == "" || value == null) {
            return "";
        }
        var result = '<i class="' + value + ' fa-lg"></i>';
        return result;
    }

};
