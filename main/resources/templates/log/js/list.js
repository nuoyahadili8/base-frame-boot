/**
 * Created by 陈熠 on 2017/6/23.
 *  email   :  228112142@qq.com
 */
//设置日期
layui.use('laydate', function(){
    var laydate = layui.laydate;

    var start = {
       max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
           // end.min = datas; //开始日选好后，重置结束日的最小日期
           // end.start = datas //将结束日的初始值设定为开始日
        }
    };

    var end = {
        max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
           // start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    document.getElementById('LAY_demorange_s').onclick = function(){
        start.elem = this;
        laydate(start);
    };
    document.getElementById('LAY_demorange_e').onclick = function(){
        end.elem = this
        laydate(end);
    };

});

/**添加*/
function add(){
    //var ids= getSelectedRows(table_id);
    $("body").load("/sys/log/add");
}
/**删除*/
function deleteBatch(table_id){
    //获取选中的id
   var ids= getSelectedRows(table_id);
    if(ids!=null){
        confirm("确认删除？",function(){
            $.ajax({
                //url: "/sys/menu/list",
                type: "post",
                url: "/sys/log/deleteBatch",
                contentType: "application/json",
                data: JSON.stringify(ids),
                async: false,
                dataType:"json",
                success: function (R) {
                    if(R.code === 0){
                        alert('操作成功', function () {
                            $(".search-btn").click();
                        });
                    }else{
                        alert(R.msg);
                    }
                },
                error:function(){
                    alert("系统错误");
                }
            });
        });

    }

}
/**修改*/
function edit(table_id){
    var id=getSelectedRow(table_id);
    if(id!=null){
         $("body").load("/sys/log/edit/"+id);
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
    customState: function (rowdata, renderData,index, value) {
        var result = "";
        // if(rowdata.status==0){
        //     result="<span style='color:red'>禁用</span>"
        // }
        // if(rowdata.status==1){
        //     result="<span style='color:green'>正常</span>"
        // }
        if(value==""||value==null){
            return "";
        }
        result = "<span style='color:green'>" + value + "</span>"
        return result;
    },
    //渲染操作方法列
    customMethod: function (rowdata,renderData, index, value) {
        if(value==""||value==null){
            return "";
        }
        var result = value.substr(24, value.length - 23);
        return result;
    }

};
$(document).ready(function () {
    //表格渲染查询
    $("#syslogTable").pageGrid();
    // $("#userTable").pageGrid();

});