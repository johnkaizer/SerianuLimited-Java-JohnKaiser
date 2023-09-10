$(document).ready(function () {
    let ui = $('.edit-view');

    function postConfiguration(serializeArray) {
        console.log('serializeArray = ', serializeArray)
        const slackConf = {};
        $.each(serializeArray, function () {
            slackConf[this.name] = this.value || "";
        });

        let settings = {
            "url": "/slack-configurations",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify(slackConf),
        };

        $.ajax(settings).done(function (response) {
            console.log(response);
            if(response.status === "00"){
                //clear form
                Swal.fire('Slack configuration saved successfully')
            }else{
                Swal.fire(
                    'Error',
                    'Unable to create Slack configuration',
                    'question'
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
                token: {validators: {notEmpty: {message: 'Token is required'}}}
            }
        })
        .on('success.fv.form', function (e) {
            e.preventDefault();
            $("form", ui).data('formValidation').resetForm();
            postConfiguration($(this).serializeArray());
        });
})