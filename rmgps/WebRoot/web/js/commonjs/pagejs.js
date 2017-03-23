//var theTable = document.getElementById("listtable");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum");
//var totalNum = document.getElementById("totalNum");
//var noMsg = document.getElementById("noMsg")

var spanPre = document.getElementById("spanPre");
var spanNext = document.getElementById("spanNext");
var spanFirst = document.getElementById("spanFirst");
var spanLast = document.getElementById("spanLast");

var forward_num = document.getElementById("forward_num");

var theTable = document.getElementById("listbody");
var numberRowsInTable = 0;
var pageSize = 10;
var page = 1;

//下一页
function next() {
    hideTable();
    
    currentRow = pageSize * page;
    maxRow = currentRow + pageSize;
    if ( maxRow > numberRowsInTable ) maxRow = numberRowsInTable;
    for ( var i = currentRow; i< maxRow; i++ ) {
        theTable.rows[i].style.display = '';
    }
    page++;
    
    if ( maxRow == numberRowsInTable )  { nextText(); lastText(); }
    showPage();
    preLink();
    firstLink();
    forward_num.value = page;
}

//跳转到指定页
function forward_to_num(){
	var fNum = forward_num.value;
	//确保输入的是数字类型
	var patrn = /^([0-9]){1,}$/;
	if(fNum.match(patrn)){//只能是数字
		fNum = parseInt(fNum);//转成整数
	}else{
		alert("请输入数字！");
		return false;
	}
	
	hideTable();
	
	if(fNum >= pageCount()){//则跳转到最后一页
    	page = pageCount();
    	if(page != 1){
    		nextText(); lastText();showPage();preLink();firstLink();
    	}else{//只有一页
    		nextText(); lastText();showPage();preText();firstText();
    	}
    }else if(fNum <= 1){//则跳转到第一页
    	page = 1;
    	if(pageCount() != 1){
    		nextLink(); lastLink();showPage();preText();firstText();
    	}else{//只有一页
    		nextText(); lastText();showPage();preText();firstText();
    	}
    }else{
    	page = fNum;
    	nextLink(); lastLink();showPage();preLink();firstLink();
    }
	forward_num.value = page;
    currentRow = pageSize * (page-1);
    maxRow = currentRow + pageSize;
    for ( var i = currentRow; i< maxRow; i++ ) {
    	if(theTable.rows[i] != undefined){
    		theTable.rows[i].style.display = '';
    	}
    }
}

//上一页
function pre() {
    hideTable();
    page--;
    
    currentRow = pageSize * page;
    maxRow = currentRow - pageSize;
    if ( currentRow > numberRowsInTable ) currentRow = numberRowsInTable;
    for ( var i = maxRow; i< currentRow; i++ ) {
        theTable.rows[i].style.display = '';
    }
    
    
    if ( maxRow == 0 ) { preText(); firstText(); }
    showPage();
    nextLink();
    lastLink();
    forward_num.value = page;
}

//首页
function first() {
    hideTable();
    page = 1;
    for ( var i = 0; i<pageSize; i++ ) {
    	if(theTable.rows[i] != undefined){
    		theTable.rows[i].style.display = '';
    	}
    }
    if(numberRowsInTable > pageSize){
    	showPage();
    	firstText();
	    preText();
	    nextLink();
	    lastLink();
    }else{
    	showPage();
    	firstText();
	    preText();
	    nextText();
	    lastText();
    }
    forward_num.value = "1";
}

//尾页
function last() {
    hideTable();
    page = pageCount();
    currentRow = pageSize * (page - 1);
    for ( var i = currentRow; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = '';
    }
    showPage();
    lastText();
    preLink();
    nextText();
    firstLink();
    forward_num.value = pageCount();
}

function hideTable() {
    for ( var i = 0; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = 'none';
    }
}

function showPage() {
    pageNum.innerHTML = "第<font color='#CD6600'>"+ page +"</font>/";
}

//总共页数
function pageCount() {
    var count = 0;
    if ( numberRowsInTable%pageSize != 0 ) count = 1; 
    return parseInt(numberRowsInTable/pageSize) + count;
}

function preLink() { spanPre.innerHTML = "<div  class='leftdirection'></div><a href='javascript:pre();'>上一页</a>"; }
function preText() { spanPre.innerHTML = "<font color='gray'>上一页</font>";  }

function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>下一页</a><div  class='rightdirection'></div>"; }
function nextText() { spanNext.innerHTML = "<font color='gray'>下一页</font>"; }

function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>首页</a>"; }
function firstText() { spanFirst.innerHTML = "<font color='gray'>首页</font>"; }

function lastLink() { spanLast.innerHTML = "<a href='javascript:last();' style='float:right;'>尾页</a>"; }
function lastText() { spanLast.innerHTML = "<font color='gray'  style='float:right;'>尾页</font>"; }

//隐藏表格
function hide() {
	page = 1;
	numberRowsInTable = theTable.rows.length;//获取tbody的行数
	
    for ( var i = pageSize; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = 'none';
    }
    
    //totalNum.innerHTML = "总共" + numberRowsInTable + "条";
    totalPage.innerHTML = pageCount() + "页";
    pageNum.innerHTML = "第<font color='#CD6600'>1</font>/";
    forward_num.value = "1";
    
    if(numberRowsInTable > pageSize){
    	firstText();
	    preText();
	    nextLink();
	    lastLink();
    }else{
    	firstText();
	    preText();
	    nextText();
	    lastText();
    }
}

function deleteIt(obj){
	obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
	numberRowsInTable = document.getElementById("tablelsw").rows.length;
	if(numberRowsInTable == 0){//如果所有的行都被删除了，没数据显示了，则不再进行显示，并删除分页标签，显示提示“无数据进行显示”
		spanPre.style.display = 'none';
		spanNext.style.display = 'none';
		spanFirst.style.display = 'none';
		spanLast.style.display = 'none';
		totalPage.style.display = 'none';
		pageNum.style.display = 'none';
		//totalNum.style.display = 'none';
		noMsg.style.display = '';
	}else{
		toCurrentPage();
	}
}

function toCurrentPage(){
	currentRow = pageSize * (page - 1);//得到当前页的开始显示的是第几行
	var showNum = currentRow + pageSize;//需要显示的行数
	//alert(showNum + "----" + numberRowsInTable);
	if(showNum >= numberRowsInTable){//如果总行数都不够，则有多少显示多少
		showNum = numberRowsInTable;
		//下一页与尾页变为可读
		nextText();
		lastText();
	}
	if(numberRowsInTable == currentRow){//如果该页所有的行数都被删除，则跳转到上一页
		pre();
		//下一页与尾页变为可读
		nextText();
		lastText();
	}
	for ( var i = currentRow; i< showNum; i++ ) {//循环进行显示
        theTable.rows[i].style.display = '';
    }
	//totalNum.innerHTML = "总共" + numberRowsInTable + "条";
	totalPage.innerHTML = pageCount() + "页";
    pageNum.innerHTML = "第<font color='#CD6600'>1</font>/";
}

hide();