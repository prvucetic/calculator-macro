;
(function (AJS, $, window, document, undefined) {
    "use strict";

    var url = AJS.contextPath() + "/plugins/calculator/calculate.action";

    $(document).ready(function () {
        $(".js-calculator-calculate").on('click', function (e) {
            e.preventDefault();
            var $first = $('#first-operand'),
                $second = $('#second-operand'),
                valid = validate($first);
            valid = validate($second) && valid;
            if (valid) {
                $.ajax({
                    url: url + '?first-operand=' + $first.val() + '&second-operand=' + $second.val() + '&operation=' + $('#operation').val(),
                    type: 'GET',
                    contentType: "application/json"
                }).done(function (data) {
                    processResponse(data);
                });
            }
        });
    });

    function processResponse(data) {
        $('#aui-message-bar').empty();
        AJS.messages.success({
            title: false,
            body: data,
            closeable: true
        });
    }

    function validate($field) {
        var result = true;
        if ($field.val().trim().length === 0) {
            $field.parent().find('div.error').text('Field is required.').show();
            result = false;
        } else if (!/[+-]?([0-9]*[.])?[0-9]+/g.test($field.val())) {
            $field.parent().find('div.error').text('Only numbers are allowed.').show();
            result = false;
        } else {
            $field.parent().find('div.error').hide();
        }
        return result;
    }

}(AJS, AJS.$ || jQuery, window, document));