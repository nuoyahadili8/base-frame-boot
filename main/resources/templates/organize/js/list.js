/**
 * Created by chenyi on 2017-10-20 13:54:57
 *  email   :  qq228112142@qq.com//
 */
/**数据渲染对象*/
var Render = {
    /**
     * 渲染状态列
     * @param rowdata 行数据
     * @param index
     * @param value
     */
    customState: function (rowdata,renderData, index, value) {
        if(value == "启用"){
            return '<span style="color:green">'+value+'</span>';

        }
        if(value == "禁用"){
            return '<span style="color:red">'+value+'</span>';
        }
        return value;
    },
    /**
     * @param rowdata   行数据
     * @state 状态值     1|0
     * @description     详情按钮渲染
     */
    info:function(rowdata,renderData){
        var btn=' <button  onclick="detailOne(\''+"/organize/info"+'\',\''+rowdata.orgId+'\')" class="layui-btn layui-btn-mini">详情</button>';
        return btn;
    },
    /**
     * @param rowdata   行数据
     * @state 状态值     1|0
     * @description     修改按钮渲染
     */
    edit:function(rowdata,renderData){
        var btn=' <button  onclick="editOne(\''+"/organize/edit"+'\',\''+rowdata.orgId+'\')" class="layui-btn layui-btn-mini">修改</button>';
        return btn;
    },
    /**
     * @param rowdata   行数据
     * @state 状态值     1|0
     * @description     删除按钮渲染
     */
    delete:function(rowdata,renderData){
        var btn=' <button  onclick="deleteOne(\''+"删除"+'\',\''+"/organize/delete"+'\',\''+rowdata.orgId+'\')" class="layui-btn layui-btn-mini layui-btn-delete">删除</button>';
        return btn;
    },
    /**
     * @param rowdata   行数据
     * @state 状态值     1|0
     * @description     启用禁用按钮渲染
     */
    state:function(rowdata,renderData){
        if(renderData.state=='0'){
            return' <button onclick="updateStateOne(\''+"启用"+'\',\''+"/organize/enable"+'\',\''+rowdata.orgId+'\')"' +
                '  class="layui-btn layui-btn-mini layui-btn-green">启用</button>';
        }
        if(renderData.state=='1'){
            return' <button  onclick="updateStateOne(\''+"禁用"+'\',\''+"/organize/limit"+'\',\''+rowdata.orgId+'\')" ' +
                'class="layui-btn layui-btn-mini layui-btn-danger">禁用</button>';
        }
        return "";
    }
};
