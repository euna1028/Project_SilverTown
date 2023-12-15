    document.addEventListener('DOMContentLoaded', function() {
        const btnCancle = document.getElementById('btnCancle');

        btnCancle.addEventListener('click', function() {
            // 입력 란 초기화
            const inputs = document.querySelectorAll('input');
            inputs.forEach(input => {
                input.value = '';
            });

            // 에러 메시지 초기화
            const errorDivs = document.querySelectorAll('[id^=error]');
            errorDivs.forEach(errorDiv => {
                errorDiv.textContent = '';
            });
        });
    });
function memberUpdateCheck() {
	var pwField = document.memberUpdateForm.pw;
	var pwChkField = document.memberUpdateForm.pwChk;
	var nameField = document.memberUpdateForm.name;
	var addr1Field = document.memberUpdateForm.addr1;
	var addr2Field = document.memberUpdateForm.addr2;
	var addr3Field = document.memberUpdateForm.addr3;
	var photoField = document.memberUpdateForm.photo;

	if (isEmpty(pwField) || lessThan(pwField, 4)
		|| notContains(pwField, "1234567890") || notEquals(pwField, pwChkField)) {
		alert("PW?");
		pwField.value = ""; pwChkField.value = "";
		pwField.focus();
		return false;
	}
	if (isEmpty(nameField)) {
		alert("이름?");
		nameField.value = "";
		nameField.focus();
		return false;
	}
	if (isEmpty(addr1Field) || isEmpty(addr2Field) || isEmpty(addr3Field)) {
		alert("주소?");
		addr1Field.value = ""; addr2Field.value = ""; addr3Field.value = "";
		addr1Field.focus();
		return false;
	}
	if (isEmpty(photoField)) {
		return true;
	}
	if (isNotType(photoField, "png")
		&& isNotType(photoField, "jpg")
		&& isNotType(photoField, "gif")) {
		alert("프사?");
		photoField.value = "";
		return false;
	}
	return true;
}

function joinCheck() {
	var idField = document.joinForm.id;
	var pwField = document.joinForm.pw;
	var pwChkField = document.joinForm.cpw;
	var nameField = document.joinForm.name;
	var addr1Field = document.joinForm.addr1;
	var addr2Field = document.joinForm.addr2;
	var addr3Field = document.joinForm.addr3;
	var photoField = document.joinForm.photo;

	if (isEmpty(idField) || containsHS(idField)) {
		alert("ID?");
		idField.value = "";
		idField.focus();
		return false;
	}
	if (isEmpty(pwField) || lessThan(pwField, 4)
		|| notContains(pwField, "1234567890") || notEquals(pwField, pwChkField)) {
		alert("PW?");
		pwField.value = ""; pwChkField.value = "";
		pwField.focus();
		return false;
	}
	if (isEmpty(nameField)) {
		alert("이름?");
		nameField.value = "";
		nameField.focus();
		return false;
	}
	if (isEmpty(addr1Field) || isEmpty(addr2Field) || isEmpty(addr3Field)) {
		alert("주소?");
		addr1Field.value = ""; addr2Field.value = ""; addr3Field.value = "";
		addr1Field.focus();
		return false;
	}
	if (isEmpty(photoField)
		||
		(isNotType(photoField, "png") &&
			isNotType(photoField, "jpg") &&
			isNotType(photoField, "gif"))) {
		alert("프사?");
		photoField.value = "";
		return false;
	}
	return true;
}

function loginCheck() {
	var idField = document.loginForm.id;
	var pwField = document.loginForm.pw;
	if (isEmpty(idField) || isEmpty(pwField)) {
		alert("ID와 PW를 확인해주세요.");
		idField.value = "";
		pwField.value = "";
		idField.focus();
		return false;
	}
	return true;
}

function snsReplyWriteCheck(f) {
	var txtField = f.txt;
	if (isEmpty(txtField)) {
		alert("?");
		txtField.value = "";
		txtField.focus();
		return false;
	}
	return true;
}

function snsWriteCheck() {
	var txtField = document.snsWriteForm.txt;
	if (isEmpty(txtField)) {
		alert("?");
		txtField.value = "";
		txtField.focus();
		return false;
	}
	return true;
}
function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 우편번호와 주소 정보를 가져와서 입력란에 자동으로 채우기
            document.getElementsById('addr1').value = data.zonecode;
            document.getElementsById('addr2').value = data.roadAddress;
            document.getElementsById('addr3').focus();
        }
    }).open();
}
function sendEmail() {
    // 이메일 주소를 입력한 input 요소에서 가져옵니다.
    var email = document.querySelector('input[name="gm_email"]').value;
    
    // AJAX 요청을 보냅니다.
    $.ajax({
        url: '/noticeMail', // 서버에서 이메일을 보내는 요청을 처리할 URL을 지정합니다.
        method: 'POST',
        data: { email: email }, // 이메일 주소를 서버에 전달합니다.
        success: function(response) {
            // 서버로부터의 응답을 처리합니다.
            if (response.success) {
                alert('이메일이 성공적으로 전송되었습니다.');
            } else {
                alert('이메일 전송에 실패했습니다.');
            }
        },
        error: function() {
            alert('서버 오류로 이메일 전송에 실패했습니다.');
        }
    });
}
