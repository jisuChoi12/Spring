<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
  <head>
<script src="https://unpkg.com/jquery@3.4.1/dist/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>

<link href='https://unpkg.com/@fullcalendar/core@4.3.1/main.min.css' rel='stylesheet' />
<link href='https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.css' rel='stylesheet' />
<link href='https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.css' rel='stylesheet' />
<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' rel='stylesheet' />
<script src='https://unpkg.com/@fullcalendar/core@4.3.1/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/interaction@4.3.0/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.js'></script>
<script src='https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.js'></script>
</head>

<body>
	<div id='calendar' style="width: 600px;"></div>
</body>

<div id="fullCalModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="modalTitle" class="modal-title"></h4>
            </div>
            <div id="modalBody" class="modal-body"></div>
        </div>
    </div>
    Test
</div>


<script>

    function loadCalendar() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            height : 500,
        	locale: 'ko',
            selectable: true,
            plugins: ['interaction', 'dayGrid'],
            firstDay : 1,       
            header: {
                left: 'attBtn',
                center: 'title',
                right: 'prev, next',
            },
            
            events: [
                {
              		title: 'All Day Event',
              		description: 'www',
              		start: '2019-11-03'
                },
           	],
            // 출석체크를 위한 버튼 생성
            customButtons: {
            	attBtn : {
            		text : '출석체크',
            		id : 'check',
            		click : function(){
            			// ajax 통신으로 출석 정보 저장하기
            			// POSR "/member/attendeances" -> { status : "success", date : "2019-11-05"}
            			// 통신 성공시 버튼 바꾸고, property disabled 만들기
            			$.ajax({
            				type : 'POST',
            				url : '/springProject/member/attendances',
            				data : {'id' : 'orange'},
            				dataType : 'text',
            				success : function(data){
            					$('.fc-attBtn-button').prop('disabled',true);
                    			$(".fc-attBtn-button").html('출석완료');
            				},
            				error : function(err){
            					console.log(err);
            				}
            			});
/*             			alert("출석체크"); */   
            		}
            	}
            },
            // 달력 정보 가져오기
            eventSources : [
            	{
            		// ajax 통신으로 달력 정보 가져오기
            		// GET "/member/attendances" -> {dateList:[ date: "2019-11-01", ... ]}
            		/* url : '/member/attendances',
            		type : 'GET',
            		dataType : 'JSON',
            		success : function(data){
            			
            		},
            		error : function(){
            			alert("에러");
            		},  
            		color : 'purple',
            		textColor : 'white' */
            	}
            ],
        
            eventClick: function(event) {
                console.log('modal', event);
                $('#fullCalModal').modal();
                $('#modalTitle').html(event.event.title);
                $('#modalBody').html(event.event.extendedProps.description);
            },

        });
        calendar.render();
    };

    if (document.readyState !== 'complete') {
            document.addEventListener('DOMContentLoaded', loadCalendar);
    } else {
            loadCalendar();
    }
</script>

