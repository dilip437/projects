var newMeetingForm = document.querySelector('#newMeetingForm');
var newTeamForm = document.querySelector('#newTeamForm');
var newOrganizationForm = document.querySelector('#newOrganizationForm');
var newMessageForm = document.querySelector('#newMessageForm');
var newAssignRoleForm = document.querySelector('#newAssignRoleForm');
var newAssignQuickRoleForm = document.querySelector('#newAssignQuickRoleForm');
var updateTeamForm = document.querySelector('#updateTeamForm');
var updateOrgForm = document.querySelector('#updateOrgForm');
var resetPasswordForm = document.querySelector('#resetPasswordForm');

newMeetingForm.addEventListener('submit', createMeeting, false);
newTeamForm.addEventListener('submit', createTeam, false);
newOrganizationForm.addEventListener('submit', createOrganization, false);
newMessageForm.addEventListener('submit', sendMessageToGroup, false);
newAssignRoleForm.addEventListener('submit', assignRole, false);
newAssignQuickRoleForm.addEventListener('submit', assignQuickRole, false);
updateTeamForm.addEventListener('submit', updateTeam, false);
updateOrgForm.addEventListener('submit', updateOrganization, false);
resetPasswordForm.addEventListener('submit', resetPassword, false);

var selected_team_users = [];
var selected_update_team_users = [];

function validate_date_time(date_str) {
	if(!date_str && date_str.trim().length == 0)
		return false;
	var sa = date_str.split(" ");
	var s_date = sa[0];
	var s_time = sa[1];
	var d = s_date.split("/");
	
	var dd = d[0];
	var MM = d[1];
	var yyyy = d[2];
	
	var t = s_time.split(":");
	var hh = t[0];
	var mm = t[1];
	
	var valid = true;
	
	if(dd == 0 || MM == 0){
		valid = false;
	}else if(dd > 31 || MM > 12 || hh > 23 || mm > 59){
		valid = false;
	}else if(yyyy < 2019 || yyyy > 2025){
		valid = false;
	}else if(MM == 2){
		var r = yyyy % 4;
		var R = yyyy % 400;
		if(r == 0 && R > 0){
			if(dd > 29)
				valid = false;
		}else if(dd > 28){
			valid = false;
		}
	}else if(MM == 4 || MM == 6 || MM == 9 || MM == 11){
		if(dd > 30){
			valid = false;
		}
	}
	return valid;
}

function test_date_time_regex() {
	var old_value = $('#meeting-time').attr('title');
	$('#meeting-time').attr('title', 'Please follow format');
	var new_value = $('#meeting-time').attr('title');
	var date_str = $('#meeting-time').val();
	if(!validate_date_time(date_str)){
		alert("Invalid Date/Time");
	}
	return false;
}

function validate_meeting_form() {
	route_id = $('#route_id').val().trim();
	if(!route_id) {
		util_show_text("Meeting name can't be empty");
		return false;
	}
	if(!validate_date_time($('#meeting-time').val())){
		util_show_text("Invalid Date/Time");
		return false;
	}
	var group_name = "unknown";
	var group_box = $("#meeting-group");
	if(group_box)
		group_name = group_box.children("option:selected").val();
	if(group_name == 'unknown' || group_name == 'Select Group') {
		util_show_text("Select a team/group and try again");
		return false;
	}
	return true;
}

function validate_create_team_form() {
	if(!$('#team-name').val().trim()) {
		util_show_text("Team name can't be empty");
		return false;
	}
	var user_name = "unknown";
	var user_names_box = $("#user-names");
	if(user_names_box)
		user_name = user_names_box.children("option:selected").val();
	if(user_name == 'unknown' || user_name == '-1') {
		util_show_text("Select an user and try again");
		return false;
	}
	return true;
}

function validate_organization_form() {
	if(!$('#org-name').val().trim()) {
		util_show_text("Organization name can't be empty");
		return false;
	}
	if(!$('#org-address').val().trim()) {
		util_show_text("Address can't be empty");
		return false;
	}
	var country_name = "unknown";
	var country_names_box = $("#org-country");
	if(country_names_box)
		country_name = country_names_box.children("option:selected").val();
	if(country_name == 'unknown' || country_name == '-1') {
		util_show_text("Select a Country and try again");
		return false;
	}
	return true;
}

