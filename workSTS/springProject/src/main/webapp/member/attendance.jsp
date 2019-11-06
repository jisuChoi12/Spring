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
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.1.0/fullcalendar.js'></script>
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
var attDate;
var arrDatesCount;

$(document).ready(function(){
	$.ajax({
			type : 'POST',
			url : '/springProject/member/getAttendances',
			data : {'id' : '${memId}'},
			dataType : 'JSON',
			success : function(data){
				attDatesCnt = data.attDates.length;
				attDate = data.attDates[0];
				/* alert('20'+attDate); */
			},
			error : function(err){
				console.log(err);
			}
	}); 
});

    function loadCalendar() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            height : 500,
        	locale: 'ko',
            selectable: true,
            plugins: ['interaction', 'dayGrid'],
            firstDay : 1,       
            header: {
                left: 'title',
                center: '',
                right : 'attBtn'
            },
            // 출석체크를 위한 버튼 생성
            customButtons: {
            	attBtn : {
            		text : '출석체크',
            		id : 'check',
            		click : function(){
            			$.ajax({
            				type : 'POST',
            				url : '/springProject/member/attendances',
            				data : {'id' : '${memId}'},
            				dataType : 'text',
            				success : function(data){
            					
            					$('.fc-attBtn-button').prop('disabled',true);
                    			$(".fc-attBtn-button").html('출석완료');
            				},
            				error : function(err){
            					console.log(err);
            				}
            			});
            		}
            	}
            },
             eventSources : [{  
            	 events: [
                     {
                   		title: '출석체크',
                   		description: '10 포인트',
                   		start: '20'+attDate  
                     },
                     {
                   		title: '출석체크',
                   		description: attDate,
                   		start: '2019-11-01'  
                     },
                     {
                    	title: '출석체크',
                    	description: '10 포인트',
                    	start: '2019-11-29'  
                      }
                	]
            
             	/* events : function(info, successCallback, failureCallback) { */
             	/* events : function(start, end, callback) { 
             		$.ajax({
             			type : 'POST',
             			url : '/springProject/member/getAttendances',
             			data : {'id' : '${memId}'},
             			dataType : 'JSON',
             			success : function(data){
             				addCalendarEvent(data.attDates);
             			},
             			error : function(err){
             				console.log(err);
             			}
             		}); */
             	/*} ,
             	events: [
                    {
                  		title: '출석체크',
                  		description: '10 포인트',
                  		start: '2019-11-01'
                    },
                    {
                  		title: '출석체크',
                  		description: '10 포인트',
                  		start: '2019-11-02'
                    },
               	] */
             }],
        
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

