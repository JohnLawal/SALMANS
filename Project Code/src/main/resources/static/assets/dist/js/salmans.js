$(function () {
    //Initialize Select2 Elements
    $(".select2").select2();
});


function cancelAppointment(appointmentId) {
    $.confirm({
        title: 'Are you sure you want to cancel this appointment?',
        content: "This action cannot be reversed",
        buttons: {
            cancel: {
                text: 'No, Do Not Cancel',
                btnClass: 'btn btn-default'
            },
            confirm: {
                text: "Yes, Cancel Now",
                btnClass: 'btn btn-warning',
                action: function () {
                    location.href = "/salmans/customer/cancel_appointment/" + appointmentId;
                }
            }
        }
    });
}
function cancelAppointmentByAdmin(appointmentId) {
    $.confirm({
        title: 'Are you sure you want to cancel this appointment?',
        content: "This action cannot be reversed",
        buttons: {
            cancel: {
                text: 'No, Do Not Cancel',
                btnClass: 'btn btn-default'
            },
            confirm: {
                text: "Yes, Cancel Now",
                btnClass: 'btn btn-warning',
                action: function () {
                    location.href = "/salmans/admin/cancel_appointment/" + appointmentId;
                }
            }
        }
    });
}