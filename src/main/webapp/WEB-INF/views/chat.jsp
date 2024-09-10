<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>챗봇 인터페이스</title>
</head>
	<style>
		#chatbot {
			display: none;
		}
		.bot-profile {
			width: 35px; /* 원하는 크기로 조정 */
			height: 35px; /* 원하는 크기로 조정 */
			border-radius: 50%;
			overflow: hidden;
			display: inline-block;
			justify-content: flex-end;
		}
		.chatbot-icon {
			width: 60px; /* 원하는 크기로 조정 */
			height: 60px; /* 원하는 크기로 조정 */
			border-radius: 50%;
			overflow: hidden;
			display: inline-block;
			justify-content: flex-end;
		}

		.bot-profile img {
			width: 100%; /* 원하는 크기로 조정 */
			height: auto; /* 원하는 크기로 조정 */
			border-radius: 50%;
			transform: translate(-20%, -20%); /* 용의 얼굴 부분을 중앙에 맞추기 */
		}

		.chat-6ragon {
			position: fixed; /* 화면에 고정 */
			bottom: 20px;    /* 아래쪽에서 20px 떨어진 위치 */
			right: 20px;     /* 오른쪽에서 20px 떨어진 위치 */
			display: flex;
			justify-content: flex-end;
			align-items: center;
			margin: 0;
			flex-direction: column;
		}
		.chatbot-container {
			position: fixed; /* 화면에 고정 */
			bottom: 100px;    /* 아래쪽에서 100px 떨어진 위치 */
			right: 10px;     /* 오른쪽에서 5px 떨어진 위치 */
			width: 400px;
			border-radius: 10px;
			box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
			background-color: white;
			display: flex;
			flex-direction: column;
		}
		.chatbot-header {
			background-color: #4a4a4a;
			color: white;
			padding: 10px;
			border-top-left-radius: 10px;
			border-top-right-radius: 10px;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}

		#toggle-chatbot {
			background: none;
			border: none;
			color: white;
			font-size: 16px;
			cursor: pointer;
		}

		.chatbot-content {
			display: none; /* 초기에는 숨겨진 상태 */
			flex-direction: column;
			height: 600px;
		}

		.chatbot-messages {
			display: flex;
			flex-direction: column;
			padding: 20px;
			flex-grow: 1;
			overflow-y: auto;
			border-bottom: 1px solid #ADD8E6;
		}
		.chatbot-buttons {
			display: flex;
			flex-direction: column;
			gap: 10px;
			padding: 20px;
		}

		.chatbot-buttons button {
			padding: 10px;
			border: 1px solid #4a4a4a;
			border-radius: 5px;
			background-color: white;
			color: #4a4a4a;
			cursor: pointer;
			text-align: left;
		}

		.chatbot-buttons button:hover {
			background-color: #4a4a4a;
			color: white;
		}

		.chatbot-buttons img {
			height: 30px;
			width: 30px;
		}

		.chatbot-input-area {
			display: flex;
			padding: 10px;
			border-top: 1px solid #e0e0e0;
		}

		#user-input {
			flex-grow: 1;
			padding: 10px;
			border: 1px solid #ddd;
			border-radius: 5px;
			font-size: 14px;
		}

		#send-message {
			padding: 10px 15px;
			margin-left: 10px;
			border: none;
			background-color: #4a4a4a;
			color: white;
			border-radius: 5px;
			cursor: pointer;
		}

		#send-message:hover {
			background-color: #333;
		}

		.user-time {
			text-align: right;    /* 텍스트를 오른쪽 정렬 */
		}

		.user-message, .bot-message {
			max-width: 80%; /* 메시지 너비를 70%로 제한 */
			padding: 10px;
			border-radius: 10px;
			margin-bottom: 10px;
			position: relative;
			word-wrap: break-word; /* 단어를 잘라서 줄바꿈 */
		}

		.user-message {
			background-color: #e6e6e6;
			margin-left: auto; /* 왼쪽에 여백을 주어 오른쪽에 고정 */
			text-align: right; /* 텍스트를 오른쪽 정렬 */
			border: 1px solid #ddd;
		}

		.user-message::after {
			content: '';
			position: absolute;
			top: 10px;
			right: -10px;
			width: 0;
			height: 0;
			border: 10px solid transparent;
			border-left-color: #e6e6e6;
			border-right: 0;
			border-top: 0;
		}

		.bot-message {
			background-color: #DDA0DD;
			color: white;
			margin-right: auto; /* 오른쪽에 여백을 주어 왼쪽에 고정 */
			text-align: left; /* 텍스트를 왼쪽 정렬 */
		}

		.bot-message::after {
			content: '';
			position: absolute;
			top: 10px;
			left: -10px;
			width: 0;
			height: 0;
			border: 10px solid transparent;
			border-right-color: #DDA0DD;
			border-left: 0;
			border-top: 0;
		}

		.bot-message chatbot-buttons {
			display: grid;
			grid-template-columns: repeat(3, 1fr);
			gap: 10px;
			margin: 20px 0;
		}

		.bot-message chatbot-buttons button {
			padding: 10px;
			text-align: center;
			border-radius: 10px;
			background-color: white;
			border: 1px solid #f57c20;
			color: #f57c20;
			font-size: 0.9rem;
			cursor: pointer;
			display: flex;
			flex-direction: column;
			align-items: center;
		}

		.bot-message chatbot-buttons button:hover {
			background-color: #f57c20;
			color: white;
		}
		.inline-div {
			display: inline-block;
			background-color: lightblue;
			padding: 5px;
			margin-right: 10px;
		}
	</style>
	<script>
		const messages='';
		$(window).on('load', function(){
			//init22();
			//init();
			init2();
			document.getElementById('mTime').innerText = setMessageTime();
			//document 안의 이미지, js 파일 포함 전부 로드가 되었을 경우 실행할 코드
			console.log("load 시작");
			document.getElementById('toggle-chatbot').addEventListener('click', function() {
				//const chatbotContainer = document.getElementsByClassName('chatbot-container');
				const chatbot = document.getElementById('chatbot');

				if (chatbot.style.display === 'none' || chatbot.style.display === '') {
					chatbot.style.display = 'flex';
					console.log("if")
					//toggleButton.textContent = '▲';
				} else {
					chatbot.style.display = 'none';
					console.log("else")
					//toggleButton.textContent = '▼';
				}
			});

			document.getElementById('close-chatbot').addEventListener('click', function() {
				const chatbot = document.getElementById('chatbot');
				chatbot.style.display = 'none'; // 챗봇 창을 숨김
			});

			document.getElementById('send-message').addEventListener('click', function() {
				sendMessage();
			});

			document.getElementById('user-input').addEventListener('keypress', function(event) {
				if (event.key === 'Enter') {
					sendMessage();
				}
			});

		});
		async function init(){
			try{
			const url = 'http://localhost:8080/chat/init';
			const response = await fetch(url);
			if(!response.ok){
				throw new Error('Network response was not ok');
			}

			const data = await response.html();
			/*setTimeout(()=>{
                console.log(data);
                appendMessage(data);
            },3000);*/
			console.log(data);
			const element = document.getElementById("initText");
			if(element){
				element.innerHTML = data;
			}else{
				console.log("else");
			}
			//document.getElementById('initText').innerText(data);
			}catch (error){
				console.log('Fetch error:',error);
			}
		}

		async function init2(){
			fetch('/chat/init',{
				method:'GET',
				headers:{
					'Accept':'text/html'
				}
			})
					.then(response => response.text())
					.then(data => {
						document.getElementById('initText').innerHTML = data;
						//document.getElementById('initText').innerText = data;
					})
					.catch(error => console.error('Error:',error));
		}

		async function init22(){
			fetch('/chat/data',{
				method:'GET',
				headers:{
					//'Accept':'text/html'
					//'Accept':'application/xml'
					'Accept':'application/json'
				}
			})
					.then(response => response.text())
					.then(data => {
						console.log(data);
					})
					.catch(error => console.error('Error:',error));
		}

		function setMessageTime(){
			const now = new Date();
			const hours = now.getHours(); //현재 시간의 시간 (0~23)
			const minutes = now.getMinutes();//햔재 시각의 분(0~59)
			const formattedTime = hours.toString().padStart(2,'0')+":"+minutes.toString().padStart(2,'0');
			console.log(formattedTime);

			return formattedTime;
		}

		//채팅 메시지 전송버튼시 실행
		function sendMessage() {
			const userInput = document.getElementById('user-input');
			const message = userInput.value.trim();

			if (message !== '') {
				const messageContainer = document.createElement('div');
				messageContainer.classList.add('user-message');
				messageContainer.textContent = message;
				document.getElementById('chatbot-messages').appendChild(messageContainer);
				//유저메시지 시간 입력
				appendTime("user-time");
				//Openapi실행
				openApi2(message);
				// 메시지 전송 후 입력 필드 초기화
				userInput.value = '';
				userInput.focus();
				// 챗봇 스크롤을 가장 아래로 이동
				chatbotScrollDown();
			}
		}
		function openApi(message){
			//이 함수를 통해서 get url 요청을 보내야함
			const url = 'http://localhost:8080/chat/sendMessage?user_input='+message;
			fetch(url)
					.then(response =>{
						if(!response.ok){
							throw new Error('Network response was not ok'+response.statusText)
						}
						return response.json(); // 응답을 JSON으로 변환
					})
					.then(data => {
						console.log(data);//응답 데이터를 콘솔에 출력
					})
					.then(text =>{
						console.log(text);
					})
		}

		async function openApi2(message){
			try{
				const url = 'http://localhost:8080/chat/sendMessage?user_input='+message;

				const response = await fetch(url);
				if(!response.ok){
					throw new Error('Network response was not ok');
				}

				const data = await response.text();
				/*setTimeout(()=>{
					console.log(data);
					appendMessage(data);
				},3000);*/
				//console.log(data);
				appendMessage(data);

			}catch (error){
				appendTime();
				console.log('Fetch error:',error);
			}
		}

		function appendMessage(message){
			console.log("appendMessage");
			if (message !== '') {
				console.log(message);
				const messageContainer = document.createElement('div');
				messageContainer.classList.add('bot-message');
				messageContainer.textContent = message;
				document.getElementById('chatbot-messages').appendChild(messageContainer);
				appendTime("bot-time");
				//챗봇 스크롤을 가장 아래로 이동
				chatbotScrollDown();

			}


		}

		// 챗봇 스크롤을 가장 아래로 이동
		function chatbotScrollDown(){
			const chatbotMessages = document.getElementById('chatbot-messages');
			chatbotMessages.scrollTop = chatbotMessages.scrollHeight;
		}

		function appendTime(timeType){

			console.log("timeType:"+timeType);
			//메시지 시간 유저
			const messageTime = document.createElement('div');
			messageTime.classList.add(timeType);
			messageTime.textContent = setMessageTime();
			document.getElementById('chatbot-messages').appendChild(messageTime);
			//챗봇 스크롤을 가장 아래로 이동
			chatbotScrollDown();
		}

		//봇과 유저 대화내용 전달하는 함수
		function chatMessageData(type,value){
			const map = new Map();
			if(type.equals("bot-message")){
				map.set('role','assistant');
			}else{
				map.set('role','user');
			}
			map.set('content',value)
			console.log(map);
		}

	</script>
