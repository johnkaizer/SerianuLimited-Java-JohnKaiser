$(document).ready(function () {
    let ui = $('.edit-view');

    function postMessage(serializeArray) {
        console.log('serializeArray = ', serializeArray)
        const slackMessage = {};
        $.each(serializeArray, function () {
            slackMessage[this.name] = this.value || "";
        });

        let settings = {
            "url": "/notifications",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(slackMessage),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            if (response.status === "00") {
                //clear form
                Swal.fire('Message sent successfully')
            } else {
                Swal.fire(
                    'Error', response.message, 'question'
                )
            }
        });
    }

    $('form', ui)
        .formValidation({
            framework: 'bootstrap',
            excluded: ':disabled',
            fields: {
                channelId: {validators: {notEmpty: {message: 'Channel Id is required'}}},
                message: {validators: {notEmpty: {message: 'Message is required'}}}
            }
        })
        .on('success.fv.form', function (e) {
            e.preventDefault();
            $("form", ui).data('formValidation').resetForm();
            postMessage($(this).serializeArray());
        });
})