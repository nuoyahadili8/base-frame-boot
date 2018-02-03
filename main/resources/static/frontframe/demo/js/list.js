/**
 * Created by chenyi on 2017-10-20 13:54:57
 *  email   :  qq228112142@qq.com//
 */
/**数据渲染对象*/
var Render = {
    /**
     * 渲染状态列
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @param index
     * @param value      当前对象值
     */
    customState: function (rowdata,renderData,index, value) {
        $(this).find("[class='we']");
        if(value == "启用"){
            return '<span style="color:green">'+value+'</span>';
        }
        if(value == "禁用"){
            return '<span style="color:red">'+value+'</span>';
        }
        return value;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      详情按钮渲染
     */
    info:function(rowdata,renderData){
        var btn=' <button  onclick="Msg.success(\''+"操作成功"+'\')" class="layui-btn layui-btn-mini">详情</button>';
        return btn;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description      修改按钮渲染
     */
    edit:function(rowdata,renderData){
        var btn=' <button  onclick="Msg.success(\''+"操作成功"+'\')" class="layui-btn layui-btn-mini">修改</button>';
        return btn;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description     删除按钮渲染
     */
    delete:function(rowdata,renderData){
        var btn=' <button  onclick="Msg.success(\''+"操作成功"+'\')" class="layui-btn layui-btn-mini layui-btn-delete">删除</button>';
        return btn;
    },
    /**
     * @param rowdata    行数据
     * @param renderData 渲染后的列
     * @description     启用禁用按钮渲染
     */
    state:function(rowdata,renderData){

        if(rowdata.state=='0'){
            return' <button onclick="Msg.success(\''+"操作成功"+'\')"' +
                '  class="layui-btn layui-btn-mini layui-btn-green">启用</button>';
        }
        if(rowdata.state=='1'){
            return' <button  onclick="Msg.success(\''+"操作成功"+'\')" ' +
                'class="layui-btn layui-btn-mini layui-btn-danger">禁用</button>';
        }
        return "";
    }
};
