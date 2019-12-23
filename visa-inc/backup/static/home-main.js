var username = null;
var userInfo = null;
var user_id = null;
var route_id = null;
var teams = [];

function util_ensure_comma(s) {
	return s.length > 0 ? "," : "";
}

function util_ensure_line_break(s) {
	return s.length > 0 ? "<br/>" : "";
}

function util_assert_empty(obj) {
	return obj == undefined || obj == null;
}

function util_assert_list_empty(obj) {
	return obj == undefined || obj == null || obj.length < 0;
}

function util_assert_string_empty(obj) {
	return util_assert_list_empty(obj);
}

function util_show_text(s) {
	alert(s);
}

function util_show_error(s, e) {
	util_show_text("Error :: " + s + " :: " + e.responseText);
}

function init() {
	$(".new-meeting-container").css({"display": "none"});
	$(".new-team-container").css({"display": "none"});
	$(".new-org-container").css({"display": "none"});
    $('.new-message-container').css({'display': 'none'});
    $('.update-team-area').css({'display': 'none'});
	$(".update-team-container").css({'display': 'none'});
    $('.inbox-meeting-area').css({'display': 'none'});
    $('.inbox-message-area').css({'display': 'none'});
    $(".reset-password-area").css({"display": "none"});
	$(".assign-role-area").css({"display": "none"});
	$(".assign-quick-role-area").css({"display": "none"});
	$(".manage-role-area").css({"display": "none"});
	$('.update-org-area').css({'display': 'none'});
	$(".update-org-container").css({'display': 'none'});
}

//function populate_manage_role_group_html() {
//	
//	$.ajax({
//	    type: "GET",
//	    url: "http://localhost:8080/team/",
//	    success: function (data) {
//	    	if(util_assert_list_empty(data)) {
//	    		util_show_text("result == undefined");
//	    		return false;
//	    	}
//	    	
//			var textHtml = "<table>";
//            $.each(data, function (index, team) {
//            	if(index < 3){
//            		textHtml = textHtml + "<tr>";
//		            textHtml = textHtml + "<td style='width: 175px;'>" + team.teamname + "</td>";
//		            
//					var usernames_string = "";
//		            $.each(team.usernames, function (index2, username) {
//		            	usernames_string = usernames_string + util_ensure_comma(usernames_string) + username;
//		            });
//		            if(usernames_string.length > 0){
//				    	textHtml = textHtml + "<td style='color: blue;'>" + usernames_string + "</td>";
//		            }
//		            usernames_string = "";
//            		textHtml = textHtml + "</tr>";
//            	}
//            });
//            textHtml = textHtml + "</table>";
//            if(textHtml.length > 0){
//	    		$(".manage-role-area").html(textHtml);	            	
//            }
//	    },
//	    error: function (e) {
//	    	util_show_error("Find Groups", e);
//	    }
//	});
//}

function add_roles_to_user(role_name, user_name) {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/role/add/user/" + user_name + "/" + role_name,
	    success: function (data) {
	    	
	    },
	    error: function (e) {
	    	util_show_error("Add Roles to User", e);
	    }
	});
}

function add_roles_to_group(role_name, group_name) {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/role/add/group/" + group_name + "/" + role_name,
	    success: function (data) {
	    	
	    },
	    error: function (e) {
	    	util_show_error("Add Roles to Group", e);
	    }
	});
}

function populate_roles() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/role/",
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var role_string = "";
	    	var role_ids = "";
	    	$.each(data, function (index, role) {
	    		role_string = role_string + util_ensure_comma(role_string) + role.name;
	    		role_ids = role_ids + util_ensure_comma(role_ids) + role.id;
			});
	    	alert("role_string = " + role_string);
	    	alert("role_ids = " + role_ids);
	    },
	    error: function (e) {
	    	util_show_error("Find All Roles", e);
	    }
	});
}

function populate_all_orgs() {
	$.ajax({
	    type: "GET",
	    url: url_user_org,
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var org_string = "";
	    	$.each(data, function (index, org) {
	    		org_string = org_string + util_ensure_comma(org_string) + org.orgname;
			});
	    },
	    error: function (e) {
	    	util_show_error("Find All Organizations", e);
	    }
	});
}

function refresh_groups() {
	populate_groups();
	populate_update_team();
}

function populate_assign_role_box(user_or_group_name, user_or_group_full_name, user_or_group_type) {
	$('.user-or-group-name').html(user_or_group_full_name);
	$('#user-or-group-value').val(user_or_group_name);
	$('#hidden-assign-type-user-or-group').val(user_or_group_type);
	if(user_or_group_type == 'GROUP'){
		$('.user-display-role-names').css({"display": "block"});
		populate_team_roles(user_or_group_name);
	}else if(user_or_group_type == 'USER'){
		$('.user-display-role-names').css({"display": "block"});
		populate_user_roles(user_or_group_name);
	}
}

