/*-----------------------------
설 명 : 공통 함수 모음 js
-----------------------------*/
const UtilCm = {
	/**
	 * @method fn_get_formJsonData form 영역 json data 반환 함수
	 * @desc : from 영역에 input select textarea에 대한 정보를 json 형식으로 반환한다. key 값은 name 값이다.
	 * @param formEl : form Element
	 */
	fn_get_formJsonData : (formEl = $("form:first"))=>{
		const $formEl = $(formEl);
		jQuery.fn.serializeObject = function() {
			let obj = null;
			try {
				if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
					let arr = this.serializeArray();
					if (arr) {
						obj = {};
						jQuery.each(arr, function() {
							obj[this.name] = this.value;
						});
					}
				}
			} catch (e) {
				alert(e.message);
			}
			return obj;
		};
		return $formEl.serializeObject();
	},
	
	/**
	 * @method fn_postCall_ajax POST ajax call 함수
	 * @desc : ajax POST 통신 처리를 위한 함수
	 * @param url : 요청 url 정보
	 * @param data: 요청 데이터
	 * @param successCallback : 성공시 호출되는 callBack 함수
	 * @param errorCallback : 오류시 처리하는 callBack 함수
	 */
	fn_postCall_ajax: (url, data, successCallback, errorCallback) => {
		const xhr = new XMLHttpRequest();
		xhr.open('POST', url, true);
		// Content-Type 헤더 설정
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					successCallback(JSON.parse(xhr.response),xhr);
				} else {
					errorCallback(JSON.parse(xhr.response), xhr.status, xhr);
				}
			}
		};
		xhr.send(JSON.stringify(data));
	}
}