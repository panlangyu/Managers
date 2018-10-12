/**
 * 系统管理--产品记录
 */
var RecordInfo = {
    id: "recordTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};


/**
 * 初始化表格的列
 */
RecordInfo.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'userId', field: 'userId', visible: false, align: 'center', valign: 'middle'},
        {title: '账号', field: 'account', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '手机', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '开始日期', field: 'createtime', align: 'center', valign: 'middle', sortable: true},
        {title: '结束日期', field: 'updatetime', align: 'center', valign: 'middle', sortable: true},
        {title: '客户类型', field: 'customer_type', align: 'center', valign: 'middle', sortable: true},
        {title: '产品名称', field: 'deptName', align: 'center', valign: 'middle', sortable: true},
        {title: '产品代码', field: 'email', align: 'center', valign: 'middle', sortable: true},
        {title: '产品类别', field: 'sexName', align: 'center', valign: 'middle', sortable: true},
        {title: '截止日期数量', field: 'nums', align: 'center', valign: 'middle', sortable: true},
        {title: '管理费', field: 'management', align: 'center', valign: 'middle', sortable: true},
        {title: '管理分成', field: 'employee_num', align: 'center', valign: 'middle', sortable: true},
        {title: '客户净数量', field: 'mechanism', align: 'center', valign: 'middle', sortable: true},
        {title: '销售机构', field: 'salesagency', align: 'center', valign: 'middle', sortable: true}];

    return columns;
};



/**
 * 检查是否选中
 */
RecordInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        RecordInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加记录
 */
RecordInfo.openAddMgr = function () {

    var index = layer.open({
        type: 2,
        title: '添加产品记录',
        area: ['900px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/record/record_add'
    });
    this.layerIndex = index;
};


/**
 * 点击修改按钮时+ "&userId=" + RecordInfo.seItem.userId
 * @param userId 管理员id
 */
RecordInfo.openChangeRecord = function () {

    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');

        RecordInfo.seItem = selected[0];

        //alert(RecordInfo.seItem.id +"   "+RecordInfo.seItem.userId);

        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['900px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/user_edit?userId=' + RecordInfo.seItem.userId+"&recordId=" + RecordInfo.seItem.id
        });
        this.layerIndex = index;

    }
};


/**
 * 删除产品记录
 */
RecordInfo.delRecordInfo = function () {

    if (this.check()) {

        var selected = $('#' + this.id).bootstrapTable('getSelections');

        RecordInfo.seItem = selected[0];

        var operation = function(){
            var id = RecordInfo.seItem.id;
            var userId = RecordInfo.seItem.userId;
            var ajax = new $ax(Feng.ctxPath + "/record/delete", function () {
                Feng.success("删除成功!");
                RecordInfo.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", id);
            ajax.set("userId", userId);
            ajax.start();
        };

        Feng.confirm("是否删除用户 " + this.seItem.account + " ?",operation);
    }
};





RecordInfo.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    RecordInfo.search();
}

RecordInfo.search = function () {
    var queryData = {};


    queryData['deptid'] = RecordInfo.deptid;
    queryData['account'] = $("#account").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['sex'] = $("#sex").val();

    RecordInfo.table.refresh({query: queryData});
}

RecordInfo.onClickDept = function (e, treeId, treeNode) {
    RecordInfo.deptid = treeNode.id;
    RecordInfo.search();
};

function checkss(){

    var yincang = $("#yinname").val();

    if(yincang != "admin"){

        $("#yincan").css("display","none");
    }

}


$(function () {
    var recordColunms = RecordInfo.initColumn();
    var url = "/record/list";
    var table = new BSTable("recordTable",url, recordColunms);
    table.setPaginationType("client");
    RecordInfo.table = table.init();
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(RecordInfo.onClickDept);
    ztree.init();

    checkss();

});