function validate_send_message_to_group_form() {
	var msg_text = $('#message-text').val().trim();
	if(!msg_text) {
		util_show_text("Meeting name can't be empty");
		return false;
	}
	var group_name = "unknown";
	var group_box = $("#message-group");
	if(group_box)
		group_name = group_box.children("option:selected").val();
	if(group_name == 'unknown' || group_name == '-1') {
		util_show_text("Select a team/group and try again");
		return false;
	}
	return true;
}

function validate_assign_role_form() {
	var role_name = $('#role-name').children('option:selected').val();
	if(role_name == '-1') {
		return false;
	}
	var user_or_group_name = $('#user-or-group-value').val();
	if(!user_or_group_name) {
		return false;
	}
	return true;
}

function validate_quick_assign_role_form() {
	var user_name = $('#quick-role-user-name').val().trim();
	var role_name = $('#quick-role-name').children('option:selected').val();
	if(!user_name) {
		util_show_text("User can't be empty");
		return false;
	}
	if(!role_name || role_name == '-1') {
		util_show_text("Select a Role and try again");
		return false;
	}
	return true;
}

function validate_update_org_form() {
	if(!$('#update-org-name').val().trim()) {
		util_show_text("Organization name can't be empty");
		return false;
	}
	if(!$('#update-org-address').val().trim()) {
		util_show_text("Address can't be empty");
		return false;
	}
	var country_name = "unknown";
	var country_names_box = $("#update-org-country");
	if(country_names_box)
		country_name = country_names_box.children("option:selected").val();
	if(country_name == 'unknown' || country_name == '-1') {
		util_show_text("Select a Country and try again");
		return false;
	}
	return true;
}

function validate_update_team_form() {
	if(!$('#update-team-name').val().trim()) {
		util_show_text("Team name can't be empty");
		return false;
	}
	var user_name = "unknown";
	var user_names_box = $("#update-user-names");
	if(user_names_box)
		user_name = user_names_box.children("option:selected").val();
	if(user_name == 'unknown' || user_name == '-1') {
		util_show_text("Select an user and try again");
		return false;
	}
	return true;
}

function validate_reset_password_form() {
	var user_name = $('#reset-password-user-name').val().trim();
	if(!user_name) {
		util_show_text("User can't be empty");
		return false;
	}
	return true;
}