<body>
<div class="chatbot-container">
	<div id="chatbot" class="chatbot-content">
		<div class="chatbot-header">
			<h5>6ragon</h5>
			<button id="close-chatbot">X</button>
		</div>
		<div id="chatbot-messages" class="chatbot-messages">
			<img src="/resources/images/chatBot/botIcon.png" alt="챗봇 프로필" class="bot-profile">
			<div class="bot-message">

				<p id="initText">안녕하세요, 저는 모자의정석의 LLM 챗봇 '6ragon'입니다. 무엇을 도와드릴까요?</p>
				<%--<a id="aTag" href="http://localhost:8080/login/form">로그인</a>--%>
				<div class="chatbot-buttons">
					<div>
						<a href="#">
							<div class="inline-div">
								<img src="/resources/images/chatBot/botIcon.png" alt="회사 소개">
								회사 소개
							</div>
						</a>
						<a href="#">
							<div class="inline-div">
								<img src="/resources/images/chatBot/botIcon.png" alt="회사 소개">
								회사 소개
							</div>
						</a>
					</div>
					<div>
						<a href="#">
							<div class="inline-div">
								<img src="/resources/images/chatBot/botIcon.png" alt="회사 소개">
								회사 소개
							</div>
						</a>
						<a href="#">
							<div class="inline-div">
								<img src="/resources/images/chatBot/botIcon.png" alt="회사 소개">
								회사 소개
							</div>
						</a>
					</div>
				</div>
			</div>
			<div id="mTime" class="bot-time"></div>
			<div class="user-message">
				<p>솔루션 도입 문의하기</p>
			</div>
			<div class="user-message">
				<p>6ragon팀의 구성원이 궁금해요.</p>
			</div>
		</div>
		<div class="chatbot-input-area">
			<input type="text" id="user-input" placeholder="메시지를 입력해주세요.">
			<button id="send-message">전송</button>
		</div>
	</div>
</div>
<div class="chat-6ragon">
	<a id="toggle-chatbot" target="_blank">
		<img src="/resources/images/chatBot/botIcon.png" class="chatbot-icon"/>
	</a>
</div>
</body>
</html>
