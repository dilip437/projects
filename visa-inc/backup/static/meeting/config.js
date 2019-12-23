var hostname = 'localhost';
var ip_address = '172.23.239.153';

var url_meeting = 'http://localhost:8080/'
var url_get_source_id = 'https://localhost:8080/getSourceId/'
var url_add_meeting = "http://localhost:8080/meeting/add";
var url_user_authinfo = "http://localhost:8080/authinfo/user";
var url_user_rooms_me = "http://localhost:8080/user/rooms/me";
var url_user_org = "http://localhost:8080/org/";

var request_mapping_topic_public = "/topic/public";
var request_mapping_app_chat_add_user = "/app/chat.addUser";
var request_mapping_app_chat_send_message = "/app/chat.sendMessage";

var request_mapping_topic_alert = "/topic/alert";
var request_mapping_app_alert_add_user = "/app/alert.addUser";
var request_mapping_app_alert_send_message = "/app/alert.sendMessage";

var request_mapping_topic_p2p = "/topic/p2p";
var request_mapping_app_p2p_add_user = "/app/p2p.addUser";
var request_mapping_app_p2p_send_message = "/app/p2p.sendMessage";

var show_debug = true;

var meeting_path = 'meeting';
var screenshare_view_path = 'screenshare/view.html';