function page_local_callback_document_ready() {
	
	init();
	
	$('.new-meeting-container').find('.close_button').click(function() {
		$('.new-meeting-container').css({'display': 'none'});
 	});
	
	$('.new-team-container').find('.close_button').click(function() {
		$('.new-team-container').css({'display': 'none'});
 	});
	
	$('.new-org-container').find('.close_button').click(function() {
		$('.new-org-container').css({'display': 'none'});
 	});
	
	$('.update-team-container').find('.close_button').click(function() {
		$('.update-team-container').css({'display': 'none'});
 	});
	
	$('.update-org-container').find('.close_button').click(function() {
		$('.update-org-container').css({'display': 'none'});
 	});
	
	$('.new-message-container').find('.close_button').click(function() {
		$('.new-message-container').css({'display': 'none'});
 	});
	
	$('.reset-password-area').find('.close_button').click(function() {
		$('.reset-password-area').css({'display': 'none'});
 	});
	
	$('.update-team').find('a').click(function() {
		$(".update-team-container").css({'display': 'none'});
 	});
	
	$('.update-team').find('a').click(function() {
 		if($(".update-team-area").css("display") == "none") {
			close_all_popup_box();
		}
 		toggle_update_team_box();
 	});
	
	$('.update-org-section').find('a').click(function() {
 		if($(".update-org-area").css("display") == "none") {
			close_all_popup_box();
		}
 		toggle_update_org_box();
 	});
	
 	$('.inbox-message-all').find('a').click(function() {
 		if($(".inbox-message-area").css("display") == "none") {
			close_all_popup_box();
		}
 		toggle_inbox_message_box();
 	});

 	$('.inbox-meeting-all').find('a').click(function() {
 		if($(".inbox-meeting-area").css("display") == "none") {
 			close_all_popup_box();
		}
 		toggle_inbox_meeting_box();
 	});
 	
 	$('.inbox-meeting-all').find('a').click(function() {
 		if(!is_loaded_populate_meeting_inbox_all){//alert("loading populate_meeting_inbox_all()");
 			populate_meeting_inbox_all();
 		}
 	});
 	
 	$('.update-team-area').find('button').click(function() {
 		$('.update-team-area').css({'display': 'none'});
 		$('.update-team-container').css({'display': 'none'});
 	});
 	
 	$('.update-org-area').find('button').click(function() {
 		$('.update-org-area').css({'display': 'none'});
 		$('.update-org-container').css({'display': 'none'});
 	});
 	
 	$('.update-org-container').find('button').click(function() {
 		$('.update-org-container').css({'display': 'none'});
// 		do_update_org();
 	});
 	
 	$('.inbox-meeting-area').find('button').click(function() {
		$('.inbox-meeting-area').css({'display': 'none'});
 	});

    $('.inbox-message-area').find('button').click(function() {
    	$('.inbox-message-area').css({'display': 'none'});
    });
    
    $('.new-message-container').find('button').click(function() {
//    	sendMessageToGroup();
    });
    
	$(".send-message-link").find("a").click(function() {
		if($(".new-message-container").css("display") == "none") {
			close_all_popup_box();
		}
		toggle_new_message_box();
	});
    
	$(".new-meeting-section").find("a").click(function() {
		if($(".new-meeting-container").css("display") == "none") {
			close_all_popup_box();
		}
		toggle_new_meeting_box();
	});
	
	$(".new-team-section").find("a").click(function() {
		if($(".new-team-container").css("display") == "none") {
			close_all_popup_box();		
		}
		toggle_new_team_box();
	});
	
	$(".new-org-section").find("a").click(function() {
		if($(".new-org-container").css("display") == "none") {
			close_all_popup_box();
		}
		toggle_new_org_box();
	});
	
//	$("#update_link").click(function() {
//		do_update_team();
// 	});
	
	$('.update-team-container').find('button').click(function() {
		$('.update-team-container').css({'display': 'none'});
//		do_update_team();
 	});
	
	
	$(".reset-password").find("a").click(function() {
		if($(".reset-password-area").css("display") == "none") {
			close_all_popup_box();		
		}
		toggle_reset_password_box();
	});

	$('.assign-role-area-button-1').click(function() {
 		$('.assign-role-area').css({'display': 'none'});
 	});
 	
	$('.assign-role-area-button-2').click(function() {
 		$('.assign-role-area').css({'display': 'none'});
// 		assignRole();
 	});
 	
	$('.assign-quick-role-area-button-1').click(function() {
 		$('.assign-quick-role-area').css({'display': 'none'});
 	});
 	
	$('.assign-quick-role-area-button-2').click(function() {
 		$('.assign-quick-role-area').css({'display': 'none'});
 		
 	});
	
 	$('.manage-role').find('a').click(function() {
		if($('.manage-role-area').css('display') == 'none') {
			close_all_popup_box();
		}
		toggle_manage_role_box();
	});
 	
 	$('.manage-role-area-button-1').click(function() {
 		$(".assign-role-area").css({'display': 'none'});
 		$(".assign-quick-role-area").css({'display': 'none'});
 		$('.manage-role-area').css({'display': 'none'});
 		
 	});
 	
 	$('.manage-role-area-button-2').click(function() {
 		$(".assign-role-area").css({'display': 'none'});
 		$(".assign-quick-role-area").css({'display': 'block'});
 	});
 	
 	$('.reset-password-area').find('button').click(function() {
 		$('.reset-password-area').css({'display': 'none'});
 	});
 	
	$("#user-names").change(function() {
		var s_value = $("#user-names").children("option:selected").val();
		var s_name = $("#user-names").children("option:selected").html();
		var found = false;
		$.each(selected_team_users, function (index, user) {
			if(!found)
				if(user.id == s_value) found = true;
        });
		if(found) return false;
		selected_team_users.push({'id': s_value, 'name': s_name});
 		var users_string = "";
 		var users_id_string = "";
		$.each(selected_team_users, function (index, user) {
        	users_string = users_string + util_ensure_comma(users_string) + user.name;
        	users_id_string = users_id_string + util_ensure_comma(users_id_string) + user.id;
        });
		$("#team-users").val(users_id_string);
		$("#team-users-display").val(users_string);
	});
	
	$("#update-user-names").change(function() {
		var s_value = $("#update-user-names").children("option:selected").val();
		var s_name = $("#update-user-names").children("option:selected").html();
		var found = false;
		$.each(selected_update_team_users, function (index, user) {
			if(!found)
				if(user.id == s_value) found = true;
        });
		if(found) return false;
		selected_update_team_users.push({'id': s_value, 'name': s_name});
 		var users_string = "";
 		var users_id_string = "";
		$.each(selected_update_team_users, function (index, user) {
        	users_string = users_string + util_ensure_comma(users_string) + user.name;
        	users_id_string = users_id_string + util_ensure_comma(users_id_string) + user.id;
        });
		$("#update-team-users").val(users_id_string);
		$("#update-team-users-display").val(users_string);
	});
	
	populate_all_users();
	populate_groups();
	populate_meeting_inbox();
//	populate_meeting_inbox_all();
	populate_message_inbox_all();
	populate_message_inbox();
	populate_update_team();
	populate_update_org();
}

