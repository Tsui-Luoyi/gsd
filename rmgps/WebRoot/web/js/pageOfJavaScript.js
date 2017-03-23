var theTable = document.getElementById("listtable");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum");
var totalNum = document.getElementById("totalNum");
//var noMsg = document.getElementById("noMsg")

var spanPre = document.getElementById("spanPre");
var spanNext = document.getElementById("spanNext");
var spanFirst = document.getElementById("spanFirst");
var spanLast = document.getElementById("spanLast");

var theTable = document.getElementById("listbody");
var numberRowsInTable = theTable.rows.length;
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
}

//第一页
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
}

//最后一页
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
}

function hideTable() {
    for ( var i = 0; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = 'none';
    }
}

function showPage() {
    pageNum.innerHTML = "第" + page + "页";
}

//总共页数
function pageCount() {
    var count = 0;
    if ( numberRowsInTable%pageSize != 0 ) count = 1; 
    return parseInt(numberRowsInTable/pageSize) + count;
}

function preLink() { spanPre.innerHTML = "<a href='javascript:pre();'>上一页</a>"; }
function preText() { spanPre.innerHTML = "上一页";  }

function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>下一页</a>"; }
function nextText() { spanNext.innerHTML = "下一页"; }

function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>第一页</a>"; }
function firstText() { spanFirst.innerHTML = "第一页"; }

function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>最后一页</a>"; }
function lastText() { spanLast.innerHTML = "最后一页"; }

//隐藏表格
function hide() {
    for ( var i = pageSize; i<numberRowsInTable; i++ ) {
        theTable.rows[i].style.display = 'none';
    }
    
    totalNum.innerHTML = "总共" + numberRowsInTable + "条";
    totalPage.innerHTML = "总页数" + pageCount();
    pageNum.innerHTML = "第" + '1' + "页";
    
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
		totalNum.style.display = 'none';
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
		//下一页与最后一页变为可读
		nextText();
		lastText();
	}
	if(numberRowsInTable == currentRow){//如果该页所有的行数都被删除，则跳转到上一页
		pre();
		//下一页与最后一页变为可读
		nextText();
		lastText();
	}
	for ( var i = currentRow; i< showNum; i++ ) {//循环进行显示
        theTable.rows[i].style.display = '';
    }
	totalNum.innerHTML = "总共" + numberRowsInTable + "条";
	totalPage.innerHTML = "总页数" + pageCount();
    pageNum.innerHTML = "第" + '1' + "页";
}

hide();