function populate_team_roles(team_name) {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/role/group/" + team_name,
	    success: function (data_team_roles) {
	    	if(util_assert_list_empty(data_team_roles)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var role_html = "";
			$.each(data_team_roles, function (index, role) {
				role_html = role_html + util_ensure_line_break(role_html) + role;
			});
			$('.user-display-role-names').html(role_html);
	    },
	    error: function (e) {
	    	util_show_error("Find Group Roles", e);
	    }
	});
}

function populate_user_roles(user_name) {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/role/user/" + user_name,
	    success: function (data_user_roles) {
	    	if(util_assert_list_empty(data_user_roles)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var role_html = "";
			$.each(data_user_roles, function (index, role) {
				role_html = role_html + util_ensure_line_break(role_html) + role;
			});
			$('.user-display-role-names').html(role_html);
	    },
	    error: function (e) {
	    	util_show_error("Find User Roles", e);
	    }
	});
}

function populate_all_users() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/user/",
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var option_html = '<option value="-1">Select User</option>';
	    	var textHtml = "<table>";
	    	var user_info_string = "";
			$.each(data, function (index, user) {
				if(user.userInfo){
					user_info_string = user_info_string + util_ensure_comma(user_info_string) + user.userInfo.fullName;
				}

				option_html = option_html + '<option value="' + user.id + '">' + user.userInfo.fullName + '</option>';
				textHtml = textHtml + "<tr>";
				textHtml = textHtml + "<td style='width: 175px;'>" 
            	+ "<a href='#' onclick='toggle_assign_role_box();populate_assign_role_box(\"" 
            	+ user.username + "\", \"" + user.userInfo.fullName + "\" ,\"USER\");'>" + user.userInfo.fullName + "</a></td>";
	            textHtml = textHtml + "<td style='width: 75px;'>Google</td>";
				var roles_string = "";
				$.each(user.roles, function (index2, role) {
					roles_string = roles_string + util_ensure_comma(roles_string) + role;
				});
		    	textHtml = textHtml + "<td>" + (roles_string.length > 0 ? ((roles_string.length > 25) ? roles_string.substring(0, 25) + "..." : roles_string) : "---") + "</td>";
	            roles_string = "";
	            textHtml = textHtml + "</tr>";
			});
            textHtml = textHtml + "</table>";
            if(textHtml.length > 0){
	    		$(".manage-role-user-text").html(textHtml);
            }
	    	var user_names = $("#user-names");
	    	var other_user_names = $("#other-user-names");
			other_user_names.html(option_html);
			user_names.html(option_html);
	    },
	    error: function (e) {
	    	util_show_error("Find All Users", e);
	    }
	});
}

function populate_groups() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/team/",
	    success: function (data) {
	    	if(util_assert_list_empty(data)) {
	    		util_show_text("result == undefined");
	    		return false;
	    	}
	    	var option_html = '<option>Select Group</option>';
			var textHtml = "<table>";
			$.each(data, function (index, team) {
				teams.push(team.teamname);
				option_html = option_html + '<option>' + team.teamname + '</option>';
         		textHtml = textHtml + "<tr>";
	            textHtml = textHtml + "<td style='width: 175px;'>" 
	            	+ "<a href='#' onclick='toggle_assign_role_box();populate_assign_role_box(\"" 
	            	+ team.teamname + "\", \"" + team.teamname + "\", \"GROUP\");'>" + team.teamname + "</a></td>";
	            textHtml = textHtml + "<td style='width: 75px;'>Google</td>";
				var usernames_string = "";
	            $.each(team.userFullNames, function (index2, userFullName) {
	            	usernames_string = usernames_string + util_ensure_comma(usernames_string) + userFullName;
	            });
		    	textHtml = textHtml + "<td>" + (usernames_string.length > 0 ? usernames_string : "---") + "</td>";
	            usernames_string = "";
        		textHtml = textHtml + "</tr>";
			});
            textHtml = textHtml + "</table>";
            if(textHtml.length > 0){
	    		$(".manage-role-group-text").html(textHtml);
            }
			$("#meeting-group").html(option_html);
			$("#message-group").html(option_html);
			$("#quick-role-group").html(option_html);
	    },
	    error: function (e) {
	    	util_show_error("Find Groups", e);
	    }
	});
}
 
