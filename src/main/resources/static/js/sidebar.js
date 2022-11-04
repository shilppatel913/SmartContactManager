console.log("hello will be working on the logic of the sidebar");

const toggleBar=()=>{
	
	if($(".sidebar").is(":visible")){
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","4px");
	}else{
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","300px");
	}
}