function find_group_users_then_create_meeting(group_name) {
	if(!group_name) return false;
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/team/find/" + group_name,
	    success: function (data) {
	    	if(util_assert_empty(data)) {
	    		return false;
	    	}
	    	var userids = data.userids;
	    	if(util_assert_list_empty(userids)) {
	    		return false;
	    	}
	    	
			var users_string = "";
            $.each(userids, function (index, userid) {
            	users_string = users_string + util_ensure_comma(users_string) + "\"" + userid + "\"";
            });
            
            var other_user_id = "unknown";
			var other_user_names_box = $("#other-user-names");
			if(other_user_names_box)
				other_user_id = other_user_names_box.children("option:selected").val();
			if(other_user_id && other_user_id != "unknown" && other_user_id != '-1') {
				users_string = users_string + util_ensure_comma(users_string) + "\"" + other_user_id + "\"";
			}
			if($("#meeting-users").val()){
				create_guest_user_and_create_meeting($("#meeting-users").val(), users_string);
			}else{
		    	do_create_meeting(users_string);						
			}
	    },
	    error: function (e) {
	    	util_show_error("Find Group", e);
	    }
	});
}

function find_group_users_then_send_message(group_name) {
	if(!group_name) return false;
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/team/find/" + group_name,
	    success: function (data) {
	    	if(util_assert_empty(data)) {
	    		return false;
	    	}
	    	var users = data.usernames;
	    	if(util_assert_list_empty(users)) {
	    		return false;
	    	}
	    	do_send_message_to_group(users);
	    },
	    error: function (e) {
	    	util_show_error("Find Group Users", e);
	    }
	});
}

function test_ajax() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/message/all",
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var msg_string = "";
	    	$.each(data, function (index, msg) {
	    		msg_string = msg_string + util_ensure_comma(msg_string) + "[ sender =" + msg.sender + ", text ="+  msg.text + "]";
			});
	    	util_show_text("msg_string =" + msg_string);
	    },
	    error: function (e) {
	    	util_show_error("Find All Messages", e);
	    }
	});
}

function test_ajax_2() {
	var password = '$2a$08$BkfhdoTuVNXgUV6sxFy.Ke/eUhANLfOBs72bcWSZZXS01zlpYQhme';
	var json = {'username' : 'temp5', 'password': '$2a$08$BkfhdoTuVNXgUV6sxFy.Ke/eUhANLfOBs72bcWSZZXS01zlpYQhme'};
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/user/add/temp?username=temp5&password=" + password ,
/* 			    type: "POST",
			    url: "http://localhost:8080/user/add/temp",
			    data: json,  
	            processData: false,
	            contentType: "application/json", 
	            cache: false,
	            dataType: "json", */
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	util_show_text("result =" + data);
	    },
	    error: function (e) {
	    	util_show_error("Create Temp User", e);
	    }
	});
}

function do_send_message_to_group(group_users) {
	if(username != undefined && username != null && username.length > 0) {
		var users_string = "";
        $.each(group_users, function (index, user) {
        	users_string = users_string + util_ensure_comma(users_string) + "\"" + user + "\"";
        });
        
        // use message-users values 
        
		var json = "{\"id\" : \"-12345\", \"sender\" : \"" + username + "\", \"text\" : \"" + ($('#message-text').val()) + "\", \"users\" : " 
		+ "[" 
			+ users_string 
		+ "]"
		+ "}";
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/message/send",
		    data: json,  
            processData: false,
            contentType: "application/json", 
            cache: false,
            dataType: "json",
		    success: function (result) {
		    	populate_message_inbox();
				populate_message_inbox_all();
		    },
		    error: function (e) {
		    	util_show_error("Send Message", e);
		    }
		});
	}
}

