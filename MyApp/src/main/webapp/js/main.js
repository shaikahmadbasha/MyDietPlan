;(function () {
	
	'use strict';



	// iPad and iPod detection	
	var isiPad = function(){
		return (navigator.platform.indexOf("iPad") != -1);
	};

	var isiPhone = function(){
	    return (
			(navigator.platform.indexOf("iPhone") != -1) || 
			(navigator.platform.indexOf("iPod") != -1)
	    );
	};

	


   [].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {  
      new SelectFx(el);
   } );

	


	// Offcanvas and cloning of the main menu
	var offcanvas = function() {

		var $clone = $('#pmd-menu-wrap').clone();
		$clone.attr({
			'id' : 'offcanvas-menu'
		});
		$clone.find('> ul').attr({
			'class' : '',
			'id' : ''
		});

		$('#pmd-page').prepend($clone);

		// click the burger
		$('.js-pmd-nav-toggle').on('click', function(){

			if ( $('body').hasClass('pmd-offcanvas') ) {
				$('body').removeClass('pmd-offcanvas');
			} else {
				$('body').addClass('pmd-offcanvas');
			}
			// event.preventDefault();

		});

		$('#offcanvas-menu').css('height', $(window).height());

		$(window).resize(function(){
			var w = $(window);


			$('#offcanvas-menu').css('height', w.height());

			if ( w.width() > 769 ) {
				if ( $('body').hasClass('pmd-offcanvas') ) {
					$('body').removeClass('pmd-offcanvas');
				}
			}

		});	

	}

	

	// Click outside of the Mobile Menu
	var mobileMenuOutsideClick = function() {
		$(document).click(function (e) {
	    var container = $("#offcanvas-menu, .js-pmd-nav-toggle");
	    if (!container.is(e.target) && container.has(e.target).length === 0) {
	      if ( $('body').hasClass('pmd-offcanvas') ) {
				$('body').removeClass('pmd-offcanvas');
			}
	    }
		});
	};


	 

	// Document on load.
	$(function(){
		offcanvas();
		mobileMenuOutsideClick();
	});


	var context_path = "/MyDitePlan";
	//Login form submit
	$('#employeeLogin').submit(function (e){
		e.preventDefault();
		
		var empId = $('#employeeID').val();
		var jsonData = {id:empId };
		

		var uri = context_path + '/getEmployeeDetails.do';

		$.ajax(uri, {  
			type: "POST",
		    data: JSON.stringify(jsonData),
		    contentType: "application/json",
		    dataType: "json",
			success: function(result) {
				$('#index').hide();
				
				$('#fname').val(result.name);
				$('#phone-no').val(result.contactNum);
				$('#email').val(result.email);
				$('#age').val(result.age);
				$('#weight').val(result.weight);
				$('#height').val(result.height);
				$('#activity').val(result.dailyAct);
				$('#gender').val(result.gender);
				//$('#empID').val(result.id);

				localStorage.setItem('emp-details', JSON.stringify(result));

				$('#emp-form').removeClass('hidden');
				$('#planModal').removeClass('hidden');
				
				var planList = result.empDietMenuList;

				$($('#day-1').children()[1]).html(planList[0].meal1);
				$($('#day-1').children()[2]).html(planList[0].meal2);
				$($('#day-1').children()[3]).html(planList[0].meal3);

				/*$('#day-2').children()[1].html(planList[1].meal1);
				$('#day-2').children()[2].html(planList[1].meal2);
				$('#day-2').children()[3].html(planList[1].meal3);

				$('#day-3').children()[1].html(planList[2].meal1);
				$('#day-3').children()[2].html(planList[2].meal2);
				$('#day-3').children()[3].html(planList[2].meal3);

				$('#day-4').children()[1].html(planList[3].meal1);
				$('#day-4').children()[2].html(planList[3].meal2);
				$('#day-4').children()[3].html(planList[3].meal3);

				$('#day-5').children()[1].html(planList[4].meal1);
				$('#day-5').children()[2].html(planList[4].meal2);
				$('#day-5').children()[3].html(planList[4].meal3);*/


				var historyList = result.empDiteConsulDetails;


				$.each(historyList, function( index, value ) {
				  var div ='<div class="col-xxs-12 col-xs-12 mt">	<div class="input-field"><label>Date:'+ value.date+'</label></div>	</div>	<div class="col-xxs-12 col-xs-12 mt"><div class="input-field">				<label>Nutritionist:'+ value.dietitionName+'</label></div></div><div class="col-xxs-12 col-xs-12 mt" style="border-bottom: solid 1px black;"><div class="input-field"><span class="diet-chart">'+value.details+'</label></div></div>';
				  $('#consult-history-content').append(div);
				});
				
		  },
		  error: function() {
			 
		  }
	   });

		/*
	   var result = {
			  "id": "57874",
			  "name": "Ahmad Basha",
			  "height": 120,
			  "weight": 70,
			  "gender": "Male",
			  "contactNum": 2147483647,
			  "email": "ahmad-basha.shaik@capgemini.com",
			  "status": "Ditetion is Reviewing your case",
			  "userId": null,
			  "age": 34,
			  "dailyAct": "Eat-Sleep",
			  "empDiteConsulDetails": [
				{
				  "date": "10/12/2016",
				  "dietitionName": "Vishal",
				  "details": "Calorie Intake is 2000 KCal per day."
				}
			  ],
			  "empDietMenuList": [
				{
				  "meal1": "50 gr Sprouts, Bread Omlet",
				  "meal2": "3 Roties, 100 gr Rice",
				  "meal3": null
				},
				{
				  "meal1": "50 gr Maggie",
				  "meal2": "3 Roties, 100 gr Rice",
				  "meal3": null
				}
			  ]
			};
	   
	   	

			$('#index').hide();
			
			$('#fname').val(result.name);
			$('#phone-no').val(result.contactNum);
			$('#email').val(result.email);
			$('#age').val(result.age);
			$('#weight').val(result.weight);
			$('#height').val(result.height);
			$('#activity').val(result.dailyAct);
			$('#gender').val(result.gender);

			$('#emp-form').removeClass('hidden');
			
			
			*/
	});

	
	
	// Emplyee form submit
	$('#employeeForm').submit(function (e){
		e.preventDefault();

	   var empInfo = {
		      id: $('#employeeID').val(),
			  name: $("#fname").val(),
			  height: $('#height').val(),
			  weight: $('#weight').val(),
			  gender: $('#gender').val(),
			  contactNum: $("#phone-no").val(),
			  email: $("#email").val(),
			  status: "Pending",
			  userId: null,
			  age: $('#age').val(),
			  dailyAct: $('#activity').val()
        };

		$.ajax({
           type: "POST",
           url: "/MyDitePlan/updateEmployeeDetails.do",
           dataType: "json",

			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				alert("details saved");
			},

           data: JSON.stringify(empInfo)
           
       });
		
		
		



	});
	
	
	$('#diet-login').click(function (e){
		//e.preventDefault();
		//var empInfo = localStorage.getItem('emp-details');
		//var empObj = JSON.parse(empInfo);
		
		$.ajax({
	           type: "POST",
	           url: "/MyDitePlan/getEmployeeList.do",
	           beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(data) {
					//var jsonData = JSON.parse(data);
					$.each(data, function( index, value ) {
						var dynaDiv = '<div class="col-md-6 col-xs-12 mt" style="color: white; margin-bottom:20px;"><div class="input-field">			<label >Employee ID: <span  class="emp-id-dyna" style="cursor:pointer;text-decoration: underline;">'+value.id+'</span></label>		</div>	</div>		<div class="col-md-6 col-xs-12 mt">		<div class="input-field">			<label>Employee Name: <span class="emp-name-dyna">'+value.name+'</span></label>		</div>	</div>';
						  $('#employeeList').append(dynaDiv);
						});
						
				}, error: function(data) {
					alert("details saved "+data);
				}
			});
		
		
		
		$('#employeeList').removeClass('hidden');
		$('#index').hide();
			
	});
	
	$('#employeeList').on('click', 'span.emp-id-dyna', function(){
		var empId = this.innerHTML;
		var jsonData = {id:empId };
		

		var uri = context_path + '/getEmployeeDetails.do';

		$.ajax(uri, {  
			type: "POST",
		    data: JSON.stringify(jsonData),
		    contentType: "application/json",
		    dataType: "json",
			success: function(result) {
				$('#emp-id-dyna').html(result.id);
				$('#emp-name-dyna').html(result.name);
				$('#emp-activity-dyna').html(result.dailyAct);
				$('#dietplanModal').removeClass('hidden');
				$('#employeeList').addClass('hidden');
				},
		  error: function() {
			 
		  }
	   });
	
	});
	
	/*var fetchEmp = function(value) {
		alert(value);
	};*/
	
	

}());