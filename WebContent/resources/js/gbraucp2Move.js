function dataroomDelete(no) {
	if (confirm("ㄹㅇ?")) {
		location.href = "dataroom.delete?gd_no=" + no;
	}
}
function galleryDelete(no) {
	if (confirm("ㄹㅇ?")) {
		location.href = "gallery.delete?gg_no=" + no;
	}
}
function memberBye() {
	if (confirm("탈퇴?")) {
		location.href = "member.bye";
	}
}

function snsDelete(no) {
	if (confirm("ㄹㅇ?")) {
		location.href = "sns.delete?gs_no=" + no;
	}
}

function snsPageChange(page) {
	location.href = "sns.page.change?page=" + page;
}

function snsReplyDelete(no, page) {
	if (confirm("ㄹㅇ?")) {
		location.href = "sns.reply.delete?gsr_no=" + no + "&page=" + page;
	}
}

function summonSNSUpdateArea(no, txt, page) {
	// java : 다 바꾸게
	// js : 첫번째것만 바꾸게 -> 정규식
	// txt = txt.replace("<br>", "\r\n");
	txt = txt.replace(/<br>/g, "\r\n");
	$("#snsUpdateGsNo").val(no);
	$("#snsUpdateGsTxt").val(txt);
	$("#snsUpdatePage").val(page);
	$("#blackArea").css("left", "0px");
	$("#blackArea").css("top", "0px");
	$("#blackArea").css("opacity", "1");
	//txt = prompt("수정할 내용", txt);
	//location.href = "sns.update?gs_no=" + no + "&gs_txt=" + txt + "&page=" + page;
}









