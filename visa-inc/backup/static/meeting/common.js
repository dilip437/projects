
function debug(str) {
  if(show_debug) {
    var debug_box = $("#users-video-section");
    debug_box.append(str + "<br/>");
  }
}
