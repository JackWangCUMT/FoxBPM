<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程状态</title>
<link rel="stylesheet" type="text/css" href="common/css/reset.css">
<link rel="stylesheet" type="text/css" href="common/css/form.css" />
<link rel="stylesheet" type="text/css" href="common/css/flowGraph.css" />

<script type="text/javascript" src="common/js/jquery.js"></script>
<script type="text/javascript" src="common/js/foxbpmframework.js"></script>
<script type="text/javascript" src="common/js/flowDetailInfor.js"></script>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div style="padding: 10px; height: 95%; background: #fff;">
		<div class="process" id="process">
			<div id="taksDetailDiv" class="proc_bg"></div>
			<div id="runningTrackDIV" style="display: none;" class="proc_bg"></div>
			<div id="flowGraphicDiv" class="proc_bg"></div>
		</div>
	</div>
</body>

<script type="text/javascript">
	//创建任务详细信息对象
	var taskDeatailInfor = new Foxbpm.TaskDeatailInfor({taksDetailDiv:'taksDetailDiv',runningTrackDIV:'runningTrackDIV',flowGraphicDiv:'flowGraphicDiv'});
	taskDeatailInfor.init();
</script>
</html>