function populate_all_users_2() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/user/",
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var option_html = '<option value="Select User">Select User</option>';
			$.each(data, function (index, user) {
				 option_html = option_html + '<option value="' + user.id + '">' + user.userInfo.fullName + '</option>';
			});
			var user_names_box = $("#update-user-names");
			user_names_box.html(option_html);
	    },
	    error: function (e) {
	    	util_show_error("Find All Users", e);
	    }
	});
}

function assignQuickRole(event){
	if(validate_quick_assign_role_form()){
		var role_name = $('#quick-role-name').children('option:selected').val();
		var user_name = $('#quick-role-user-name').val().trim();
		add_roles_to_user(role_name, user_name);		
	}
	event.preventDefault();
}

function assignRole(event){
	if(validate_assign_role_form()){
		var user_or_group_type = $('#hidden-assign-type-user-or-group').val();
		if(user_or_group_type == 'GROUP')
			add_roles_to_group(role_name, user_or_group_name);
		else if(user_or_group_type == 'USER')
			add_roles_to_user(role_name, user_or_group_name);
	}
	event.preventDefault();
}

function populate_update_team_box(team_id) {
	$(".update-team-container").css({'display': 'block'});
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/team/get/" + team_id,
	    success: function (team) {
	    	if(team == undefined || team == null) {
	    		return false;
	    	}
	    	$("#update-team-id").val(team.id);
	    	$("#update-team-name").val(team.teamname);
	    	$("#update-team-users").val("");
	    	var exist_users_string = "";
	    	var exist_users_string_display = "";
			if(team.userFullNames && team.userids && team.userids.length > 0 && team.userFullNames.length > 0) {
	    		$.each(team.userFullNames, function (index, userFullName) {
	    			selected_update_team_users.push({'id': team.userids[index], 'name': userFullName});
	    			exist_users_string_display = exist_users_string_display + util_ensure_comma(exist_users_string_display) + userFullName;
	    			exist_users_string = exist_users_string + util_ensure_comma(exist_users_string) + team.userids[index];
	    		});
	    		$("#update-team-users").val(exist_users_string);
	    		$("#update-team-users-display").val(exist_users_string_display);
	    	}
	    	populate_all_users_2();
	    },
	    error: function (e) {
	    	util_show_error("Find Team", e);
	    }
	});
}

function updateOrganization(event) {
	if(validate_update_org_form()){
		do_update_org();
	}
	event.preventDefault();
}
	
function do_update_org() {
	//alert("do_update_org");
    var json = "{\"id\" : " + $('#update-org-id').val().trim() + ", \"orgname\" : \"" + $('#update-org-name').val().trim() 
	+ "\", \"address\" : \"" + $('#update-org-address').val().trim()
	+ "\", \"country\" : \"" + $("#update-org-country").children("option:selected").val()
	+ "\""
	+ "}";
    //alert(json);
	$.ajax({
	    type: "POST",
	    url: "http://localhost:8080/org/save",
	    data: json,  
	    processData: false,
	    contentType: "application/json", 
	    cache: false,
	    dataType: "json",
	    success: function (result) {
	    	if(result != undefined && result != null){
	    		populate_update_org();
	    	}
	    },
	    error: function (e) {
	    	util_show_error("Create Organization", e);
	    }
	});
}

function updateTeam(event) {
	if(validate_update_team_form()){
		do_update_team();
	}
	event.preventDefault();
}

function do_update_team() {
	var team_id = $("#update-team-id").val();
	var team_name = $('#update-team-name').val();
	var users_string = "";
	$.each(selected_update_team_users, function (index, user) {
       	users_string = users_string + util_ensure_comma(users_string) + "\"" + user.id + "\"";
 	});
   	var json = "{\"id\" : " + team_id + ", \"teamname\" : \"" + team_name + "\", \"usernames\" : " 
		+ "["  
		+ users_string 
		+ "]"
		+ "}";
   	//alert(json);
	$.ajax({
	    type: "POST",
	    url: "http://" + hostname + ":8080/team/save" + "/" + team_id,
	    data: json,  
        processData: false,
        contentType: "application/json", 
        cache: false,
        dataType: "json",
	    success: function (result) {
	    	if(result != undefined && result != null){
	    		refresh_groups();
	    	}
	    },
	    error: function (e) {
	    	util_show_error("Update Group", e);
	    }
	});
}

