/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('#login-form-link').click(function (e) {
        $("#loginform").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#loginform").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    $("#initDialog").click(function () {
        
        $("#deleteConfirmDialog").dialog({
            modal: true,
            width: 300,
            resizable: false,
            buttons: {
                Confirm: function () {
                    $("#confirmButton").click();
                },
                Close: function () {
                    $(this).dialog("close");
                }
            },
        });
        $(".ui-dialog").addClass("deleteDialog");
    });
    
    $(".initDialog").click(function () {
        $(this).next("span").dialog({
            modal: true,
            width: 300,
            resizable: false,
            buttons: {
                Confirm: function () {
                    $(this).parent.parent.children("span").children("input:submit").click();
                },
                Close: function () {
                    $(this).dialog("close");
                }
            },
        });
        $(".ui-dialog").addClass("deleteDialog");
    });
    
    $(".dropdown").hover(            
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop( true, true ).slideDown("fast");
            $(this).toggleClass('open');        
        },
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop( true, true ).slideUp("fast");
            $(this).toggleClass('open');       
        }
    );
});
