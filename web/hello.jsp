<%@ page import="com.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>产品中心</title>
    <link href="js/iCheck/skins/minimal/minimal.css" rel="stylesheet">
    <link href="js/iCheck/skins/square/square.css" rel="stylesheet">
    <link href="js/iCheck/skins/square/red.css" rel="stylesheet">
    <link href="js/iCheck/skins/square/blue.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/index-responsive.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="sticky-header">
<section>
    <div class="wrapper">
        <div class="row">
            <div class="col-sm-12">
                <section class="panel">
                    <header class="panel-heading">
                        Product Table
                    </header>
                    <div class="panel-body">
                        <%
                            if (request.getAttribute("a") == null) {
                                request.setAttribute("a", "");
                            }
                            if (request.getAttribute("b") == null) {
                                request.setAttribute("b", "");
                            }
                            if (request.getAttribute("c") == null) {
                                request.setAttribute("c", "");
                            }
                        %>
                        <form action="product_select" method="post">
                            产品编码 <input type="text" id="a" name="a" value="<%= request.getAttribute("a")%>">&#12288;&#12288;&#12288;&#12288;
                            产品名称 <input type="text" id="b" name="b" value="<%= request.getAttribute("b")%>">&#12288;&#12288;&#12288;&#12288;
                            自定义分类 <input type="text" id="c" name="c" value="<%= request.getAttribute("c")%>">&#12288;&#12288;&#12288;
                            <input type="submit" value="提交"/>
                        </form>
                        <br><br><br>
                        <div class="adv-table editable-table ">
                            <div class="clearfix" style="margin-bottom: 20px">
                                <div class="btn-group">
                                    <button id="editable-sample_new" class="btn btn-primary" data-toggle="modal"
                                            data-target="#myModal">
                                        新增 <i class="fa fa-plus"></i>
                                    </button>
                                    <button id="delete" class="btn btn-primary" data-toggle="modal"
                                            onclick="del()">
                                        删除
                                    </button>
                                    <button id="verify_ok" class="btn btn-primary" data-toggle="modal"
                                            onclick="pass()">
                                        审核通过
                                    </button>
                                    <button id="verify_no" class="btn btn-primary" data-toggle="modal"
                                            onclick="failed()">
                                        审核不通过
                                    </button>
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">
                                                        新增产品
                                                    </h4>
                                                </div>
                                                <div class="modal-body" style="margin-left: 135px;">
                                                    <form id="addform" action="product_add" method="post">
                                                        <label>*产品类型</label>
                                                        <br>
                                                        <input type="text" name="type" id="type0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>*产品编码</label>
                                                        <br>
                                                        <input type="text" name="code" id="code0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>*产品名称</label>
                                                        <br>
                                                        <input type="text" name="name" id="name0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>*产品单位</label>
                                                        <br>
                                                        <input type="text" name="unit" id="unit0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>*产品自定义分类</label>
                                                        <br>
                                                        <input type="text" name="classification" id="classification0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>*规格</label>
                                                        <br>
                                                        <input type="text" name="norm" id="norm0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>成本价</label>
                                                        <br>
                                                        <input type="text" name="cost_price" id="cost_price0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>采购单价</label>
                                                        <br>
                                                        <input type="text" name="purchase_price" id="purchase_price0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                        <label>销售单价</label>
                                                        <br>
                                                        <input type="text" name="unit_price" id="unit_price0"
                                                               style="margin-left: 70px">
                                                        <br><br>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary" onclick="add()">
                                                        确定
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            data-dismiss="modal" onclick="resetAddModal()">
                                                        取消
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%
                                int pageNow = 0;
                                if (request.getAttribute("pageNow") != null) {
                                    pageNow = (int) request.getAttribute("pageNow");
                                }
                                int pageCount = 0;
                                if (request.getAttribute("pageCount") != null) {
                                    pageCount = (int) request.getAttribute("pageCount");
                                }
                                List<Product> products = null;
                                if (request.getAttribute("products") != null) {
                                    products = (List<Product>) request.getAttribute("products");
                                }
                                if (products != null) {
                            %>

                            <table class="table table-striped table-hover table-bordered" id="editable-sample">
                                <tr>
                                    <th style="width: 7%;height: 20px;"></th>
                                    <th>id</th>
                                    <th>产品类型</th>
                                    <th>产品编码</th>
                                    <th>产品名称</th>
                                    <th>产品单位</th>
                                    <th>产品自定义分类</th>
                                    <th>规格</th>
                                    <th>成本价</th>
                                    <th>采购单价</th>
                                    <th>销售单价</th>
                                    <th>审核状态</th>
                                    <th width="150px">操作</th>
                                </tr>
                                <%
                                    for (Product p : products) {
                                %>
                                <tr>
                                    <td><input type="checkbox" value="<%=p.getId()%>" name="check"/></td>
                                    <td><%= p.getId() %>
                                    </td>
                                    <td><%= p.getType() %>
                                    </td>
                                    <td><%= p.getCode() %>
                                    </td>
                                    <td><%= p.getName() %>
                                    </td>
                                    <td><%= p.getUnit() %>
                                    </td>
                                    <td><%= p.getClassification() %>
                                    </td>
                                    <td><%= p.getNorm() %>
                                    </td>
                                    <td><%= p.getCost_price() %>
                                    </td>
                                    <td><%= p.getPurchase_price() %>
                                    </td>
                                    <td><%= p.getUnit_price() %>
                                    </td>
                                    </td>
                                    <td><%= p.getReview() %>
                                    </td>
                                    <td>
                                        <button class="bt" data-toggle="modal" data-target="#myModal2"
                                                onclick="getID(<%=p.getId()%>)">修 改
                                        </button>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>

                                <%
                                    }
                                %>
                            </table>

                            <%
                                if (request.getAttribute("pp") != "product_select") {
                                    request.setAttribute("pp", "hello");
                                }
                            %>
                            <div style="float: right">
                                <c:if test="${pageNow != 1}">
                                    <a href="<%=request.getAttribute("pp")%>?pageNow=${pageNow - 1}&a=<%=request.getAttribute("a")%>&b=<%=request.getAttribute("b")%>&c=<%=request.getAttribute("c")%>"
                                       style="color:blue">上一页</a>
                                </c:if>
                                <span>当前页: ${pageNow} / ${pageCount} 页</span>
                                <c:if test="${pageNow != pageCount}">
                                    <a href="<%=request.getAttribute("pp")%>?pageNow=${pageNow + 1}&a=<%=request.getAttribute("a")%>&b=<%=request.getAttribute("b")%>&c=<%=request.getAttribute("c")%>"
                                       style="color:blue">下一页</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel2">
                            修改产品
                        </h4>
                    </div>
                    <div class="modal-body" style="margin-left: 135px;width: 45%">
                        <form id="addform2" action="product_update" method="post">
                            <label>id(不可修改)</label>
                            <input type="text" name="id" id="id" readonly="readonly"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>产品类型(不可修改)</label>
                            <input type="text" name="type" id="type" readonly="readonly"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>产品编码(不可修改)</label>
                            <input type="text" name="code" id="code" readonly="readonly"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>*产品名称</label>
                            <input type="text" name="name" id="name"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>*产品单位</label>
                            <input type="text" name="unit" id="unit"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>*产品自定义分类</label>
                            <input type="text" name="classification" id="classification"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>*规格</label>
                            <input type="text" name="norm" id="norm"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>成本价</label>
                            <input type="text" name="cost_price" id="cost_price"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>采购单价</label>
                            <input type="text" name="purchase_price" id="purchase_price"
                                   style="margin-left: 70px">
                            <br><br>
                            <label>销售单价</label>
                            <input type="text" name="unit_price" id="unit_price"
                                   style="margin-left: 70px">
                            <br><br>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" onclick="change()">
                            确定
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            取消
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/jquery.nicescroll.js"></script>

<!--common scripts for all pages-->
<script src="js/scripts.js"></script>

<script>

    function del() {
        var nodes = $("#editable-sample tr td input:checked"); //获取所有复选框选中的项作为一个对象
        var ids = new Array();  //创建一个接收id的数组
        $.each(nodes, function (i) {    //遍历所选的对象
            ids.push($(nodes[i]).val());  //将每一个选中对象的值赋给接收id的数组
        })
        // ids = JSON.stringify(ids);
        // ids = ids.substring(2,ids.length-2);
        // ids = ids.replace("\"","")
        // var url = "product_delete?ids=" + ids;
        // window.location.href = url;
        var r = confirm("删除:" + ids);
        if (r == true) {
            $.ajax({
                type: "GET",
                url: "/product_delete",
                data: {"ids": JSON.stringify(ids)},
                success: function (ids_back) {
                    // location.reload();
                    if (ids_back != "") {
                        alert("删除失败:" + ids_back);
                    }
                    window.location.href = "hello";
                },
                error: function () {
                    alert("删除失败");
                }
            })
        }
    }

    function pass() {
        var nodes = $("#editable-sample tr td input:checked"); //获取所有复选框选中的项作为一个对象
        var ids = new Array();  //创建一个接收id的数组
        $.each(nodes, function (i) {    //遍历所选的对象
            ids.push($(nodes[i]).val());  //将每一个选中对象的值赋给接收id的数组
        })
        var r = confirm("转换为审核通过:" + ids);
        if (r == true) {
            $.ajax({
                    type: "POST",
                    url: "/review_pass",
                    data: {"ids": JSON.stringify(ids)},
                    success: function (code) {
                        if (code != "0") {
                            alert("此条审核操作失败，可能原因：重复通过审核！");
                        }
                        window.location.href = "hello";
                    }
                    ,
                    error: function () {
                        alert("操作失败");
                    }
                }
            )
        }
    }

    function failed() {
        var nodes = $("#editable-sample tr td input:checked"); //获取所有复选框选中的项作为一个对象
        var ids = new Array();  //创建一个接收id的数组
        $.each(nodes, function (i) {    //遍历所选的对象
            ids.push($(nodes[i]).val());  //将每一个选中对象的值赋给接收id的数组
        })
        var r = confirm("转换为审核不通过:" + ids);
        if (r == true) {
            $.ajax({
                type: "POST",
                url: "/review_failed",
                data: {"ids": JSON.stringify(ids)},
                success: function (code) {
                    if (code != "0") {
                        alert("此条审核操作失败，可能原因：重复不通过审核！");
                    }
                    window.location.href = "hello";
                },
                error: function () {
                    alert("操作失败");
                }
            })
        }
    }

    function getID(id) {
        $.ajax({
            type: "get",
            url: "/product_findById",
            data: {
                id: id
            },
            dataType: "text",
            success: function (product) {
                var ss = product.substring(8, product.length - 1);
                var editdata = ss.split(",");
                $('#id').val(editdata[0].substring(3, editdata[0].length));
                $('#type').val(editdata[1].substring(7, editdata[1].length - 1));
                $('#code').val(editdata[2].substring(7, editdata[2].length - 1));
                $('#name').val(editdata[3].substring(7, editdata[3].length - 1));
                $('#unit').val(editdata[4].substring(7, editdata[4].length - 1));
                $('#classification').val(editdata[5].substring(17, editdata[5].length - 1));
                $('#norm').val(editdata[6].substring(7, editdata[6].length - 1));
                $('#cost_price').val(editdata[7].substring(13, editdata[7].length - 1));
                $('#purchase_price').val(editdata[8].substring(17, editdata[8].length - 1));
                $('#unit_price').val(editdata[9].substring(13, editdata[9].length - 1));
            },
            error: function () {
                alert("获取产品信息失败");
            }
        });
    }

    function add() {
        var form = document.getElementById('addform');
        form.submit();
        $("#myModal").on("hidden.bs.modal", function () { //当弹出的模态框消失
            $(this).removeData("bs.modal");
        });
    }

    function change() {
        var form = document.getElementById('addform2');
        form.submit();
        $("#myModal2").on("hidden.bs.modal", function () {
            $(this).removeData("bs.modal");
        });
    }

    //点击取消后清空表单中已写信息
    function resetAddModal() {
        document.getElementById("addform").reset();
    }

</script>
</body>
</html>