function populate_message_inbox() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/message/all",
	    success: function (data) {
			if(util_assert_empty(data)) {
				textHtml = "<div style='color: #FF4743;'>No Messages found</div>";
				$(".message-area-text").html(textHtml);
			}else{
				var textHtml = "<table>";
	            $.each(data, function (index, message) {
	            	if(index < 3){
	            		textHtml = textHtml + "<tr>";
   		            	textHtml = textHtml + "<td style='width: 175px;'>" + userInfo.fullName + "</td>";
				    	textHtml = textHtml + "<td style='width: 325px;'>" + message.text + "</td>";
				    	textHtml = textHtml + "<td style='text-align: right;padding-right: 10px;'>" + message.createTime + "</td>";
	            		textHtml = textHtml + "</tr>";
	            	}
	            });
	            textHtml = textHtml + "</table>";
	            if(textHtml.length > 0){
		    		$(".message-area-text").html(textHtml);	            	
	            }
			}
	    },
	    error: function (e) {
	    	util_show_error("Find Message Inbox", e);
	    }
	});
}

function populate_message_inbox_all() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/message/all",
	    success: function (data) {
			if(util_assert_empty(data)) {
				textHtml = "<div style='color: #FF4743;'>No Messages found</div>";
				$(".inbox-message-area-text").html(textHtml);
			}else{
				var textHtml = "<table>";
	            $.each(data, function (index, message) {
            		textHtml = textHtml + "<tr>";
	            	textHtml = textHtml + "<td style='width: 175px;'>" + userInfo.fullName + "</td>";
			    	textHtml = textHtml + "<td style='width: 385px;'>" + message.text + "</td>";
			    	textHtml = textHtml + "<td style='text-align: right;padding-right: 10px;'>" + message.createTime + "</td>";
            		textHtml = textHtml + "</tr>";
	            });
	            textHtml = textHtml + "</table>";
	            if(textHtml.length > 0){
		    		$(".inbox-message-area-text").html(textHtml);	            	
	            }
			}
	    },
	    error: function (e) {
	    	util_show_error("Find All Message Inbox", e);
	    }
	});
}

function populate_meeting_inbox() {
//	alert("populate_meeting_inbox()");
	if(username != undefined && username != null && username.length > 0) {
		$.ajax({
		    type: "GET",
		    url: "http://" + hostname + ":8080/meeting" + "/next/" +  user_id,
		    success: function (data) {
				if(util_assert_empty(data)) {
					textHtml = "<div style='color: #FF4743;'>No Meetings found</div>";
					$(".meeting-area-text").html(textHtml);
				}else{
					var textHtml = "<table>";
		            $.each(data, function (index, meeting) {
		            	if(index < 3){
		            		textHtml = textHtml + "<tr>";
	   		            	textHtml = textHtml + "<td style='width: 250px;'><a href='" + url_meeting + "#" + meeting.id + "'>" 
	   		            		+ meeting.name + "</a></td>";
    				    	textHtml = textHtml + "<td style='width: 100px;'>1:00 hr</td>";
    				    	textHtml = textHtml + "<td style='width: 150px;'>" + userInfo.fullName + "</td>";
    				    	textHtml = textHtml + "<td style='text-align: right;padding-right: 10px;'>" + meeting.startTime + "</td>";
		            		textHtml = textHtml + "</tr>";
		            	}
		            });
		            textHtml = textHtml + "</table>";
		            if(textHtml.length > 0){
			    		$(".meeting-area-text").html(textHtml);	            	
		            }
				}
		    },
		    error: function (e) {
		    	util_show_error("Find Meeting Inbox", e);
		    }
		});
	}
}

function populate_update_team() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/team/",
	    success: function (data) {
			if(util_assert_list_empty(data)) {
				textHtml = "<div style='color: #FF4743;'>No Team(s) found</div>";
				$(".update-team-area-text").html(textHtml);
			}else{
				var textHtml = "<table>";
	            $.each(data, function (index, team) {
            		textHtml = textHtml + "<tr>";
	            	textHtml = textHtml + "<td style='width: 575px;'><a href='#' onclick='populate_update_team_box(" + team.id + ");'>" + team.teamname + "</a></td>";
			    	textHtml = textHtml + "<td style='text-align: right;padding-right: 10px;'>Google</td>";
            		textHtml = textHtml + "</tr>";
	            });
	            textHtml = textHtml + "</table>";
	            if(textHtml.length > 0){
		    		$(".update-team-area-text").html(textHtml);	            	
	            }
			}
	    },
	    error: function (e) {
	    	util_show_error("Find Teams", e);
	    }
	});
}

function populate_org(org_id, org_orgname, org_address, org_country) {
	$('.update-org-container').css({'display': 'block'});
	$('#update-org-name').val(org_orgname);
	$('#update-org-id').val(org_id);
	$('#update-org-address').val(org_address);
	$('#update-org-country').val(org_country);
}

