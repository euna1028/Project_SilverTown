function connectSummonSNSControlAreaEvent() {
	var visible = false;
	// .css("opacity", "1"); .css("opacity", "0");
	var snsControlAreaHeight = $("#snsControlArea").css("height"); // 100px
	if (snsControlAreaHeight != null) {
		snsControlAreaHeight = snsControlAreaHeight.replace("px", ""); // 100
		snsControlAreaHeight -= 40; // 60

		// bottom을 -60px로(밖으로)
		$("#snsControlArea").css("bottom", "-" + snsControlAreaHeight + "px");

		$("#snsControlAreaHandle").click(
				function() {
					if (visible) {
						$("#snsControlArea").css("bottom",
								"-" + snsControlAreaHeight + "px");
					} else {
						$("#snsControlArea").css("bottom", "0px");
					}
					visible = !visible;
				});
	}
}


function connectChangeTitleColorEvent() {
	setInterval(function() {
		if ($("#siteTitle a").css("color") == "rgb(255, 255, 255)") {
			$("#siteTitle a").css("color", "#1754F1");
			$("#siteTitle a").css("text-shadow", "3px 3px 3px #E0F7FA");
		} else {
			$("#siteTitle a").css("color", "white");
			$("#siteTitle a").css("text-shadow", "3px 3px 3px #FFB74D");
		}
	}, 3000);
}

function connectCloseSNSUpdateAreaEvent() {
	$("#x").click(function() {
		$("#blackArea").css("opacity", "0");
		setTimeout(function() {
			$("#blackArea").css("left", "-100%");
			$("#blackArea").css("top", "-100%");
		}, 300);
	});
}
function connectSummonAddressSearchAreaEvent() {
	$("#memberJoinAddr1, #memberJoinAddr2").click(function() {
		new daum.Postcode({
			oncomplete : function(data) {
				// alert(data);
				// JS객체 -> 글자
				// alert(JSON.stringify(data));
				$("#memberJoinAddr1").val(data.zonecode);
				$("#memberJoinAddr2").val(data.roadAddress);
			}
		}).open();
	});
}

function connectSummonTitleAreaEvent() {
	$("#siteTitleArea").mouseenter(function() {
		$("#siteTitleArea").css("top", "0px");
	});
	$("#siteTitleArea").mouseleave(function() {
		$("#siteTitleArea").css("top", "-50px");
	});
}

function connectMemberIDCheckEvent() {
	$("#memberJoinID").keyup(
			function(e) {
				var id = $(this).val();
				if (id.length >= 4 && id.length <= 12) {
					$.ajax({
						url : "member.get",
						data : {
							gm_id : id
						},
						success : function(memberData) {
							if (memberData.members[0] == null) {
								$("#memberJoinID").css("color", "white");
								$("#error2").text("사용 가능한 아이디 입니다.");
							} else {
								$("#memberJoinID").css("color", "red");
								$("#error2").text("이미 존재하는 아이디 입니다.").css(
										"color", "red");
							}
						}
					});
				} else if (id.length < 4) {
					$("#memberJoinID").css("color", "white");
					$("#error2").text("아이디가 너무 짧습니다.");
				} else if (id.length > 12) {
					$("#memberJoinID").css("color", "white");
					$("#error2").text("아이디가 너무 깁니다.");
				}
			});
}

function connectMemberPWCheckEvent() {
	$("#memberJoinPW").keyup(function() {
		var pw = $("#memberJoinPW").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		var errorDiv = $("#error3");

		if (pw.length < 8 || pw.length > 20) {
			errorDiv.text("8자리 ~ 16자리 이내로 입력해주세요.");
		} else if (pw.search(/\s/) !== -1) {
			errorDiv.text("비밀번호는 공백 없이 입력해주세요.");
		} else if (num < 0 || eng < 0 || spe < 0) {
			errorDiv.text("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
		} else {
			errorDiv.text(""); // Clear any previous error messages
		}
	});
}

function connectMemberCPWCheckEvent() {
	$("#memberJoinPW2").keyup(function() {
		var pw = $("#memberJoinPW").val();
		var pw2 = $("#memberJoinPW2").val();
		var errorDiv = $("#error4");

		if (pw != pw2) {
			errorDiv.text("비밀 번호가 다릅니다.");
		}else{
			errorDiv.text("");
		}
	});
}
$(function() {
	connectChangeTitleColorEvent();
	connectCloseSNSUpdateAreaEvent();
	connectMemberIDCheckEvent();
	connectSummonAddressSearchAreaEvent();
	connectSummonSNSControlAreaEvent();
	connectSummonTitleAreaEvent();
	connectMemberPWCheckEvent();
	connectMemberCPWCheckEvent();
});