function create_guest_user_and_create_meeting(guest_user_name, exist_users_string) {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/user/add/temp?username=" + guest_user_name,
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var guest_user = JSON.parse(data);
	    	var guest_user_id = guest_user.id;
	    	guest_user_name = guest_user.username;
			var users_string = exist_users_string + util_ensure_comma(exist_users_string) + "\"" + guest_user_id + "\"";
			do_create_meeting(users_string);
	    },
	    error: function (e) {
	    	util_show_error("Create Guest User", e);
	    }
	});
}

var is_loaded_populate_meeting_inbox_all = false;

function do_create_meeting(users_string) {
	if(username != undefined && username != null && username.length > 0) {
		var json = "{\"id\" : \"-12345\", \"sender\" : \"" + username + "\", \"name\" : \"" 
		+ route_id + "\", \"room\" : \"" + route_id + "\", \"startTime\" : \""
		+ $('#meeting-time').val().trim() + "\", \"users\" : " 
		+ "["
			+ users_string
		+ "]"
		+ "}";
		//alert(json);
		$.ajax({
		    type: "POST",
		    url: url_add_meeting,
		    data: json,  
            processData: false,
            contentType: "application/json", 
            cache: false,
            dataType: "json",
		    success: function (result) {
		    	populate_meeting_inbox();
//		    	populate_meeting_inbox_all();
		    	is_loaded_populate_meeting_inbox_all = false;
		    },
		    error: function (e) {
		    	util_show_error("Create Meeting", e);
		    }
		});
	}
}

function do_reset_password(){
	alert("Reset Password task");
}

function resetPassword(event) {
	if(validate_reset_password_form()){
		do_reset_password();
	}
	event.preventDefault();
}

function sendMessageToGroup(event) {
	if(validate_send_message_to_group_form()){
		toggle_new_message_box();
		var group_name = "unknown";
		var group_box = $("#message-group");
		if(group_box)
			group_name = group_box.children("option:selected").val();
		find_group_users_then_send_message(group_name);
	}
	event.preventDefault();
}

function createMeeting(event) {
	var group_name = "unknown";
	var group_box = $("#meeting-group");
	if(group_box)
		group_name = group_box.children("option:selected").val();
	if(validate_meeting_form()){
		toggle_new_meeting_box();
		find_group_users_then_create_meeting(group_name);
	}
	event.preventDefault();
}

function do_create_team() {
	var users_string = "";
	$.each(selected_team_users, function (index, user) {
       	users_string = users_string + util_ensure_comma(users_string) + "\"" + user.id + "\"";
	});
	var json = "{\"id\" : 0, \"teamname\" : \"" + $('#team-name').val().trim() + "\", \"usernames\" : " 
		+ "["  
		+ users_string 
		+ "]"
		+ "}";
	$.ajax({
	    type: "POST",
	    url: "http://localhost:8080/team/add",
	    data: json,  
        processData: false,
        contentType: "application/json", 
        cache: false,
        dataType: "json",
	    success: function (result) {
	    	if(result != undefined && result != null){
	    		refresh_groups();
	    	}
	    },
	    error: function (e) {
	    	util_show_error("Create Group", e);
	    }
	});
}

function createTeam(event) {
	if(validate_create_team_form()){
		toggle_new_team_box();
		do_create_team();
	}
	event.preventDefault();
}

function do_create_org() {
    var json = "{\"id\" : 0, \"orgname\" : \"" + $('#org-name').val().trim() 
    	+ "\", \"address\" : \"" + $('#org-address').val().trim()
    	+ "\", \"country\" : \"" + $("#org-country").children("option:selected").val()
    	+ "\""
		+ "}";
	$.ajax({
	    type: "POST",
	    url: "http://localhost:8080/org/add",
	    data: json,  
        processData: false,
        contentType: "application/json", 
        cache: false,
        dataType: "json",
	    success: function (result) {
	    	if(result != undefined && result != null){
	    		populate_update_org();
	    	}
	    },
	    error: function (e) {
	    	util_show_error("Create Organization", e);
	    }
	});
}

function createOrganization(event) {
	if(validate_organization_form()){
		toggle_new_org_box();
		do_create_org();
	}
	event.preventDefault();
}