function populate_update_org() {
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8080/org/",
	    success: function (data) {
			if(util_assert_list_empty(data)) {
				textHtml = "<div style='color: #FF4743;'>No Org(s) found</div>";
				$(".update-org-area-text").html(textHtml);
			}else{
				var textHtml = "<table>";
	            $.each(data, function (index, org) {
            		textHtml = textHtml + "<tr>";
	            	textHtml = textHtml + "<td style='width: 250px;'><a href='#' onclick='populate_org(\"" + org.id 
	            		+ "\", \"" + org.orgname + "\", \"" + org.address + "\", \"" + org.country + "\");'>" + org.orgname + "</a></td>";
			    	textHtml = textHtml + "<td style='width: 350px;'>" + org.address + "</td>";
			    	textHtml = textHtml + "<td style='text-align: left;padding-right: 10px;'>" + org.country + "</td>";
            		textHtml = textHtml + "</tr>";
	            });
	            textHtml = textHtml + "</table>";
	            if(textHtml.length > 0){
		    		$(".update-org-area-text").html(textHtml);	            	
	            }
			}
	    },
	    error: function (e) {
	    	util_show_error("Find Teams", e);
	    }
	});
}

function populate_meeting_inbox_all() {	//alert("populate_meeting_inbox_all()");
	if(util_assert_string_empty(user_id)) return false;
	$.ajax({
	    type: "GET",
	    url: "http://" + hostname + ":8080/meeting/all" + "/" +  user_id,
	    success: function (data) {
			if(util_assert_empty(data)) {
				textHtml = "<div style='color: #FF4743;'>No Meetings found</div>";
				$(".inbox-meeting-area-text").html(textHtml);
			}else{
				var textHtml = "<table>";
	            $.each(data, function (index, meeting) {
            		textHtml = textHtml + "<tr>";
	            	textHtml = textHtml + "<td style='width: 250px;'><a href='" + url_meeting + "#" + meeting.id + "'>" 
	            		+ meeting.name + "</a></td>";
			    	textHtml = textHtml + "<td style='width: 75px;'>1:00 hr</td>";
			    	textHtml = textHtml + "<td style='width: 235px;'>" + userInfo.fullName + "</td>";
			    	textHtml = textHtml + "<td style='text-align: right;padding-right: 10px;'>" + meeting.startTime + "</td>";
            		textHtml = textHtml + "</tr>";
	            });
	            textHtml = textHtml + "</table>";
	            if(textHtml.length > 0){
		    		$(".inbox-meeting-area-text").html(textHtml);
		    		is_loaded_populate_meeting_inbox_all = true;
	            }
			}
	    },
	    error: function (e) {
	    	util_show_error("Find All Meeting Inbox", e);
	    }
	});
}

function toggle_inbox_message_box() {
	toggle_display($(".inbox-message-area"));
}

function toggle_update_team_box() {
	toggle_display($(".update-team-area"));
}

function toggle_update_org_box() {
	toggle_display($(".update-org-area"));
}

function toggle_inbox_meeting_box() {
	toggle_display($(".inbox-meeting-area"));
}

function toggle_new_message_box() {
	toggle_display($(".new-message-container"));
}

function toggle_new_meeting_box() {
	toggle_display($(".new-meeting-container"));
}

function toggle_new_team_box() {
	toggle_display($(".new-team-container"));
}

function toggle_new_org_box() {
	toggle_display($(".new-org-container"));
}

function toggle_reset_password_box() {
	toggle_display($(".reset-password-area"));
}

function toggle_assign_role_box() {
	toggle_display($(".assign-role-area"));
}

function toggle_assign_quick_role_box() {
	toggle_display($(".assign-quick-role-area"));
}

function toggle_manage_role_box() {
	toggle_display($(".manage-role-area"));
}

function toggle_display(obj) {
	if(obj) {
    	if(obj.css("display") != "none") {
    		obj.css({"display": "none"});
    	}else{
    	    obj.css({"display": "inline-block"});
    	}
    }
}

function close_all_popup_box() {
	$(".new-meeting-container").css({"display": "none"});
	$(".new-team-container").css({"display": "none"});
	$(".new-org-container").css({"display": "none"});
	$(".new-message-container").css({"display": "none"});
	$(".update-team-area").css({"display": "none"});
	$('.update-team-container').css({'display': 'none'});
	$(".inbox-meeting-area").css({"display": "none"});
	$(".inbox-message-area").css({"display": "none"});
	$(".reset-password-area").css({"display": "none"});
	$(".assign-role-area").css({"display": "none"});
	$(".assign-quick-role-area").css({"display": "none"});
	$(".manage-role-area").css({"display": "none"});
	$(".update-org-area").css({"display": "none"});
	$('.update-org-container').css({'display': 'none'});
}