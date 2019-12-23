function show() {

}

function swipe() {
    $("#screen-table-wait").css({"display": "block"});
    var card_number = $(".card-number").val();
	$.ajax({
	    type: "GET",
	    url: "http://localhost:8580/pos/validate/" + card_number,
	    success: function (data) {
    		if(data) swipe_success();
	    },
	    error: function (e) {
		alert("Error Find User :: error =" + e.responseText);
	    }
	});
}

function swipe_success() {
    $("#screen-table-wait").css({"display": "none"});
    $("#screen-table-amount").css({"display": "block"});
}

function hide_all_screens() {
    $("#screen-table-wait").css({"display": "none"});
    $("#screen-table-amount").css({"display": "none"});
    $("#screen-table-pin").css({"display": "none"});
}

function print_screen(c) {
    if($("#screen-table-amount").css("display") == "block") {
	var s = $(".amount-class").val();
	$(".amount-class").val(s + c);
    }else if($("#screen-table-pin").css("display") == "block") {
	var s = $(".pin-class").val();
	$(".pin-class").val(s + c);
    }
}

function keyboard_hash_click() {

}

function keyboard_stop_click() {

}

function keyboard_clear_click() {
    if($("#screen-table-amount").css("display") == "block") {
	//var s = $(".amount-class").val();
	$(".amount-class").val("");
    }else if($("#screen-table-pin").css("display") == "block") {
	//var s = $(".pin-class").val();
	$(".pin-class").val("");
    }else if($("#screen-table-wait").css("display") == "block") {
	$(".amount-class").val("");
	$(".pin-class").val("");
	hide_all_screens();
    }
}

function keyboard_ok_click() {
    if($("#screen-table-amount").css("display") == "block") {
	//var s = $(".amount-class").val();		
	hide_all_screens();
    	$("#screen-table-pin").css({"display": "block"});
    }else if($("#screen-table-pin").css("display") == "block") {
	//var s = $(".pin-class").val();
	connect_and_pay();
    }
}

function connect_and_pay() {
    alert("connect_and_pay =" + $(".amount-class").val());
    hide_all_screens();
    $("#screen-table-wait").css({"display": "block"});
    $(".screen-text-wait").html("SUCCESS");
}

function generate_keyboard_char() {
    $(".cell-11").click(function() {
	print_screen('1');
    });
    $(".cell-12").click(function() {
	print_screen('2');
    });
    $(".cell-13").click(function() {
	print_screen('3');
    });

    $(".cell-21").click(function() {
	print_screen('4');
    });
    $(".cell-22").click(function() {
	print_screen('5');
    });
    $(".cell-23").click(function() {
	print_screen('6');
    });

    $(".cell-31").click(function() {
	print_screen('7');
    });
    $(".cell-32").click(function() {
	print_screen('8');
    });
    $(".cell-33").click(function() {
	print_screen('9');
    });

    $(".cell-41").click(function() {
//		print_screen('*');
    });
    $(".cell-42").click(function() {
	print_screen('0');
    });
    $(".cell-43").click(function() {
//		print_screen('#');
	keyboard_hash_click();
    });

    $(".cell-51").click(function() {
//		print_screen('R');
	keyboard_stop_click();
    });

    $(".cell-52").click(function() {
//		print_screen('Y');
	keyboard_clear_click();
    });
    $(".cell-53").click(function() {
//		print_screen('G');
	keyboard_ok_click();
    });
}

